package com.authvault.domain.usecase;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0012\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0017\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\u0002\u0010\u0006J&\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000eH\u0086B\u00a2\u0006\u0002\u0010\u000fR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0010"}, d2 = {"Lcom/authvault/domain/usecase/ImportBackupUseCase;", "", "accountRepository", "Lcom/authvault/data/repository/AccountRepository;", "backupRepository", "Lcom/authvault/data/repository/BackupRepository;", "(Lcom/authvault/data/repository/AccountRepository;Lcom/authvault/data/repository/BackupRepository;)V", "invoke", "", "bytes", "", "password", "", "replaceAll", "", "([BLjava/lang/String;ZLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "app_debug"})
public final class ImportBackupUseCase {
    @org.jetbrains.annotations.NotNull()
    private final com.authvault.data.repository.AccountRepository accountRepository = null;
    @org.jetbrains.annotations.NotNull()
    private final com.authvault.data.repository.BackupRepository backupRepository = null;
    
    @javax.inject.Inject()
    public ImportBackupUseCase(@org.jetbrains.annotations.NotNull()
    com.authvault.data.repository.AccountRepository accountRepository, @org.jetbrains.annotations.NotNull()
    com.authvault.data.repository.BackupRepository backupRepository) {
        super();
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object invoke(@org.jetbrains.annotations.NotNull()
    byte[] bytes, @org.jetbrains.annotations.NotNull()
    java.lang.String password, boolean replaceAll, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.lang.Integer> $completion) {
        return null;
    }
}