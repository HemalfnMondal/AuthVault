package com.authvault.domain.usecase

import com.authvault.data.repository.AccountRepository
import com.authvault.data.repository.BackupRepository
import javax.inject.Inject

class ExportBackupUseCase @Inject constructor(
    private val accountRepository: AccountRepository,
    private val backupRepository: BackupRepository
) {
    suspend operator fun invoke(password: String): ByteArray {
        return backupRepository.export(accountRepository.getAccounts(), password)
    }
}
