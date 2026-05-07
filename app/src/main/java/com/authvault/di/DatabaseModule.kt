package com.authvault.di

import android.content.Context
import androidx.room.Room
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKey
import com.authvault.data.db.AppDatabase
import net.sqlcipher.database.SQLiteDatabase
import net.sqlcipher.database.SupportFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {
    @Provides
    @Singleton
    fun provideMasterKey(@ApplicationContext context: Context): MasterKey {
        return MasterKey.Builder(context)
            .setKeyScheme(MasterKey.KeyScheme.AES256_GCM)
            .build()
    }

    @Provides
    @Singleton
    fun provideDatabasePassphrase(@ApplicationContext context: Context, masterKey: MasterKey): ByteArray {
        val prefs = EncryptedSharedPreferences.create(
            context,
            "authvault_secure_prefs",
            masterKey,
            EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
            EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
        )
        val existing = prefs.getString("db_passphrase", null)
        val passphrase = existing ?: java.util.UUID.randomUUID().toString().replace("-", "")
        if (existing == null) {
            prefs.edit().putString("db_passphrase", passphrase).apply()
        }
        return SQLiteDatabase.getBytes(passphrase.toCharArray())
    }

    @Provides
    @Singleton
    fun provideSupportFactory(passphrase: ByteArray): SupportFactory {
        return SupportFactory(passphrase)
    }

    @Provides
    @Singleton
    fun provideDatabase(
        @ApplicationContext context: Context,
        supportFactory: SupportFactory
    ): AppDatabase {
        return Room.databaseBuilder(context, AppDatabase::class.java, "authvault.db")
            .openHelperFactory(supportFactory)
            .fallbackToDestructiveMigration()
            .build()
    }
}
