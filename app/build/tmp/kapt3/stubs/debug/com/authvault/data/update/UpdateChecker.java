package com.authvault.data.update;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u0000 \n2\u00020\u0001:\u0001\nB\u0005\u00a2\u0006\u0002\u0010\u0002J\u0010\u0010\u0005\u001a\u0004\u0018\u00010\u0006H\u0086@\u00a2\u0006\u0002\u0010\u0007J\u000e\u0010\b\u001a\u00020\tH\u0086@\u00a2\u0006\u0002\u0010\u0007R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u000b"}, d2 = {"Lcom/authvault/data/update/UpdateChecker;", "", "()V", "gson", "Lcom/google/gson/Gson;", "checkForUpdate", "Lcom/authvault/data/update/UpdateInfo;", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "checkForUpdateOutcome", "Lcom/authvault/data/update/UpdateCheckOutcome;", "Companion", "app_debug"})
public final class UpdateChecker {
    @org.jetbrains.annotations.NotNull()
    private final com.google.gson.Gson gson = null;
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String VERSION_JSON_URL = "https://raw.githubusercontent.com/HemalfnMondal/AuthVault/main/version.json";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String RELEASES_URL = "https://github.com/HemalfnMondal/AuthVault/releases/latest";
    @org.jetbrains.annotations.NotNull()
    public static final com.authvault.data.update.UpdateChecker.Companion Companion = null;
    
    public UpdateChecker() {
        super();
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object checkForUpdate(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.authvault.data.update.UpdateInfo> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object checkForUpdateOutcome(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.authvault.data.update.UpdateCheckOutcome> $completion) {
        return null;
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0006"}, d2 = {"Lcom/authvault/data/update/UpdateChecker$Companion;", "", "()V", "RELEASES_URL", "", "VERSION_JSON_URL", "app_debug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
    }
}