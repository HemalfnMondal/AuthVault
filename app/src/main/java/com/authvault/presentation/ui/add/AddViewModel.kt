package com.authvault.presentation.ui.add

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.authvault.data.repository.AccountRepository
import com.authvault.domain.model.Account
import com.authvault.domain.usecase.AddAccountUseCase
import com.authvault.presentation.ui.common.OtpUriParser
import org.apache.commons.codec.binary.Base32
import com.authvault.presentation.ui.common.ParsedOtpUri
import com.authvault.presentation.ui.common.iconSlugForIssuer
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

data class ManualEntryState(
    val issuer: String = "",
    val accountName: String = "",
    val secretKey: String = "",
    val algorithm: String = "SHA1",
    val type: String = "TOTP",
    val digits: Int = 6,
    val period: Int = 30,
    val counter: String = "0",
    val secretError: String? = null
) {
    val canSave: Boolean
        get() = issuer.isNotBlank() && accountName.isNotBlank() && secretKey.isBase32Secret()
}

data class AddUiState(
    val parsedAccount: ParsedOtpUri? = null,
    val manual: ManualEntryState = ManualEntryState(),
    val errorMessage: String? = null,
    val busy: Boolean = false
)

sealed interface AddUiEvent {
    data object Saved : AddUiEvent
    data class Error(val message: String) : AddUiEvent
}

@HiltViewModel
class AddViewModel @Inject constructor(
    private val addAccountUseCase: AddAccountUseCase,
    private val accountRepository: AccountRepository
) : ViewModel() {
    private val state = MutableStateFlow(AddUiState())
    val uiState: StateFlow<AddUiState> = state.asStateFlow()
    private val _events = MutableSharedFlow<AddUiEvent>(extraBufferCapacity = 4)
    val events: SharedFlow<AddUiEvent> = _events

    fun onIssuerChanged(value: String) = state.update { it.copy(manual = it.manual.copy(issuer = value, secretError = null)) }
    fun onAccountChanged(value: String) = state.update { it.copy(manual = it.manual.copy(accountName = value)) }
    fun onSecretChanged(value: String) = state.update { it.copy(manual = it.manual.copy(secretKey = value.uppercase().replace(" ", ""), secretError = null)) }
    fun onAlgorithmChanged(value: String) = state.update { it.copy(manual = it.manual.copy(algorithm = value)) }
    fun onTypeChanged(value: String) = state.update { it.copy(manual = it.manual.copy(type = value)) }
    fun onDigitsChanged(value: Int) = state.update { it.copy(manual = it.manual.copy(digits = value)) }
    fun onPeriodChanged(value: Int) = state.update { it.copy(manual = it.manual.copy(period = value)) }
    fun onCounterChanged(value: String) = state.update { it.copy(manual = it.manual.copy(counter = value.filter { ch -> ch.isDigit() })) }

    fun onQrDetected(raw: String) {
        runCatching { OtpUriParser.parse(raw) }
            .onSuccess { parsed -> state.update { it.copy(parsedAccount = parsed, errorMessage = null) } }
            .onFailure { throwable -> state.update { it.copy(errorMessage = throwable.message ?: "Could not parse QR code") } }
    }

    fun clearParsed() {
        state.update { it.copy(parsedAccount = null, errorMessage = null) }
    }

    fun syncManualDefaults() {
        val manual = state.value.manual
        state.update {
            it.copy(
                manual = manual.copy(
                    secretError = null
                )
            )
        }
    }

    fun saveParsedAccount() {
        val parsed = state.value.parsedAccount ?: return
        viewModelScope.launch {
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
            addAccountUseCase(account)
            _events.emit(AddUiEvent.Saved)
        }
    }

    fun autoDetectAlgorithm(secretKey: String): String {
        val cleanSecret = secretKey.replace(" ", "").replace("-", "").uppercase().trimEnd('=')
        return try {
            val keyBytes = Base32().decode(cleanSecret)
            when {
                keyBytes.size >= 64 -> "SHA512"
                keyBytes.size >= 32 -> "SHA256"
                else -> "SHA1"
            }
        } catch (e: Exception) {
            "SHA1"
        }
    }

    fun autoDetectDigits(secretKey: String): Int {
        val cleanSecret = secretKey.replace(" ", "").replace("-", "").uppercase().trimEnd('=')
        return try {
            val keyBytes = Base32().decode(cleanSecret)
            when {
                keyBytes.size >= 64 -> 8
                else -> 6
            }
        } catch (e: Exception) {
            6
        }
    }

    fun prepareManualForConfirm() {
        val current = state.value.manual
        val secret = current.secretKey.trim()
        val secretError = if (!secret.isBase32Secret()) "Secret key must be valid Base32." else null
        if (current.issuer.isBlank() || current.accountName.isBlank() || secretError != null) {
            state.update { it.copy(manual = current.copy(secretError = secretError ?: current.secretError)) }
            viewModelScope.launch { _events.emit(AddUiEvent.Error(secretError ?: "Please complete all required fields.")) }
            return
        }
        val algorithm = autoDetectAlgorithm(secret)
        val digits = autoDetectDigits(secret)
        val period = 30
        val parsed = ParsedOtpUri(
            issuer = current.issuer.trim(),
            accountName = current.accountName.trim(),
            secretKey = secret.replace(" ", "").replace("-", "").uppercase().trimEnd('='),
            algorithm = algorithm,
            digits = digits,
            period = period,
            type = current.type,
            counter = current.counter.toLongOrNull() ?: 0L,
            iconSlug = iconSlugForIssuer(current.issuer)
        )
        state.update { it.copy(parsedAccount = parsed, errorMessage = null) }
    }

    fun saveManualAccount() {
        val current = state.value.manual
        val secretError = if (!current.secretKey.isBase32Secret()) "Secret key must be valid Base32." else null
        if (current.issuer.isBlank() || current.accountName.isBlank() || secretError != null) {
            state.update { it.copy(manual = current.copy(secretError = secretError ?: current.secretError)) }
            viewModelScope.launch {
                _events.emit(AddUiEvent.Error(secretError ?: "Please complete all required fields."))
            }
            return
        }
        viewModelScope.launch {
            val account = Account(
                id = 0,
                issuer = current.issuer.trim(),
                accountName = current.accountName.trim(),
                secretKey = current.secretKey.trim(),
                algorithm = current.algorithm,
                digits = current.digits,
                period = if (current.type == "TOTP") current.period else 30,
                type = current.type,
                counter = current.counter.toLongOrNull() ?: 0L,
                position = 0,
                createdAt = System.currentTimeMillis(),
                iconSlug = iconSlugForIssuer(current.issuer)
            )
            addAccountUseCase(account)
            _events.emit(AddUiEvent.Saved)
        }
    }
}

private fun String.isBase32Secret(): Boolean {
    val normalized = uppercase().replace(" ", "")
    return normalized.isNotBlank() && normalized.matches(Regex("^[A-Z2-7]+=*$"))
}
