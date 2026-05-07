package com.authvault.data.repository

import com.authvault.data.db.AccountDao
import com.authvault.data.db.AccountEntity
import com.authvault.domain.model.Account
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AccountRepository @Inject constructor(
    private val accountDao: AccountDao
) {
    fun observeAccounts(): Flow<List<Account>> = accountDao.observeAccounts().map { entities ->
        entities.map { it.toDomain() }
    }

    suspend fun getAccounts(): List<Account> = accountDao.getAccounts().map { it.toDomain() }

    suspend fun getAccount(id: Int): Account? = accountDao.getAccountById(id)?.toDomain()

    suspend fun addAccount(account: Account): Int {
        val position = accountDao.getAccounts().size
        val entity = account.toEntity().copy(position = position)
        return accountDao.insert(entity).toInt()
    }

    suspend fun updateAccount(account: Account) {
        accountDao.update(account.toEntity())
    }

    suspend fun deleteAccount(id: Int) {
        accountDao.getAccountById(id)?.let { accountDao.delete(it) }
    }

    suspend fun clearAll() {
        accountDao.deleteAll()
    }

    suspend fun replaceAll(accounts: List<Account>) {
        accountDao.deleteAll()
        accountDao.insertAll(accounts.mapIndexed { index, account -> account.toEntity().copy(position = index) })
    }

    suspend fun importAccounts(accounts: List<Account>, replaceAll: Boolean): Int {
        if (replaceAll) {
            replaceAll(accounts)
            return accounts.size
        }
        val existingSecrets = accountDao.getAccounts().map { it.secretKey }.toSet()
        val filtered = accounts.filter { it.secretKey !in existingSecrets }
        if (filtered.isNotEmpty()) {
            accountDao.insertAll(filtered.mapIndexed { index, account ->
                account.toEntity().copy(position = accountDao.getAccounts().size + index)
            })
        }
        return filtered.size
    }

    suspend fun reorder(accountIds: List<Int>) {
        accountIds.forEachIndexed { index, id -> accountDao.updatePosition(id, index) }
    }

    suspend fun existsBySecretKey(secretKey: String): Boolean = accountDao.existsBySecretKey(secretKey)
}

private fun AccountEntity.toDomain(): Account = Account(
    id = id,
    issuer = issuer,
    accountName = accountName,
    secretKey = secretKey,
    algorithm = algorithm,
    digits = digits,
    period = period,
    type = type,
    counter = counter,
    position = position,
    createdAt = createdAt,
    iconSlug = iconSlug
)

private fun Account.toEntity(): AccountEntity = AccountEntity(
    id = id,
    issuer = issuer,
    accountName = accountName,
    secretKey = secretKey,
    algorithm = algorithm,
    digits = digits,
    period = period,
    type = type,
    counter = counter,
    position = position,
    createdAt = createdAt,
    iconSlug = iconSlug
)
