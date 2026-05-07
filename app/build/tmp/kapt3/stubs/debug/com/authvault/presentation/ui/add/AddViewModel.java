package com.authvault.presentation.ui.add;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000R\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0010\b\u0007\u0018\u00002\u00020\u0001B\u0017\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\u0002\u0010\u0006J\u000e\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u0016J\u000e\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u0017\u001a\u00020\u0016J\u0006\u0010\u001a\u001a\u00020\u001bJ\u000e\u0010\u001c\u001a\u00020\u001b2\u0006\u0010\u001d\u001a\u00020\u0016J\u000e\u0010\u001e\u001a\u00020\u001b2\u0006\u0010\u001d\u001a\u00020\u0016J\u000e\u0010\u001f\u001a\u00020\u001b2\u0006\u0010\u001d\u001a\u00020\u0016J\u000e\u0010 \u001a\u00020\u001b2\u0006\u0010\u001d\u001a\u00020\u0019J\u000e\u0010!\u001a\u00020\u001b2\u0006\u0010\u001d\u001a\u00020\u0016J\u000e\u0010\"\u001a\u00020\u001b2\u0006\u0010\u001d\u001a\u00020\u0019J\u000e\u0010#\u001a\u00020\u001b2\u0006\u0010$\u001a\u00020\u0016J\u000e\u0010%\u001a\u00020\u001b2\u0006\u0010\u001d\u001a\u00020\u0016J\u000e\u0010&\u001a\u00020\u001b2\u0006\u0010\u001d\u001a\u00020\u0016J\u0006\u0010\'\u001a\u00020\u001bJ\u0006\u0010(\u001a\u00020\u001bJ\u0006\u0010)\u001a\u00020\u001bJ\u0006\u0010*\u001a\u00020\u001bR\u0014\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0017\u0010\n\u001a\b\u0012\u0004\u0012\u00020\t0\u000b\u00a2\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0014\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00100\u000fX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0017\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00100\u0012\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014\u00a8\u0006+"}, d2 = {"Lcom/authvault/presentation/ui/add/AddViewModel;", "Landroidx/lifecycle/ViewModel;", "addAccountUseCase", "Lcom/authvault/domain/usecase/AddAccountUseCase;", "accountRepository", "Lcom/authvault/data/repository/AccountRepository;", "(Lcom/authvault/domain/usecase/AddAccountUseCase;Lcom/authvault/data/repository/AccountRepository;)V", "_events", "Lkotlinx/coroutines/flow/MutableSharedFlow;", "Lcom/authvault/presentation/ui/add/AddUiEvent;", "events", "Lkotlinx/coroutines/flow/SharedFlow;", "getEvents", "()Lkotlinx/coroutines/flow/SharedFlow;", "state", "Lkotlinx/coroutines/flow/MutableStateFlow;", "Lcom/authvault/presentation/ui/add/AddUiState;", "uiState", "Lkotlinx/coroutines/flow/StateFlow;", "getUiState", "()Lkotlinx/coroutines/flow/StateFlow;", "autoDetectAlgorithm", "", "secretKey", "autoDetectDigits", "", "clearParsed", "", "onAccountChanged", "value", "onAlgorithmChanged", "onCounterChanged", "onDigitsChanged", "onIssuerChanged", "onPeriodChanged", "onQrDetected", "raw", "onSecretChanged", "onTypeChanged", "prepareManualForConfirm", "saveManualAccount", "saveParsedAccount", "syncManualDefaults", "app_debug"})
@dagger.hilt.android.lifecycle.HiltViewModel()
public final class AddViewModel extends androidx.lifecycle.ViewModel {
    @org.jetbrains.annotations.NotNull()
    private final com.authvault.domain.usecase.AddAccountUseCase addAccountUseCase = null;
    @org.jetbrains.annotations.NotNull()
    private final com.authvault.data.repository.AccountRepository accountRepository = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableStateFlow<com.authvault.presentation.ui.add.AddUiState> state = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<com.authvault.presentation.ui.add.AddUiState> uiState = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableSharedFlow<com.authvault.presentation.ui.add.AddUiEvent> _events = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.SharedFlow<com.authvault.presentation.ui.add.AddUiEvent> events = null;
    
    @javax.inject.Inject()
    public AddViewModel(@org.jetbrains.annotations.NotNull()
    com.authvault.domain.usecase.AddAccountUseCase addAccountUseCase, @org.jetbrains.annotations.NotNull()
    com.authvault.data.repository.AccountRepository accountRepository) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<com.authvault.presentation.ui.add.AddUiState> getUiState() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.SharedFlow<com.authvault.presentation.ui.add.AddUiEvent> getEvents() {
        return null;
    }
    
    public final void onIssuerChanged(@org.jetbrains.annotations.NotNull()
    java.lang.String value) {
    }
    
    public final void onAccountChanged(@org.jetbrains.annotations.NotNull()
    java.lang.String value) {
    }
    
    public final void onSecretChanged(@org.jetbrains.annotations.NotNull()
    java.lang.String value) {
    }
    
    public final void onAlgorithmChanged(@org.jetbrains.annotations.NotNull()
    java.lang.String value) {
    }
    
    public final void onTypeChanged(@org.jetbrains.annotations.NotNull()
    java.lang.String value) {
    }
    
    public final void onDigitsChanged(int value) {
    }
    
    public final void onPeriodChanged(int value) {
    }
    
    public final void onCounterChanged(@org.jetbrains.annotations.NotNull()
    java.lang.String value) {
    }
    
    public final void onQrDetected(@org.jetbrains.annotations.NotNull()
    java.lang.String raw) {
    }
    
    public final void clearParsed() {
    }
    
    public final void syncManualDefaults() {
    }
    
    public final void saveParsedAccount() {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String autoDetectAlgorithm(@org.jetbrains.annotations.NotNull()
    java.lang.String secretKey) {
        return null;
    }
    
    public final int autoDetectDigits(@org.jetbrains.annotations.NotNull()
    java.lang.String secretKey) {
        return 0;
    }
    
    public final void prepareManualForConfirm() {
    }
    
    public final void saveManualAccount() {
    }
}