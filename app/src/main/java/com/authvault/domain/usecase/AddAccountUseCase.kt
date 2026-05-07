package com.authvault.domain.usecase

import com.authvault.data.repository.AccountRepository
import com.authvault.domain.model.Account
import javax.inject.Inject

class AddAccountUseCase @Inject constructor(
    private val repository: AccountRepository
) {
    suspend operator fun invoke(account: Account): Int = repository.addAccount(account)
}
