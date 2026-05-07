package com.authvault.presentation.ui.update;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000L\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0002\b\u0007\u0018\u00002\u00020\u0001B\u0017\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\u0002\u0010\u0006J\u0006\u0010\u0015\u001a\u00020\u0016J\u0006\u0010\u0017\u001a\u00020\u0016J\u0006\u0010\u0018\u001a\u00020\u0016J\u0016\u0010\u0019\u001a\u00020\u00162\u0006\u0010\u001a\u001a\u00020\u001bH\u0082@\u00a2\u0006\u0002\u0010\u001cR\u0014\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\n\u001a\b\u0012\u0004\u0012\u00020\f0\u000bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0017\u0010\r\u001a\b\u0012\u0004\u0012\u00020\t0\u000e\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u0017\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\f0\u0012\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u001d"}, d2 = {"Lcom/authvault/presentation/ui/update/UpdateViewModel;", "Landroidx/lifecycle/ViewModel;", "updateChecker", "Lcom/authvault/data/update/UpdateChecker;", "settingsRepository", "Lcom/authvault/data/repository/SettingsRepository;", "(Lcom/authvault/data/update/UpdateChecker;Lcom/authvault/data/repository/SettingsRepository;)V", "events", "Lkotlinx/coroutines/flow/MutableSharedFlow;", "Lcom/authvault/presentation/ui/update/UpdateUiEvent;", "state", "Lkotlinx/coroutines/flow/MutableStateFlow;", "Lcom/authvault/presentation/ui/update/UpdateUiState;", "uiEvents", "Lkotlinx/coroutines/flow/SharedFlow;", "getUiEvents", "()Lkotlinx/coroutines/flow/SharedFlow;", "uiState", "Lkotlinx/coroutines/flow/StateFlow;", "getUiState", "()Lkotlinx/coroutines/flow/StateFlow;", "checkForUpdatesIfDue", "", "checkForUpdatesNow", "dismissUpdateDialog", "performCheck", "manual", "", "(ZLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "app_debug"})
@dagger.hilt.android.lifecycle.HiltViewModel()
public final class UpdateViewModel extends androidx.lifecycle.ViewModel {
    @org.jetbrains.annotations.NotNull()
    private final com.authvault.data.update.UpdateChecker updateChecker = null;
    @org.jetbrains.annotations.NotNull()
    private final com.authvault.data.repository.SettingsRepository settingsRepository = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableStateFlow<com.authvault.presentation.ui.update.UpdateUiState> state = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<com.authvault.presentation.ui.update.UpdateUiState> uiState = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableSharedFlow<com.authvault.presentation.ui.update.UpdateUiEvent> events = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.SharedFlow<com.authvault.presentation.ui.update.UpdateUiEvent> uiEvents = null;
    
    @javax.inject.Inject()
    public UpdateViewModel(@org.jetbrains.annotations.NotNull()
    com.authvault.data.update.UpdateChecker updateChecker, @org.jetbrains.annotations.NotNull()
    com.authvault.data.repository.SettingsRepository settingsRepository) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<com.authvault.presentation.ui.update.UpdateUiState> getUiState() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.SharedFlow<com.authvault.presentation.ui.update.UpdateUiEvent> getUiEvents() {
        return null;
    }
    
    public final void checkForUpdatesIfDue() {
    }
    
    public final void checkForUpdatesNow() {
    }
    
    public final void dismissUpdateDialog() {
    }
    
    private final java.lang.Object performCheck(boolean manual, kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
}