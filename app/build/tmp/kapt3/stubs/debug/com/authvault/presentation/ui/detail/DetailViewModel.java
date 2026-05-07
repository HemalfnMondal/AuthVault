package com.authvault.presentation.ui.detail;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\b\u0007\u0018\u00002\u00020\u0001B\u0017\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\u0002\u0010\u0006J\u0006\u0010\u0010\u001a\u00020\u0011R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u000b0\nX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0017\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u000b0\r\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000f\u00a8\u0006\u0012"}, d2 = {"Lcom/authvault/presentation/ui/detail/DetailViewModel;", "Landroidx/lifecycle/ViewModel;", "savedStateHandle", "Landroidx/lifecycle/SavedStateHandle;", "repository", "Lcom/authvault/data/repository/AccountRepository;", "(Landroidx/lifecycle/SavedStateHandle;Lcom/authvault/data/repository/AccountRepository;)V", "accountId", "", "state", "Lkotlinx/coroutines/flow/MutableStateFlow;", "Lcom/authvault/presentation/ui/detail/DetailUiState;", "uiState", "Lkotlinx/coroutines/flow/StateFlow;", "getUiState", "()Lkotlinx/coroutines/flow/StateFlow;", "toggleSecretVisibility", "", "app_debug"})
@dagger.hilt.android.lifecycle.HiltViewModel()
public final class DetailViewModel extends androidx.lifecycle.ViewModel {
    @org.jetbrains.annotations.NotNull()
    private final com.authvault.data.repository.AccountRepository repository = null;
    private final int accountId = 0;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableStateFlow<com.authvault.presentation.ui.detail.DetailUiState> state = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<com.authvault.presentation.ui.detail.DetailUiState> uiState = null;
    
    @javax.inject.Inject()
    public DetailViewModel(@org.jetbrains.annotations.NotNull()
    androidx.lifecycle.SavedStateHandle savedStateHandle, @org.jetbrains.annotations.NotNull()
    com.authvault.data.repository.AccountRepository repository) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<com.authvault.presentation.ui.detail.DetailUiState> getUiState() {
        return null;
    }
    
    public final void toggleSecretVisibility() {
    }
}