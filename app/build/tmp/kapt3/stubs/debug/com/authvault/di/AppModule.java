package com.authvault.di;

@dagger.Module()
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u00c7\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0007J\b\u0010\u0007\u001a\u00020\bH\u0007J\u0018\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u000b0\n2\b\b\u0001\u0010\f\u001a\u00020\rH\u0007J\u0016\u0010\u000e\u001a\u00020\u000f2\f\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u000b0\nH\u0007\u00a8\u0006\u0011"}, d2 = {"Lcom/authvault/di/AppModule;", "", "()V", "provideAccountRepository", "Lcom/authvault/data/repository/AccountRepository;", "database", "Lcom/authvault/data/db/AppDatabase;", "provideBackupRepository", "Lcom/authvault/data/repository/BackupRepository;", "provideDataStore", "Landroidx/datastore/core/DataStore;", "Landroidx/datastore/preferences/core/Preferences;", "context", "Landroid/content/Context;", "provideSettingsRepository", "Lcom/authvault/data/repository/SettingsRepository;", "dataStore", "app_debug"})
@dagger.hilt.InstallIn(value = {dagger.hilt.components.SingletonComponent.class})
public final class AppModule {
    @org.jetbrains.annotations.NotNull()
    public static final com.authvault.di.AppModule INSTANCE = null;
    
    private AppModule() {
        super();
    }
    
    @dagger.Provides()
    @javax.inject.Singleton()
    @org.jetbrains.annotations.NotNull()
    public final androidx.datastore.core.DataStore<androidx.datastore.preferences.core.Preferences> provideDataStore(@dagger.hilt.android.qualifiers.ApplicationContext()
    @org.jetbrains.annotations.NotNull()
    android.content.Context context) {
        return null;
    }
    
    @dagger.Provides()
    @javax.inject.Singleton()
    @org.jetbrains.annotations.NotNull()
    public final com.authvault.data.repository.AccountRepository provideAccountRepository(@org.jetbrains.annotations.NotNull()
    com.authvault.data.db.AppDatabase database) {
        return null;
    }
    
    @dagger.Provides()
    @javax.inject.Singleton()
    @org.jetbrains.annotations.NotNull()
    public final com.authvault.data.repository.BackupRepository provideBackupRepository() {
        return null;
    }
    
    @dagger.Provides()
    @javax.inject.Singleton()
    @org.jetbrains.annotations.NotNull()
    public final com.authvault.data.repository.SettingsRepository provideSettingsRepository(@org.jetbrains.annotations.NotNull()
    androidx.datastore.core.DataStore<androidx.datastore.preferences.core.Preferences> dataStore) {
        return null;
    }
}