package com.authvault.data.repository;

@javax.inject.Singleton()
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0007\u0018\u00002\u00020\u0001B\u000f\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u0016\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0086@\u00a2\u0006\u0002\u0010\tJ\u000e\u0010\n\u001a\u00020\u000bH\u0086@\u00a2\u0006\u0002\u0010\fJ\u0016\u0010\r\u001a\u00020\u000b2\u0006\u0010\u000e\u001a\u00020\u0006H\u0086@\u00a2\u0006\u0002\u0010\u000fJ\u0016\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u0013H\u0086@\u00a2\u0006\u0002\u0010\u0014J\u0018\u0010\u0015\u001a\u0004\u0018\u00010\b2\u0006\u0010\u000e\u001a\u00020\u0006H\u0086@\u00a2\u0006\u0002\u0010\u000fJ\u0014\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\b0\u0017H\u0086@\u00a2\u0006\u0002\u0010\fJ$\u0010\u0018\u001a\u00020\u00062\f\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\b0\u00172\u0006\u0010\u001a\u001a\u00020\u0011H\u0086@\u00a2\u0006\u0002\u0010\u001bJ\u0012\u0010\u001c\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\b0\u00170\u001dJ\u001c\u0010\u001e\u001a\u00020\u000b2\f\u0010\u001f\u001a\b\u0012\u0004\u0012\u00020\u00060\u0017H\u0086@\u00a2\u0006\u0002\u0010 J\u001c\u0010\u001a\u001a\u00020\u000b2\f\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\b0\u0017H\u0086@\u00a2\u0006\u0002\u0010 J\u0016\u0010!\u001a\u00020\u000b2\u0006\u0010\u0007\u001a\u00020\bH\u0086@\u00a2\u0006\u0002\u0010\tR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\""}, d2 = {"Lcom/authvault/data/repository/AccountRepository;", "", "accountDao", "Lcom/authvault/data/db/AccountDao;", "(Lcom/authvault/data/db/AccountDao;)V", "addAccount", "", "account", "Lcom/authvault/domain/model/Account;", "(Lcom/authvault/domain/model/Account;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "clearAll", "", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "deleteAccount", "id", "(ILkotlin/coroutines/Continuation;)Ljava/lang/Object;", "existsBySecretKey", "", "secretKey", "", "(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getAccount", "getAccounts", "", "importAccounts", "accounts", "replaceAll", "(Ljava/util/List;ZLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "observeAccounts", "Lkotlinx/coroutines/flow/Flow;", "reorder", "accountIds", "(Ljava/util/List;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "updateAccount", "app_debug"})
public final class AccountRepository {
    @org.jetbrains.annotations.NotNull()
    private final com.authvault.data.db.AccountDao accountDao = null;
    
    @javax.inject.Inject()
    public AccountRepository(@org.jetbrains.annotations.NotNull()
    com.authvault.data.db.AccountDao accountDao) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.Flow<java.util.List<com.authvault.domain.model.Account>> observeAccounts() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object getAccounts(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.util.List<com.authvault.domain.model.Account>> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object getAccount(int id, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.authvault.domain.model.Account> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object addAccount(@org.jetbrains.annotations.NotNull()
    com.authvault.domain.model.Account account, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.lang.Integer> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object updateAccount(@org.jetbrains.annotations.NotNull()
    com.authvault.domain.model.Account account, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object deleteAccount(int id, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object clearAll(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object replaceAll(@org.jetbrains.annotations.NotNull()
    java.util.List<com.authvault.domain.model.Account> accounts, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object importAccounts(@org.jetbrains.annotations.NotNull()
    java.util.List<com.authvault.domain.model.Account> accounts, boolean replaceAll, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.lang.Integer> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object reorder(@org.jetbrains.annotations.NotNull()
    java.util.List<java.lang.Integer> accountIds, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object existsBySecretKey(@org.jetbrains.annotations.NotNull()
    java.lang.String secretKey, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.lang.Boolean> $completion) {
        return null;
    }
}