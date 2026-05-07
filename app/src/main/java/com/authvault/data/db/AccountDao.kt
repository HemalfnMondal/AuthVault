package com.authvault.data.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface AccountDao {
    @Query("SELECT * FROM accounts ORDER BY position ASC, createdAt ASC")
    fun observeAccounts(): Flow<List<AccountEntity>>

    @Query("SELECT * FROM accounts WHERE id = :id LIMIT 1")
    suspend fun getAccountById(id: Int): AccountEntity?

    @Query("SELECT * FROM accounts ORDER BY position ASC, createdAt ASC")
    suspend fun getAccounts(): List<AccountEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(account: AccountEntity): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(accounts: List<AccountEntity>): List<Long>

    @Update
    suspend fun update(account: AccountEntity)

    @Delete
    suspend fun delete(account: AccountEntity)

    @Query("DELETE FROM accounts")
    suspend fun deleteAll()

    @Query("UPDATE accounts SET position = :position WHERE id = :id")
    suspend fun updatePosition(id: Int, position: Int)

    @Query("SELECT EXISTS(SELECT 1 FROM accounts WHERE secretKey = :secretKey LIMIT 1)")
    suspend fun existsBySecretKey(secretKey: String): Boolean
}
