package com.authvault.presentation.ui.detail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.authvault.data.repository.AccountRepository
import com.authvault.domain.model.Account
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

data class DetailUiState(
    val account: Account? = null,
    val secretVisible: Boolean = false
)

@HiltViewModel
class DetailViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val repository: AccountRepository
) : ViewModel() {
    private val accountId = savedStateHandle.get<Int>("accountId") ?: 0
    private val state = MutableStateFlow(DetailUiState())
    val uiState: StateFlow<DetailUiState> = state.asStateFlow()

    init {
        viewModelScope.launch {
            state.value = state.value.copy(account = repository.getAccount(accountId))
        }
    }

    fun toggleSecretVisibility() {
        state.value = state.value.copy(secretVisible = !state.value.secretVisible)
    }
}
