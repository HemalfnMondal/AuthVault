package com.authvault.presentation.ui.main;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u00c0\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\b\u0007\u0018\u00002\u00020\u0001B/\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\u000b\u00a2\u0006\u0002\u0010\fJ\u0006\u0010)\u001a\u00020\u000fJ\u0016\u0010*\u001a\u00020\u000f2\u0006\u0010+\u001a\u00020,2\u0006\u0010-\u001a\u00020.JF\u0010/\u001a\u00020\u000f2\u0006\u00100\u001a\u00020!26\u00101\u001a2\u0012\u0013\u0012\u00110\u001c\u00a2\u0006\f\b3\u0012\b\b4\u0012\u0004\b\b(5\u0012\u0013\u0012\u00110!\u00a2\u0006\f\b3\u0012\b\b4\u0012\u0004\b\b(6\u0012\u0004\u0012\u00020\u000f02J \u00107\u001a\u0004\u0018\u00010!2\u0006\u0010+\u001a\u00020,2\u0006\u0010-\u001a\u00020.H\u0082@\u00a2\u0006\u0002\u00108J \u00109\u001a\u0004\u0018\u00010!2\u0006\u0010+\u001a\u00020,2\u0006\u0010-\u001a\u00020.H\u0082@\u00a2\u0006\u0002\u00108J\u0006\u0010:\u001a\u00020\u000fJ\f\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u00160\u0015J\u0006\u0010;\u001a\u00020\u000fJ\u000e\u0010<\u001a\u00020\u000f2\u0006\u0010=\u001a\u00020\u0013J\u000e\u0010>\u001a\u00020\u000f2\u0006\u0010=\u001a\u00020\u0013J\u000e\u0010?\u001a\u00020\u000f2\u0006\u0010@\u001a\u00020AJ\u0006\u0010B\u001a\u00020\u000fJ\u000e\u0010C\u001a\u00020\u000f2\u0006\u0010D\u001a\u00020!J\u0006\u0010E\u001a\u00020\u000fJ\u0012\u0010F\u001a\u0004\u0018\u00010G2\u0006\u0010H\u001a\u00020!H\u0002J\u0014\u0010I\u001a\u00020\u000f2\f\u0010J\u001a\b\u0012\u0004\u0012\u00020A0KJ\u0006\u0010L\u001a\u00020\u000fJ\u0006\u0010M\u001a\u00020\u000fJ \u0010N\u001a\u0012\u0012\u0004\u0012\u00020P0Oj\b\u0012\u0004\u0012\u00020P`Q2\u0006\u0010R\u001a\u00020SH\u0002J\u0006\u0010T\u001a\u00020\u000fJ \u0010U\u001a\u00020\u0013*\u00020P2\u0012\u0010V\u001a\u000e\u0012\u0004\u0012\u00020!\u0012\u0004\u0012\u00020A0WH\u0002R\u0014\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u000f0\u000eX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u000f0\u000eX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0016\u0010\u0011\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00130\u0012X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u00160\u0015X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0017\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u000f0\u0018\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u001aR\u0014\u0010\u001b\u001a\b\u0012\u0004\u0012\u00020\u001c0\u0012X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0017\u0010\u001d\u001a\b\u0012\u0004\u0012\u00020\u000f0\u0018\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001e\u0010\u001aR\u0014\u0010\u001f\u001a\b\u0012\u0004\u0012\u00020\u001c0\u0012X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010 \u001a\b\u0012\u0004\u0012\u00020!0\u0012X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\"\u001a\b\u0012\u0004\u0012\u00020#0\u0018X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0017\u0010$\u001a\b\u0012\u0004\u0012\u00020&0%\u00a2\u0006\b\n\u0000\u001a\u0004\b\'\u0010(\u00a8\u0006X"}, d2 = {"Lcom/authvault/presentation/ui/main/MainViewModel;", "Landroidx/lifecycle/ViewModel;", "getAccountsUseCase", "Lcom/authvault/domain/usecase/GetAccountsUseCase;", "generateCodeUseCase", "Lcom/authvault/domain/usecase/GenerateCodeUseCase;", "deleteAccountUseCase", "Lcom/authvault/domain/usecase/DeleteAccountUseCase;", "accountRepository", "Lcom/authvault/data/repository/AccountRepository;", "settingsRepository", "Lcom/authvault/data/repository/SettingsRepository;", "(Lcom/authvault/domain/usecase/GetAccountsUseCase;Lcom/authvault/domain/usecase/GenerateCodeUseCase;Lcom/authvault/domain/usecase/DeleteAccountUseCase;Lcom/authvault/data/repository/AccountRepository;Lcom/authvault/data/repository/SettingsRepository;)V", "_imagePickerRequests", "Lkotlinx/coroutines/channels/Channel;", "", "_scanRequests", "deleteTarget", "Lkotlinx/coroutines/flow/MutableStateFlow;", "Lcom/authvault/presentation/model/AccountUiModel;", "events", "Lkotlinx/coroutines/flow/MutableSharedFlow;", "Lcom/authvault/presentation/ui/main/MainUiEvent;", "imagePickerRequests", "Lkotlinx/coroutines/flow/Flow;", "getImagePickerRequests", "()Lkotlinx/coroutines/flow/Flow;", "reorderMode", "", "scanRequests", "getScanRequests", "searchExpanded", "searchQuery", "", "ticker", "", "uiState", "Lkotlinx/coroutines/flow/StateFlow;", "Lcom/authvault/presentation/ui/main/MainUiState;", "getUiState", "()Lkotlinx/coroutines/flow/StateFlow;", "confirmDelete", "decodeAndSaveFromImage", "context", "Landroid/content/Context;", "uri", "Landroid/net/Uri;", "decodeAndSaveFromQr", "rawUri", "onResult", "Lkotlin/Function2;", "Lkotlin/ParameterName;", "name", "success", "message", "decodeRawFromImage", "(Landroid/content/Context;Landroid/net/Uri;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "decodeRawWithMlKit", "dismissDeleteSheet", "onAddClicked", "onCodeClicked", "account", "onDeleteRequested", "onDetailsClicked", "accountId", "", "onSearchClicked", "onSearchQueryChanged", "value", "onSettingsClicked", "parseOtpAuthUri", "Lcom/authvault/presentation/ui/common/ParsedOtpUri;", "uriStr", "reorder", "newOrderIds", "", "requestImagePick", "requestScan", "sortComparator", "Ljava/util/Comparator;", "Lcom/authvault/domain/model/Account;", "Lkotlin/Comparator;", "settings", "Lcom/authvault/data/repository/SettingsState;", "toggleReorderMode", "toUiModel", "result", "Lkotlin/Pair;", "app_debug"})
@dagger.hilt.android.lifecycle.HiltViewModel()
public final class MainViewModel extends androidx.lifecycle.ViewModel {
    @org.jetbrains.annotations.NotNull()
    private final com.authvault.domain.usecase.GenerateCodeUseCase generateCodeUseCase = null;
    @org.jetbrains.annotations.NotNull()
    private final com.authvault.domain.usecase.DeleteAccountUseCase deleteAccountUseCase = null;
    @org.jetbrains.annotations.NotNull()
    private final com.authvault.data.repository.AccountRepository accountRepository = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableStateFlow<java.lang.String> searchQuery = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableStateFlow<java.lang.Boolean> searchExpanded = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableStateFlow<java.lang.Boolean> reorderMode = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableStateFlow<com.authvault.presentation.model.AccountUiModel> deleteTarget = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableSharedFlow<com.authvault.presentation.ui.main.MainUiEvent> events = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.channels.Channel<kotlin.Unit> _imagePickerRequests = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.Flow<kotlin.Unit> imagePickerRequests = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.channels.Channel<kotlin.Unit> _scanRequests = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.Flow<kotlin.Unit> scanRequests = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.Flow<java.lang.Long> ticker = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<com.authvault.presentation.ui.main.MainUiState> uiState = null;
    
    @javax.inject.Inject()
    public MainViewModel(@org.jetbrains.annotations.NotNull()
    com.authvault.domain.usecase.GetAccountsUseCase getAccountsUseCase, @org.jetbrains.annotations.NotNull()
    com.authvault.domain.usecase.GenerateCodeUseCase generateCodeUseCase, @org.jetbrains.annotations.NotNull()
    com.authvault.domain.usecase.DeleteAccountUseCase deleteAccountUseCase, @org.jetbrains.annotations.NotNull()
    com.authvault.data.repository.AccountRepository accountRepository, @org.jetbrains.annotations.NotNull()
    com.authvault.data.repository.SettingsRepository settingsRepository) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.Flow<kotlin.Unit> getImagePickerRequests() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.Flow<kotlin.Unit> getScanRequests() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<com.authvault.presentation.ui.main.MainUiState> getUiState() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.MutableSharedFlow<com.authvault.presentation.ui.main.MainUiEvent> events() {
        return null;
    }
    
    public final void requestImagePick() {
    }
    
    public final void requestScan() {
    }
    
    public final void decodeAndSaveFromQr(@org.jetbrains.annotations.NotNull()
    java.lang.String rawUri, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function2<? super java.lang.Boolean, ? super java.lang.String, kotlin.Unit> onResult) {
    }
    
    private final com.authvault.presentation.ui.common.ParsedOtpUri parseOtpAuthUri(java.lang.String uriStr) {
        return null;
    }
    
    public final void decodeAndSaveFromImage(@org.jetbrains.annotations.NotNull()
    android.content.Context context, @org.jetbrains.annotations.NotNull()
    android.net.Uri uri) {
    }
    
    private final java.lang.Object decodeRawFromImage(android.content.Context context, android.net.Uri uri, kotlin.coroutines.Continuation<? super java.lang.String> $completion) {
        return null;
    }
    
    private final java.lang.Object decodeRawWithMlKit(android.content.Context context, android.net.Uri uri, kotlin.coroutines.Continuation<? super java.lang.String> $completion) {
        return null;
    }
    
    public final void onSearchClicked() {
    }
    
    public final void onSearchQueryChanged(@org.jetbrains.annotations.NotNull()
    java.lang.String value) {
    }
    
    public final void onSettingsClicked() {
    }
    
    public final void onAddClicked() {
    }
    
    public final void onCodeClicked(@org.jetbrains.annotations.NotNull()
    com.authvault.presentation.model.AccountUiModel account) {
    }
    
    public final void onDetailsClicked(int accountId) {
    }
    
    public final void onDeleteRequested(@org.jetbrains.annotations.NotNull()
    com.authvault.presentation.model.AccountUiModel account) {
    }
    
    public final void dismissDeleteSheet() {
    }
    
    public final void confirmDelete() {
    }
    
    public final void toggleReorderMode() {
    }
    
    public final void reorder(@org.jetbrains.annotations.NotNull()
    java.util.List<java.lang.Integer> newOrderIds) {
    }
    
    private final com.authvault.presentation.model.AccountUiModel toUiModel(com.authvault.domain.model.Account $this$toUiModel, kotlin.Pair<java.lang.String, java.lang.Integer> result) {
        return null;
    }
    
    private final java.util.Comparator<com.authvault.domain.model.Account> sortComparator(com.authvault.data.repository.SettingsState settings) {
        return null;
    }
}