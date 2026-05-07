package com.authvault.presentation.ui.edit;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000X\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\n\b\u0007\u0018\u00002\u00020\u0001B\u0017\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\u0002\u0010\u0006J\u0006\u0010\u0019\u001a\u00020\u001aJ\u0006\u0010\u001b\u001a\u00020\u001aJ\b\u0010\u001c\u001a\u00020\u001aH\u0002J\u0010\u0010\u001d\u001a\u00020\u001e2\u0006\u0010\u001f\u001a\u00020\u001eH\u0002J\u000e\u0010 \u001a\u00020\u001a2\u0006\u0010!\u001a\u00020\u001eJ\u000e\u0010\"\u001a\u00020\u001a2\u0006\u0010!\u001a\u00020\u001eJ\u000e\u0010#\u001a\u00020\u001a2\u0006\u0010!\u001a\u00020\u001eJ\b\u0010$\u001a\u00020\u001aH\u0002J\u0006\u0010%\u001a\u00020\u001aJ\u0006\u0010&\u001a\u00020\u001aJ\u0006\u0010\'\u001a\u00020\u001aR\u000e\u0010\u0007\u001a\u00020\bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u000b0\nX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0010\u0010\f\u001a\u0004\u0018\u00010\rX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00100\u000fX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0017\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u000b0\u0012\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014R\u0017\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u00100\u0016\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0018\u00a8\u0006("}, d2 = {"Lcom/authvault/presentation/ui/edit/EditAccountViewModel;", "Landroidx/lifecycle/ViewModel;", "savedStateHandle", "Landroidx/lifecycle/SavedStateHandle;", "repository", "Lcom/authvault/data/repository/AccountRepository;", "(Landroidx/lifecycle/SavedStateHandle;Lcom/authvault/data/repository/AccountRepository;)V", "accountId", "", "events", "Lkotlinx/coroutines/flow/MutableSharedFlow;", "Lcom/authvault/presentation/ui/edit/EditAccountEvent;", "originalAccount", "Lcom/authvault/domain/model/Account;", "state", "Lkotlinx/coroutines/flow/MutableStateFlow;", "Lcom/authvault/presentation/ui/edit/EditAccountUiState;", "uiEvents", "Lkotlinx/coroutines/flow/SharedFlow;", "getUiEvents", "()Lkotlinx/coroutines/flow/SharedFlow;", "uiState", "Lkotlinx/coroutines/flow/StateFlow;", "getUiState", "()Lkotlinx/coroutines/flow/StateFlow;", "discardChanges", "", "dismissDiscardDialog", "loadAccount", "normalizeSecret", "", "secret", "onAccountNameChanged", "value", "onIssuerChanged", "onSecretChanged", "recomputeFlags", "requestDiscardDialog", "saveChanges", "toggleSecretVisibility", "app_debug"})
@dagger.hilt.android.lifecycle.HiltViewModel()
public final class EditAccountViewModel extends androidx.lifecycle.ViewModel {
    @org.jetbrains.annotations.NotNull()
    private final com.authvault.data.repository.AccountRepository repository = null;
    private final int accountId = 0;
    @org.jetbrains.annotations.Nullable()
    private com.authvault.domain.model.Account originalAccount;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableStateFlow<com.authvault.presentation.ui.edit.EditAccountUiState> state = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<com.authvault.presentation.ui.edit.EditAccountUiState> uiState = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableSharedFlow<com.authvault.presentation.ui.edit.EditAccountEvent> events = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.SharedFlow<com.authvault.presentation.ui.edit.EditAccountEvent> uiEvents = null;
    
    @javax.inject.Inject()
    public EditAccountViewModel(@org.jetbrains.annotations.NotNull()
    androidx.lifecycle.SavedStateHandle savedStateHandle, @org.jetbrains.annotations.NotNull()
    com.authvault.data.repository.AccountRepository repository) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<com.authvault.presentation.ui.edit.EditAccountUiState> getUiState() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.SharedFlow<com.authvault.presentation.ui.edit.EditAccountEvent> getUiEvents() {
        return null;
    }
    
    private final void loadAccount() {
    }
    
    public final void onIssuerChanged(@org.jetbrains.annotations.NotNull()
    java.lang.String value) {
    }
    
    public final void onAccountNameChanged(@org.jetbrains.annotations.NotNull()
    java.lang.String value) {
    }
    
    public final void onSecretChanged(@org.jetbrains.annotations.NotNull()
    java.lang.String value) {
    }
    
    public final void toggleSecretVisibility() {
    }
    
    public final void requestDiscardDialog() {
    }
    
    public final void dismissDiscardDialog() {
    }
    
    public final void discardChanges() {
    }
    
    public final void saveChanges() {
    }
    
    private final void recomputeFlags() {
    }
    
    private final java.lang.String normalizeSecret(java.lang.String secret) {
        return null;
    }
}