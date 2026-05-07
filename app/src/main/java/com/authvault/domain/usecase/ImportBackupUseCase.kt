package com.authvault.domain.usecase

import com.authvault.data.repository.AccountRepository
import com.authvault.data.repository.BackupRepository
import javax.inject.Inject

class ImportBackupUseCase @Inject constructor(
    private val accountRepository: AccountRepository,
    private val backupRepository: BackupRepository
) {
    suspend operator fun invoke(bytes: ByteArray, password: String, replaceAll: Boolean): Int {
        val accounts = backupRepository.importBackup(bytes, password)
        return accountRepository.importAccounts(accounts, replaceAll)
    }
}
