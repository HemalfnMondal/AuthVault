package com.authvault.data.db

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [AccountEntity::class],
    version = 1,
    exportSchema = true
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun accountDao(): AccountDao
}
