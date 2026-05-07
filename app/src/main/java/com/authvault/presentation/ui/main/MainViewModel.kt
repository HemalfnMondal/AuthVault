package com.authvault.presentation.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.authvault.data.repository.AccountRepository
import com.authvault.data.repository.SettingsRepository
import com.authvault.data.repository.SettingsState
import com.authvault.domain.model.Account
import com.authvault.domain.usecase.DeleteAccountUseCase
import com.authvault.domain.usecase.GenerateCodeUseCase
import com.authvault.domain.usecase.GetAccountsUseCase
import com.authvault.presentation.model.AccountUiModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import android.content.Context
import android.net.Uri
import com.authvault.presentation.ui.common.OtpUriParser
import com.google.zxing.BinaryBitmap
import com.google.zxing.MultiFormatReader
import com.google.zxing.NotFoundException
import com.google.zxing.RGBLuminanceSource
import com.google.zxing.common.HybridBinarizer
import com.google.zxing.common.GlobalHistogramBinarizer
import com.google.zxing.DecodeHintType
import com.google.zxing.BarcodeFormat
import java.lang.Exception
import kotlin.coroutines.resume
import kotlinx.coroutines.suspendCancellableCoroutine
import javax.inject.Inject

data class MainUiState(
    val accounts: List<AccountUiModel> = emptyList(),
    val searchQuery: String = "",
    val searchExpanded: Boolean = false,
    val reorderMode: Boolean = false,
    val settings: SettingsState = SettingsState(),
    val deleteTarget: AccountUiModel? = null,
    val selectedAccountId: Int? = null
)

sealed interface MainUiEvent {
    data class CopyToClipboard(val text: String) : MainUiEvent
    data class ShowSnackbar(val message: String) : MainUiEvent
}

