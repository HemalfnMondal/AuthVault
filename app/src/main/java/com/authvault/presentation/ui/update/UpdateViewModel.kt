package com.authvault.presentation.ui.update

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.authvault.data.repository.SettingsRepository
import com.authvault.data.update.UpdateChecker
import com.authvault.data.update.UpdateCheckOutcome
import com.authvault.data.update.UpdateInfo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

private const val ONE_DAY_MILLIS = 24L * 60L * 60L * 1000L

data class UpdateUiState(
    val updateInfo: UpdateInfo? = null
)

sealed interface UpdateUiEvent {
    data class ShowSnackbar(val message: String) : UpdateUiEvent
}

@HiltViewModel
class UpdateViewModel @Inject constructor(
    private val updateChecker: UpdateChecker,
    private val settingsRepository: SettingsRepository
) : ViewModel() {
    private val state = MutableStateFlow(UpdateUiState())
    val uiState: StateFlow<UpdateUiState> = state.asStateFlow()

    private val events = MutableSharedFlow<UpdateUiEvent>(extraBufferCapacity = 2)
    val uiEvents: SharedFlow<UpdateUiEvent> = events.asSharedFlow()

    fun checkForUpdatesIfDue() {
        viewModelScope.launch {
            val settings = settingsRepository.state.first()
            val now = System.currentTimeMillis()
            if (now - settings.lastUpdateCheckMillis < ONE_DAY_MILLIS) return@launch
            performCheck(manual = false)
        }
    }

    fun checkForUpdatesNow() {
        viewModelScope.launch {
            performCheck(manual = true)
        }
    }

    fun dismissUpdateDialog() {
        state.update { it.copy(updateInfo = null) }
    }

    private suspend fun performCheck(manual: Boolean) {
        val now = System.currentTimeMillis()
        when (val outcome = updateChecker.checkForUpdateOutcome()) {
            is UpdateCheckOutcome.Available -> {
                state.update { it.copy(updateInfo = outcome.info) }
                settingsRepository.update { current -> current.copy(lastUpdateCheckMillis = now) }
            }
            UpdateCheckOutcome.Latest -> {
                settingsRepository.update { current -> current.copy(lastUpdateCheckMillis = now) }
                if (manual) {
                    events.emit(UpdateUiEvent.ShowSnackbar("You are on the latest version"))
                }
            }
            UpdateCheckOutcome.Error -> {
                if (manual) {
                    events.emit(UpdateUiEvent.ShowSnackbar("Could not check for updates. Check your connection."))
                }
            }
        }
    }
}
