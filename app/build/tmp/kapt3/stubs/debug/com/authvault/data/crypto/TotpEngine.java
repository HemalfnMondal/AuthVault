package com.authvault.data.crypto;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\b\b\u00c6\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u0016\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u0004J.\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\t2\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u000b\u001a\u00020\t2\u0006\u0010\f\u001a\u00020\u00042\u0006\u0010\u0007\u001a\u00020\u0004J0\u0010\r\u001a\u00020\t2\u0006\u0010\u000e\u001a\u00020\t2\u0006\u0010\u000b\u001a\u00020\t2\u0006\u0010\f\u001a\u00020\u00042\u0006\u0010\u0007\u001a\u00020\u00042\b\b\u0002\u0010\u000f\u001a\u00020\u0006J\u001a\u0010\u0010\u001a\u00020\u00042\b\b\u0002\u0010\u0007\u001a\u00020\u00042\b\b\u0002\u0010\u000f\u001a\u00020\u0006\u00a8\u0006\u0011"}, d2 = {"Lcom/authvault/data/crypto/TotpEngine;", "", "()V", "countdown", "", "timeMillis", "", "period", "generate", "", "secretKeyBase32", "algorithm", "digits", "generateTotp", "secretKey", "timestamp", "getCountdown", "app_debug"})
public final class TotpEngine {
    @org.jetbrains.annotations.NotNull()
    public static final com.authvault.data.crypto.TotpEngine INSTANCE = null;
    
    private TotpEngine() {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String generate(@org.jetbrains.annotations.NotNull()
    java.lang.String secretKeyBase32, long timeMillis, @org.jetbrains.annotations.NotNull()
    java.lang.String algorithm, int digits, int period) {
        return null;
    }
    
    public final int countdown(long timeMillis, int period) {
        return 0;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String generateTotp(@org.jetbrains.annotations.NotNull()
    java.lang.String secretKey, @org.jetbrains.annotations.NotNull()
    java.lang.String algorithm, int digits, int period, long timestamp) {
        return null;
    }
    
    public final int getCountdown(int period, long timestamp) {
        return 0;
    }
}