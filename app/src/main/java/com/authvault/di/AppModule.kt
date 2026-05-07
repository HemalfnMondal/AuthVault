package com.authvault.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStoreFile
import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import com.authvault.data.db.AppDatabase
import com.authvault.data.repository.AccountRepository
import com.authvault.data.repository.BackupRepository
import com.authvault.data.repository.SettingsRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

private const val SETTINGS_DATA_STORE = "authvault_settings"

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun provideDataStore(@ApplicationContext context: Context): DataStore<Preferences> {
        return PreferenceDataStoreFactory.create {
            context.preferencesDataStoreFile(SETTINGS_DATA_STORE)
        }
    }

    @Provides
    @Singleton
    fun provideAccountRepository(database: AppDatabase): AccountRepository {
        return AccountRepository(database.accountDao())
    }

    @Provides
    @Singleton
    fun provideBackupRepository(): BackupRepository = BackupRepository()

    @Provides
    @Singleton
    fun provideSettingsRepository(dataStore: DataStore<Preferences>): SettingsRepository {
        return SettingsRepository(dataStore)
    }
}
