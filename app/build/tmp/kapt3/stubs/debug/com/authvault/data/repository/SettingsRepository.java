package com.authvault.data.repository;

@javax.inject.Singleton()
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0007\u0018\u00002\u00020\u0001:\u0001\u0011B\u0015\b\u0007\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u00a2\u0006\u0002\u0010\u0005J2\u0010\u000b\u001a\u00020\f2\"\u0010\r\u001a\u001e\b\u0001\u0012\u0004\u0012\u00020\b\u0012\n\u0012\b\u0012\u0004\u0012\u00020\b0\u000f\u0012\u0006\u0012\u0004\u0018\u00010\u00010\u000eH\u0086@\u00a2\u0006\u0002\u0010\u0010R\u0014\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0017\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007\u00a2\u0006\b\n\u0000\u001a\u0004\b\t\u0010\n\u00a8\u0006\u0012"}, d2 = {"Lcom/authvault/data/repository/SettingsRepository;", "", "dataStore", "Landroidx/datastore/core/DataStore;", "Landroidx/datastore/preferences/core/Preferences;", "(Landroidx/datastore/core/DataStore;)V", "state", "Lkotlinx/coroutines/flow/Flow;", "Lcom/authvault/data/repository/SettingsState;", "getState", "()Lkotlinx/coroutines/flow/Flow;", "update", "", "transform", "Lkotlin/Function2;", "Lkotlin/coroutines/Continuation;", "(Lkotlin/jvm/functions/Function2;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "Keys", "app_debug"})
public final class SettingsRepository {
    @org.jetbrains.annotations.NotNull()
    private final androidx.datastore.core.DataStore<androidx.datastore.preferences.core.Preferences> dataStore = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.Flow<com.authvault.data.repository.SettingsState> state = null;
    
    @javax.inject.Inject()
    public SettingsRepository(@org.jetbrains.annotations.NotNull()
    androidx.datastore.core.DataStore<androidx.datastore.preferences.core.Preferences> dataStore) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.Flow<com.authvault.data.repository.SettingsState> getState() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object update(@org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function2<? super com.authvault.data.repository.SettingsState, ? super kotlin.coroutines.Continuation<? super com.authvault.data.repository.SettingsState>, ? extends java.lang.Object> transform, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\b\u0007\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0002\b\r\b\u00c2\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u0017\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0017\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\t\u0010\u0007R\u0017\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\u0007R\u0017\u0010\f\u001a\b\u0012\u0004\u0012\u00020\r0\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u0007R\u0017\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\r0\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0007R\u0017\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00120\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0007R\u0017\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\r0\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0007R\u0013\u0010\u0016\u001a\u00020\u0017\u00a2\u0006\n\n\u0002\u0010\u001a\u001a\u0004\b\u0018\u0010\u0019R\u0017\u0010\u001b\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\u0007R\u0017\u0010\u001d\u001a\b\u0012\u0004\u0012\u00020\u00120\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001e\u0010\u0007\u00a8\u0006\u001f"}, d2 = {"Lcom/authvault/data/repository/SettingsRepository$Keys;", "", "()V", "ALLOW_SCREENSHOTS", "Landroidx/datastore/preferences/core/Preferences$Key;", "", "getALLOW_SCREENSHOTS", "()Landroidx/datastore/preferences/core/Preferences$Key;", "APP_LOCK", "getAPP_LOCK", "AUTO_CLEAR", "getAUTO_CLEAR", "AUTO_LOCK", "", "getAUTO_LOCK", "CLIP_DELAY", "getCLIP_DELAY", "DEFAULT_ALG", "", "getDEFAULT_ALG", "DEFAULT_DIGITS", "getDEFAULT_DIGITS", "LAST_UPDATE_CHECK", "error/NonExistentClass", "getLAST_UPDATE_CHECK", "()Lerror/NonExistentClass;", "Lerror/NonExistentClass;", "SCREENSHOT_WARNING_SHOWN", "getSCREENSHOT_WARNING_SHOWN", "SORT_ORDER", "getSORT_ORDER", "app_debug"})
    static final class Keys {
        @org.jetbrains.annotations.NotNull()
        private static final androidx.datastore.preferences.core.Preferences.Key<java.lang.Boolean> APP_LOCK = null;
        @org.jetbrains.annotations.NotNull()
        private static final androidx.datastore.preferences.core.Preferences.Key<java.lang.Integer> AUTO_LOCK = null;
        @org.jetbrains.annotations.NotNull()
        private static final androidx.datastore.preferences.core.Preferences.Key<java.lang.Boolean> AUTO_CLEAR = null;
        @org.jetbrains.annotations.NotNull()
        private static final androidx.datastore.preferences.core.Preferences.Key<java.lang.Integer> CLIP_DELAY = null;
        @org.jetbrains.annotations.NotNull()
        private static final androidx.datastore.preferences.core.Preferences.Key<java.lang.Boolean> ALLOW_SCREENSHOTS = null;
        @org.jetbrains.annotations.NotNull()
        private static final androidx.datastore.preferences.core.Preferences.Key<java.lang.Boolean> SCREENSHOT_WARNING_SHOWN = null;
        @org.jetbrains.annotations.NotNull()
        private static final error.NonExistentClass LAST_UPDATE_CHECK = null;
        @org.jetbrains.annotations.NotNull()
        private static final androidx.datastore.preferences.core.Preferences.Key<java.lang.String> SORT_ORDER = null;
        @org.jetbrains.annotations.NotNull()
        private static final androidx.datastore.preferences.core.Preferences.Key<java.lang.String> DEFAULT_ALG = null;
        @org.jetbrains.annotations.NotNull()
        private static final androidx.datastore.preferences.core.Preferences.Key<java.lang.Integer> DEFAULT_DIGITS = null;
        @org.jetbrains.annotations.NotNull()
        public static final com.authvault.data.repository.SettingsRepository.Keys INSTANCE = null;
        
        private Keys() {
            super();
        }
        
        @org.jetbrains.annotations.NotNull()
        public final androidx.datastore.preferences.core.Preferences.Key<java.lang.Boolean> getAPP_LOCK() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final androidx.datastore.preferences.core.Preferences.Key<java.lang.Integer> getAUTO_LOCK() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final androidx.datastore.preferences.core.Preferences.Key<java.lang.Boolean> getAUTO_CLEAR() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final androidx.datastore.preferences.core.Preferences.Key<java.lang.Integer> getCLIP_DELAY() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final androidx.datastore.preferences.core.Preferences.Key<java.lang.Boolean> getALLOW_SCREENSHOTS() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final androidx.datastore.preferences.core.Preferences.Key<java.lang.Boolean> getSCREENSHOT_WARNING_SHOWN() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final error.NonExistentClass getLAST_UPDATE_CHECK() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final androidx.datastore.preferences.core.Preferences.Key<java.lang.String> getSORT_ORDER() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final androidx.datastore.preferences.core.Preferences.Key<java.lang.String> getDEFAULT_ALG() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final androidx.datastore.preferences.core.Preferences.Key<java.lang.Integer> getDEFAULT_DIGITS() {
            return null;
        }
    }
}