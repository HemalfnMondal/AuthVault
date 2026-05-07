package com.authvault.domain.usecase

import com.authvault.data.repository.AccountRepository
import javax.inject.Inject

class DeleteAccountUseCase @Inject constructor(
    private val repository: AccountRepository
) {
    suspend operator fun invoke(id: Int) = repository.deleteAccount(id)
}
