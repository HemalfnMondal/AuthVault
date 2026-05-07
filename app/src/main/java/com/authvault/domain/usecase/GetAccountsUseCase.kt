package com.authvault.domain.usecase

import com.authvault.data.repository.AccountRepository
import javax.inject.Inject

class GetAccountsUseCase @Inject constructor(
    private val repository: AccountRepository
) {
    operator fun invoke() = repository.observeAccounts()
}
