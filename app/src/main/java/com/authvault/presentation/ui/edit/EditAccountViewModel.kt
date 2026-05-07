package com.authvault.presentation.ui.edit

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.authvault.data.repository.AccountRepository
import com.authvault.domain.model.Account
import com.authvault.presentation.ui.common.iconSlugForIssuer
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

data class EditAccountUiState(
    val loading: Boolean = true,
    val accountId: Int = 0,
    val issuer: String = "",
    val accountName: String = "",
    val secretKey: String = "",
    val algorithm: String = "",
    val digits: Int = 6,
    val period: Int = 30,
    val type: String = "TOTP",
    val counter: Long = 0L,
    val createdAt: Long = 0L,
    val iconSlug: String? = null,
    val secretVisible: Boolean = false,
    val secretError: String? = null,
    val showDiscardDialog: Boolean = false,
    val hasChanges: Boolean = false,
    val saveEnabled: Boolean = false
)

sealed interface EditAccountEvent {
    data object Saved : EditAccountEvent
    data class Error(val message: String) : EditAccountEvent
}

@HiltViewModel
class EditAccountViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val repository: AccountRepository
) : ViewModel() {
    private val accountId = savedStateHandle.get<Int>("accountId") ?: 0
    private var originalAccount: Account? = null

    private val state = MutableStateFlow(EditAccountUiState())
    val uiState: StateFlow<EditAccountUiState> = state.asStateFlow()

    private val events = MutableSharedFlow<EditAccountEvent>(extraBufferCapacity = 2)
    val uiEvents: SharedFlow<EditAccountEvent> = events.asSharedFlow()

    init {
        loadAccount()
    }

    private fun loadAccount() {
        viewModelScope.launch {
            val account = repository.getAccount(accountId)
            if (account == null) {
                events.emit(EditAccountEvent.Error("Account not found"))
                state.update { it.copy(loading = false) }
                return@launch
            }
            originalAccount = account
            state.value = EditAccountUiState(
                loading = false,
                accountId = account.id,
                issuer = account.issuer,
                accountName = account.accountName,
                secretKey = account.secretKey,
                algorithm = account.algorithm,
                digits = account.digits,
                period = account.period,
                type = account.type,
                counter = account.counter,
                createdAt = account.createdAt,
                iconSlug = account.iconSlug,
                saveEnabled = false,
                hasChanges = false
            )
        }
    }

    fun onIssuerChanged(value: String) {
        state.update { current ->
            current.copy(
                issuer = value,
                secretError = null
            )
        }
        recomputeFlags()
    }

    fun onAccountNameChanged(value: String) {
        state.update { current ->
            current.copy(
                accountName = value,
                secretError = null
            )
        }
        recomputeFlags()
    }

    fun onSecretChanged(value: String) {
        state.update { current ->
            current.copy(
                secretKey = value.uppercase().replace(" ", ""),
                secretError = null
            )
        }
        recomputeFlags()
    }

    fun toggleSecretVisibility() {
        state.update { it.copy(secretVisible = !it.secretVisible) }
    }

    fun requestDiscardDialog() {
        state.update { it.copy(showDiscardDialog = true) }
    }

    fun dismissDiscardDialog() {
        state.update { it.copy(showDiscardDialog = false) }
    }

    fun discardChanges() {
        state.update { it.copy(showDiscardDialog = false) }
    }

    fun saveChanges() {
        val current = state.value
        if (!current.saveEnabled) return

        val normalizedSecret = normalizeSecret(current.secretKey)
        if (!normalizedSecret.isBase32Secret()) {
            state.update { it.copy(secretError = "Secret key must be valid Base32.") }
            return
        }

        viewModelScope.launch {
            val base = originalAccount ?: return@launch
            val updated = base.copy(
                issuer = current.issuer.trim(),
                accountName = current.accountName.trim(),
                secretKey = normalizedSecret,
                iconSlug = iconSlugForIssuer(current.issuer.trim()) ?: base.iconSlug
            )
            repository.updateAccount(updated)
            events.emit(EditAccountEvent.Saved)
        }
    }

    private fun recomputeFlags() {
        val current = state.value
        val original = originalAccount

        val normalizedSecret = normalizeSecret(current.secretKey)
        val secretValid = normalizedSecret.isBase32Secret()
        val hasChanges = if (original == null) {
            false
        } else {
            original.issuer != current.issuer.trim() ||
                original.accountName != current.accountName.trim() ||
                normalizeSecret(original.secretKey) != normalizedSecret
        }

        val canSave = hasChanges &&
            current.issuer.isNotBlank() &&
            current.accountName.isNotBlank() &&
            secretValid

        state.update {
            it.copy(
                hasChanges = hasChanges,
                saveEnabled = canSave,
                secretError = if (!secretValid && current.secretKey.isNotBlank()) "Secret key must be valid Base32." else null
            )
        }
    }

    private fun normalizeSecret(secret: String): String {
        return secret.replace(" ", "").replace("-", "").uppercase().trimEnd('=')
    }
}

private fun String.isBase32Secret(): Boolean {
    return isNotBlank() && matches(Regex("^[A-Z2-7]+$"))
}
