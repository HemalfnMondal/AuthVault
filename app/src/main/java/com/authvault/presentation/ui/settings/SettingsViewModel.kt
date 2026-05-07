package com.authvault.presentation.ui.settings

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.authvault.data.repository.BackupRepository
import com.authvault.data.repository.SettingsRepository
import com.authvault.data.repository.SettingsState
import com.authvault.domain.usecase.ExportBackupUseCase
import com.authvault.domain.usecase.ImportBackupUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

data class SettingsUiState(
    val settings: SettingsState = SettingsState(),
    val message: String? = null
)

@HiltViewModel
class SettingsViewModel @Inject constructor(
    private val settingsRepository: SettingsRepository,
    private val exportBackupUseCase: ExportBackupUseCase,
    private val importBackupUseCase: ImportBackupUseCase,
    private val backupRepository: BackupRepository
) : ViewModel() {
    val settingsState: StateFlow<SettingsState> = settingsRepository.state.stateIn(
        viewModelScope,
        kotlinx.coroutines.flow.SharingStarted.WhileSubscribed(5_000),
        SettingsState()
    )

    private val _uiState = MutableStateFlow(SettingsUiState())
    val uiState: StateFlow<SettingsUiState> = _uiState.asStateFlow()

    suspend fun exportBackup(password: String): ByteArray = exportBackupUseCase(password)

    suspend fun previewImport(bytes: ByteArray, password: String): Int = backupRepository.importBackup(bytes, password).size

    suspend fun importBackup(bytes: ByteArray, password: String, replaceAll: Boolean): Int =
        importBackupUseCase(bytes, password, replaceAll)

    fun setAppLock(enabled: Boolean) = update { it.copy(appLockEnabled = enabled) }
    fun setAutoLockTimeout(minutes: Int) = update { it.copy(autoLockTimeoutMinutes = minutes) }
    fun setAutoClearClipboard(enabled: Boolean) = update { it.copy(autoClearClipboard = enabled) }
    fun setClipboardDelay(seconds: Int) = update { it.copy(clipboardClearDelaySeconds = seconds) }
    fun setSortOrder(order: String) = update { it.copy(sortOrder = order) }
    fun setDefaultAlgorithm(algorithm: String) = update { it.copy(defaultAlgorithm = algorithm) }
    fun setDefaultDigits(digits: Int) = update { it.copy(defaultDigits = digits) }

    private fun update(transform: (SettingsState) -> SettingsState) {
        viewModelScope.launch {
            settingsRepository.update { current -> transform(current) }
        }
    }
}