@HiltViewModel
class MainViewModel @Inject constructor(
    getAccountsUseCase: GetAccountsUseCase,
    private val generateCodeUseCase: GenerateCodeUseCase,
    private val deleteAccountUseCase: DeleteAccountUseCase,
    private val accountRepository: AccountRepository,
    settingsRepository: SettingsRepository
) : ViewModel() {
    private val searchQuery = MutableStateFlow("")
    private val searchExpanded = MutableStateFlow(false)
    private val reorderMode = MutableStateFlow(false)
    private val deleteTarget = MutableStateFlow<AccountUiModel?>(null)
    private val events = MutableSharedFlow<MainUiEvent>(extraBufferCapacity = 8)
    private val _imagePickerRequests = Channel<Unit>(capacity = Channel.BUFFERED)
    val imagePickerRequests = _imagePickerRequests.receiveAsFlow()
    private val _scanRequests = Channel<Unit>(capacity = Channel.BUFFERED)
    val scanRequests = _scanRequests.receiveAsFlow()

    private val ticker = flow {
        while (true) {
            emit(System.currentTimeMillis())
            delay(1000)
        }
    }

    val uiState: StateFlow<MainUiState> = combine(
        getAccountsUseCase(),
        settingsRepository.state,
        ticker,
        searchQuery,
        searchExpanded,
        reorderMode,
        deleteTarget
    ) { values: Array<Any?> ->
        val accounts = values[0] as List<Account>
        val settings = values[1] as SettingsState
        val now = values[2] as Long
        val query = values[3] as String
        val expanded = values[4] as Boolean
        val reorder = values[5] as Boolean
        val delete = values[6] as AccountUiModel?
        val models = accounts
            .sortedWith(sortComparator(settings))
            .map { account -> account.toUiModel(generateCodeUseCase(account, now)) }
            .filter { query.isBlank() || it.issuer.contains(query, true) || it.accountName.contains(query, true) }
        MainUiState(
            accounts = models,
            searchQuery = query,
            searchExpanded = expanded,
            reorderMode = reorder,
            settings = settings,
            deleteTarget = delete
        )
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5_000), MainUiState())

    fun events() = events

    fun requestImagePick() {
        _imagePickerRequests.trySend(Unit)
    }

    fun requestScan() {
        _scanRequests.trySend(Unit)
    }

    fun decodeAndSaveFromQr(rawUri: String, onResult: (success: Boolean, message: String) -> Unit) {
        viewModelScope.launch {
            try {
                kotlinx.coroutines.withContext(Dispatchers.Main) {
                    android.util.Log.d("QrScan", "decodeAndSaveFromQr received: $rawUri")
                }
                if (!rawUri.startsWith("otpauth://", ignoreCase = true)) {
                    withContext(Dispatchers.Main) { onResult(false, "Not a valid 2FA QR code") }
                    return@launch
                }

                val parsed = try {
                    OtpUriParser.parse(rawUri)
                } catch (e: Exception) {
                    // fallback to forgiving parse
                    try {
                        parseOtpAuthUri(rawUri)
                    } catch (ex: Exception) { null }
                }

                if (parsed == null) {
                    withContext(Dispatchers.Main) {
                        onResult(false, "Could not read QR code. Try again.")
                    }
                    return@launch
                }

                android.util.Log.d("QrScan", "Parsed OTP URI: issuer=${parsed.issuer} account=${parsed.accountName} secretLen=${parsed.secretKey.length}")

                if (parsed.secretKey.isBlank()) {
                    withContext(Dispatchers.Main) { onResult(false, "QR code has no secret key") }
                    return@launch
                }

                val account = com.authvault.domain.model.Account(
                    id = 0,
                    issuer = parsed.issuer,
                    accountName = parsed.accountName,
                    secretKey = parsed.secretKey,
                    algorithm = parsed.algorithm,
                    digits = parsed.digits,
                    period = parsed.period,
                    type = parsed.type,
                    counter = parsed.counter,
                    position = 0,
                    createdAt = System.currentTimeMillis(),
                    iconSlug = parsed.iconSlug
                )

                // Save on IO
                withContext(Dispatchers.IO) {
                    try {
                        android.util.Log.d("QrScan", "Saving account to DB: ${parsed.issuer}")
                        accountRepository.addAccount(account)
                        android.util.Log.d("QrScan", "Account saved")
                    } catch (e: Exception) {
                        android.util.Log.e("QrScan", "DB insert failed", e)
                        throw e
                    }
                }

                events.emit(MainUiEvent.ShowSnackbar("${parsed.issuer} added successfully"))
                withContext(Dispatchers.Main) { onResult(true, "") }

            } catch (e: Exception) {
                events.emit(MainUiEvent.ShowSnackbar("Error saving account"))
                withContext(Dispatchers.Main) { onResult(false, "Error: ${e.message}") }
            }
        }
    }

    // forgiving parser fallback (returns ParsedOtpUri or null)
    private fun parseOtpAuthUri(uriStr: String): com.authvault.presentation.ui.common.ParsedOtpUri? {
        return try {
            val parsed = android.net.Uri.parse(uriStr)
            if (parsed.scheme != "otpauth") return null
            val type = parsed.host ?: "totp"
            val rawPath = parsed.path?.trimStart('/') ?: ""
            val decodedPath = java.net.URLDecoder.decode(rawPath, "UTF-8")
            val issuer: String
            val accountName: String
            if (decodedPath.contains(':')) {
                val parts = decodedPath.split(':', limit = 2)
                issuer = parts[0].trim()
                accountName = parts[1].trim()
            } else {
                accountName = decodedPath.trim()
                issuer = parsed.getQueryParameter("issuer") ?: decodedPath.trim()
            }
            val secret = parsed.getQueryParameter("secret") ?: return null
            val cleanSecret = secret.replace(" ", "").replace("-", "").uppercase().trimEnd('=')
            val algorithm = parsed.getQueryParameter("algorithm")?.uppercase() ?: "SHA1"
            val digits = parsed.getQueryParameter("digits")?.toIntOrNull() ?: 6
            val period = parsed.getQueryParameter("period")?.toIntOrNull() ?: 30
            val counter = parsed.getQueryParameter("counter")?.toLongOrNull() ?: 0L
            com.authvault.presentation.ui.common.ParsedOtpUri(
                issuer = issuer.ifBlank { "Unknown" },
                accountName = accountName.ifBlank { issuer },
                secretKey = cleanSecret,
                algorithm = algorithm,
                digits = digits,
                period = period,
                type = type.uppercase(),
                counter = counter,
                iconSlug = null
            )
        } catch (e: Exception) {
            null
        }
    }

    fun decodeAndSaveFromImage(context: Context, uri: Uri) {
        viewModelScope.launch {
            android.util.Log.d("ImageUpload", "decodeAndSaveFromImage starting for uri=$uri")
            withContext(Dispatchers.IO) {
                try {
                    val raw = decodeRawFromImage(context, uri)
                    android.util.Log.d("ImageUpload", "Decoded raw text from image: ${raw?.take(200)}")
                    if (raw == null || !raw.startsWith("otpauth://", ignoreCase = true)) {
                        events.emit(MainUiEvent.ShowSnackbar("Could not read QR code from this image"))
                        return@withContext
                    }
                    val parsed = try {
                        OtpUriParser.parse(raw)
                    } catch (e: Exception) {
                        android.util.Log.e("ImageUpload", "OtpUriParser.parse failed, trying fallback", e)
                        parseOtpAuthUri(raw)
                    }
                    if (parsed == null) {
                        events.emit(MainUiEvent.ShowSnackbar("Could not parse otpauth URI"))
                        return@withContext
                    }
                    val account = Account(
                        id = 0,
                        issuer = parsed.issuer,
                        accountName = parsed.accountName,
                        secretKey = parsed.secretKey,
                        algorithm = parsed.algorithm,
                        digits = parsed.digits,
                        period = parsed.period,
                        type = parsed.type,
                        counter = parsed.counter,
                        position = 0,
                        createdAt = System.currentTimeMillis(),
                        iconSlug = parsed.iconSlug
                    )
                    try {
                        android.util.Log.d("ImageUpload", "Inserting account: ${parsed.issuer}")
                        accountRepository.addAccount(account)
                        events.emit(MainUiEvent.ShowSnackbar("${parsed.issuer} added successfully"))
                        android.util.Log.d("ImageUpload", "Account inserted successfully")
                    } catch (e: Exception) {
                        android.util.Log.e("ImageUpload", "DB insert failed", e)
                        events.emit(MainUiEvent.ShowSnackbar("Failed to save account"))
                    }
                } catch (e: Exception) {
                    android.util.Log.e("ImageUpload", "decodeAndSaveFromImage failed", e)
                    events.emit(MainUiEvent.ShowSnackbar("Failed to read image: ${e.message}"))
                }
            }
        }
    }

    private suspend fun decodeRawFromImage(context: Context, uri: Uri): String? {
        try {
            android.util.Log.d("ImageUpload", "Opening input stream for uri=$uri")
            val inputStream = context.contentResolver.openInputStream(uri) ?: run {
                android.util.Log.e("ImageUpload", "Could not open input stream for $uri")
                return null
            }
            val options = android.graphics.BitmapFactory.Options().apply { inJustDecodeBounds = true }
            android.graphics.BitmapFactory.decodeStream(inputStream, null, options)
            inputStream.close()

            val maxDimension = 1024
            val sampleSize = maxOf(1, options.outWidth / maxDimension, options.outHeight / maxDimension)
            android.util.Log.d("ImageUpload", "Bitmap decode sampleSize=$sampleSize (w=${options.outWidth} h=${options.outHeight})")
            val decodeOptions = android.graphics.BitmapFactory.Options().apply { inSampleSize = sampleSize }
            val inputStream2 = context.contentResolver.openInputStream(uri) ?: run {
                android.util.Log.e("ImageUpload", "Could not open input stream (2) for $uri")
                return null
            }
            val bitmap = try {
                android.graphics.BitmapFactory.decodeStream(inputStream2, null, decodeOptions)
            } catch (oom: OutOfMemoryError) {
                android.util.Log.e("ImageUpload", "OOM decoding bitmap", oom)
                inputStream2.close()
                return null
            } ?: run {
                android.util.Log.e("ImageUpload", "BitmapFactory returned null for $uri")
                inputStream2.close()
                return null
            }
            inputStream2.close()

            val intArray = IntArray(bitmap.width * bitmap.height)
            bitmap.getPixels(intArray, 0, bitmap.width, 0, 0, bitmap.width, bitmap.height)
            val source = RGBLuminanceSource(bitmap.width, bitmap.height, intArray)

            val hints = mapOf(DecodeHintType.POSSIBLE_FORMATS to listOf(BarcodeFormat.QR_CODE), DecodeHintType.TRY_HARDER to true)

            val rawResult = runCatching {
                val binaryBitmap = BinaryBitmap(HybridBinarizer(source))
                MultiFormatReader().apply { setHints(hints) }.decode(binaryBitmap)
            }.getOrNull() ?: runCatching {
                val binaryBitmap = BinaryBitmap(GlobalHistogramBinarizer(source))
                MultiFormatReader().apply { setHints(hints) }.decode(binaryBitmap)
            }.getOrNull()

            if (rawResult != null) {
                android.util.Log.d("ImageUpload", "ZXing raw text length=${rawResult.text.length}")
                return rawResult.text
            }

            android.util.Log.e("ImageUpload", "ZXing could not find QR in bitmap, trying ML Kit fallback")
            return decodeRawWithMlKit(context, uri)
        } catch (e: Exception) {
            android.util.Log.e("ImageUpload", "decodeRawFromImage exception", e)
            return null
        }
    }

    private suspend fun decodeRawWithMlKit(context: Context, uri: Uri): String? {
        return suspendCancellableCoroutine { cont ->
            val scanner = com.google.mlkit.vision.barcode.BarcodeScanning.getClient(
                com.google.mlkit.vision.barcode.BarcodeScannerOptions.Builder()
                    .setBarcodeFormats(com.google.mlkit.vision.barcode.common.Barcode.FORMAT_QR_CODE)
                    .build()
            )

            val image = try {
                com.google.mlkit.vision.common.InputImage.fromFilePath(context, uri)
            } catch (e: Exception) {
                android.util.Log.e("ImageUpload", "ML Kit could not create InputImage", e)
                scanner.close()
                cont.resume(null)
                return@suspendCancellableCoroutine
            }

            scanner.process(image)
                .addOnSuccessListener { barcodes ->
                    val value = barcodes.firstOrNull { it.rawValue?.startsWith("otpauth://", ignoreCase = true) == true }?.rawValue
                        ?: barcodes.firstOrNull()?.rawValue
                    android.util.Log.d("ImageUpload", "ML Kit fallback decode result present=${value != null}")
                    scanner.close()
                    if (cont.isActive) cont.resume(value)
                }
                .addOnFailureListener { e ->
                    android.util.Log.e("ImageUpload", "ML Kit fallback decode failed", e)
                    scanner.close()
                    if (cont.isActive) cont.resume(null)
                }
        }
    }

    fun onSearchClicked() {
        searchExpanded.update { !it }
    }

    fun onSearchQueryChanged(value: String) {
        searchQuery.value = value
    }

    fun onSettingsClicked(): Unit = Unit

    fun onAddClicked(): Unit = Unit

    fun onCodeClicked(account: AccountUiModel) {
        viewModelScope.launch {
            events.emit(MainUiEvent.CopyToClipboard(account.code))
            events.emit(MainUiEvent.ShowSnackbar("Copied!"))
        }
    }

    fun onDetailsClicked(accountId: Int) {
        deleteTarget.value = deleteTarget.value?.takeIf { it.id == accountId } ?: deleteTarget.value
    }

    fun onDeleteRequested(account: AccountUiModel) {
        deleteTarget.value = account
    }

    fun dismissDeleteSheet() {
        deleteTarget.value = null
    }

    fun confirmDelete() {
        val target = deleteTarget.value ?: return
        viewModelScope.launch {
            deleteAccountUseCase(target.id)
            deleteTarget.value = null
            events.emit(MainUiEvent.ShowSnackbar("Account deleted"))
        }
    }

    fun toggleReorderMode() {
        reorderMode.update { !it }
    }

    fun reorder(newOrderIds: List<Int>) {
        viewModelScope.launch {
            accountRepository.reorder(newOrderIds)
        }
    }

    private fun Account.toUiModel(result: Pair<String, Int>): AccountUiModel {
        val (code, countdown) = result
        return AccountUiModel(
            id = id,
            issuer = issuer,
            accountName = accountName,
            code = code,
            countdown = countdown,
            algorithm = algorithm,
            digits = digits,
            period = period,
            type = type,
            counter = counter,
            position = position,
            createdAt = createdAt,
            iconSlug = iconSlug
        )
    }

    private fun sortComparator(settings: SettingsState): Comparator<Account> = when (settings.sortOrder) {
        "az" -> compareBy(String.CASE_INSENSITIVE_ORDER) { it.issuer.lowercase() }
        "date" -> compareByDescending<Account> { it.createdAt }
        else -> compareBy<Account> { it.position }.thenBy { it.createdAt }
    }
}
