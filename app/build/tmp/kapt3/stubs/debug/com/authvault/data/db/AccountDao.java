package com.authvault.data.db;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010 \n\u0000\n\u0002\u0010\t\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0005\bg\u0018\u00002\u00020\u0001J\u0016\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u00a7@\u00a2\u0006\u0002\u0010\u0006J\u000e\u0010\u0007\u001a\u00020\u0003H\u00a7@\u00a2\u0006\u0002\u0010\bJ\u0016\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\fH\u00a7@\u00a2\u0006\u0002\u0010\rJ\u0018\u0010\u000e\u001a\u0004\u0018\u00010\u00052\u0006\u0010\u000f\u001a\u00020\u0010H\u00a7@\u00a2\u0006\u0002\u0010\u0011J\u0014\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00050\u0013H\u00a7@\u00a2\u0006\u0002\u0010\bJ\u0016\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0004\u001a\u00020\u0005H\u00a7@\u00a2\u0006\u0002\u0010\u0006J\"\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\u00150\u00132\f\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u00050\u0013H\u00a7@\u00a2\u0006\u0002\u0010\u0018J\u0014\u0010\u0019\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\u00130\u001aH\'J\u0016\u0010\u001b\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u00a7@\u00a2\u0006\u0002\u0010\u0006J\u001e\u0010\u001c\u001a\u00020\u00032\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u001d\u001a\u00020\u0010H\u00a7@\u00a2\u0006\u0002\u0010\u001e\u00a8\u0006\u001f"}, d2 = {"Lcom/authvault/data/db/AccountDao;", "", "delete", "", "account", "Lcom/authvault/data/db/AccountEntity;", "(Lcom/authvault/data/db/AccountEntity;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "deleteAll", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "existsBySecretKey", "", "secretKey", "", "(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getAccountById", "id", "", "(ILkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getAccounts", "", "insert", "", "insertAll", "accounts", "(Ljava/util/List;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "observeAccounts", "Lkotlinx/coroutines/flow/Flow;", "update", "updatePosition", "position", "(IILkotlin/coroutines/Continuation;)Ljava/lang/Object;", "app_debug"})
@androidx.room.Dao()
public abstract interface AccountDao {
    
    @androidx.room.Query(value = "SELECT * FROM accounts ORDER BY position ASC, createdAt ASC")
    @org.jetbrains.annotations.NotNull()
    public abstract kotlinx.coroutines.flow.Flow<java.util.List<com.authvault.data.db.AccountEntity>> observeAccounts();
    
    @androidx.room.Query(value = "SELECT * FROM accounts WHERE id = :id LIMIT 1")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getAccountById(int id, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.authvault.data.db.AccountEntity> $completion);
    
    @androidx.room.Query(value = "SELECT * FROM accounts ORDER BY position ASC, createdAt ASC")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getAccounts(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.util.List<com.authvault.data.db.AccountEntity>> $completion);
    
    @androidx.room.Insert(onConflict = 1)
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object insert(@org.jetbrains.annotations.NotNull()
    com.authvault.data.db.AccountEntity account, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.lang.Long> $completion);
    
    @androidx.room.Insert(onConflict = 1)
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object insertAll(@org.jetbrains.annotations.NotNull()
    java.util.List<com.authvault.data.db.AccountEntity> accounts, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.util.List<java.lang.Long>> $completion);
    
    @androidx.room.Update()
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object update(@org.jetbrains.annotations.NotNull()
    com.authvault.data.db.AccountEntity account, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @androidx.room.Delete()
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object delete(@org.jetbrains.annotations.NotNull()
    com.authvault.data.db.AccountEntity account, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @androidx.room.Query(value = "DELETE FROM accounts")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object deleteAll(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @androidx.room.Query(value = "UPDATE accounts SET position = :position WHERE id = :id")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object updatePosition(int id, int position, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @androidx.room.Query(value = "SELECT EXISTS(SELECT 1 FROM accounts WHERE secretKey = :secretKey LIMIT 1)")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object existsBySecretKey(@org.jetbrains.annotations.NotNull()
    java.lang.String secretKey, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.lang.Boolean> $completion);
}