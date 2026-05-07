package com.authvault.presentation.ui.settings;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0012\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u000e\n\u0002\u0018\u0002\n\u0000\b\u0007\u0018\u00002\u00020\u0001B\'\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u00a2\u0006\u0002\u0010\nJ\u0016\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u0018H\u0086@\u00a2\u0006\u0002\u0010\u0019J&\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\u001c\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u001d\u001a\u00020\u001eH\u0086@\u00a2\u0006\u0002\u0010\u001fJ\u001e\u0010 \u001a\u00020\u001b2\u0006\u0010\u001c\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u0018H\u0086@\u00a2\u0006\u0002\u0010!J\u000e\u0010\"\u001a\u00020#2\u0006\u0010$\u001a\u00020\u001eJ\u000e\u0010%\u001a\u00020#2\u0006\u0010$\u001a\u00020\u001eJ\u000e\u0010&\u001a\u00020#2\u0006\u0010\'\u001a\u00020\u001bJ\u000e\u0010(\u001a\u00020#2\u0006\u0010)\u001a\u00020\u001bJ\u000e\u0010*\u001a\u00020#2\u0006\u0010+\u001a\u00020\u0018J\u000e\u0010,\u001a\u00020#2\u0006\u0010-\u001a\u00020\u001bJ\u000e\u0010.\u001a\u00020#2\u0006\u0010/\u001a\u00020\u0018J\u001c\u00100\u001a\u00020#2\u0012\u00101\u001a\u000e\u0012\u0004\u0012\u00020\u0010\u0012\u0004\u0012\u00020\u001002H\u0002R\u0014\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\r0\fX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0017\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00100\u000f\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u0017\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\r0\u000f\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0012\u00a8\u00063"}, d2 = {"Lcom/authvault/presentation/ui/settings/SettingsViewModel;", "Landroidx/lifecycle/ViewModel;", "settingsRepository", "Lcom/authvault/data/repository/SettingsRepository;", "exportBackupUseCase", "Lcom/authvault/domain/usecase/ExportBackupUseCase;", "importBackupUseCase", "Lcom/authvault/domain/usecase/ImportBackupUseCase;", "backupRepository", "Lcom/authvault/data/repository/BackupRepository;", "(Lcom/authvault/data/repository/SettingsRepository;Lcom/authvault/domain/usecase/ExportBackupUseCase;Lcom/authvault/domain/usecase/ImportBackupUseCase;Lcom/authvault/data/repository/BackupRepository;)V", "_uiState", "Lkotlinx/coroutines/flow/MutableStateFlow;", "Lcom/authvault/presentation/ui/settings/SettingsUiState;", "settingsState", "Lkotlinx/coroutines/flow/StateFlow;", "Lcom/authvault/data/repository/SettingsState;", "getSettingsState", "()Lkotlinx/coroutines/flow/StateFlow;", "uiState", "getUiState", "exportBackup", "", "password", "", "(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "importBackup", "", "bytes", "replaceAll", "", "([BLjava/lang/String;ZLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "previewImport", "([BLjava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "setAppLock", "", "enabled", "setAutoClearClipboard", "setAutoLockTimeout", "minutes", "setClipboardDelay", "seconds", "setDefaultAlgorithm", "algorithm", "setDefaultDigits", "digits", "setSortOrder", "order", "update", "transform", "Lkotlin/Function1;", "app_debug"})
@dagger.hilt.android.lifecycle.HiltViewModel()
public final class SettingsViewModel extends androidx.lifecycle.ViewModel {
    @org.jetbrains.annotations.NotNull()
    private final com.authvault.data.repository.SettingsRepository settingsRepository = null;
    @org.jetbrains.annotations.NotNull()
    private final com.authvault.domain.usecase.ExportBackupUseCase exportBackupUseCase = null;
    @org.jetbrains.annotations.NotNull()
    private final com.authvault.domain.usecase.ImportBackupUseCase importBackupUseCase = null;
    @org.jetbrains.annotations.NotNull()
    private final com.authvault.data.repository.BackupRepository backupRepository = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<com.authvault.data.repository.SettingsState> settingsState = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableStateFlow<com.authvault.presentation.ui.settings.SettingsUiState> _uiState = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<com.authvault.presentation.ui.settings.SettingsUiState> uiState = null;
    
    @javax.inject.Inject()
    public SettingsViewModel(@org.jetbrains.annotations.NotNull()
    com.authvault.data.repository.SettingsRepository settingsRepository, @org.jetbrains.annotations.NotNull()
    com.authvault.domain.usecase.ExportBackupUseCase exportBackupUseCase, @org.jetbrains.annotations.NotNull()
    com.authvault.domain.usecase.ImportBackupUseCase importBackupUseCase, @org.jetbrains.annotations.NotNull()
    com.authvault.data.repository.BackupRepository backupRepository) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<com.authvault.data.repository.SettingsState> getSettingsState() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<com.authvault.presentation.ui.settings.SettingsUiState> getUiState() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object exportBackup(@org.jetbrains.annotations.NotNull()
    java.lang.String password, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super byte[]> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object previewImport(@org.jetbrains.annotations.NotNull()
    byte[] bytes, @org.jetbrains.annotations.NotNull()
    java.lang.String password, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.lang.Integer> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object importBackup(@org.jetbrains.annotations.NotNull()
    byte[] bytes, @org.jetbrains.annotations.NotNull()
    java.lang.String password, boolean replaceAll, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.lang.Integer> $completion) {
        return null;
    }
    
    public final void setAppLock(boolean enabled) {
    }
    
    public final void setAutoLockTimeout(int minutes) {
    }
    
    public final void setAutoClearClipboard(boolean enabled) {
    }
    
    public final void setClipboardDelay(int seconds) {
    }
    
    public final void setSortOrder(@org.jetbrains.annotations.NotNull()
    java.lang.String order) {
    }
    
    public final void setDefaultAlgorithm(@org.jetbrains.annotations.NotNull()
    java.lang.String algorithm) {
    }
    
    public final void setDefaultDigits(int digits) {
    }
    
    private final void update(kotlin.jvm.functions.Function1<? super com.authvault.data.repository.SettingsState, com.authvault.data.repository.SettingsState> transform) {
    }
}