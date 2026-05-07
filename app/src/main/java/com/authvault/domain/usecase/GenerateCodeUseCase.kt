package com.authvault.domain.usecase

import com.authvault.data.crypto.HotpEngine
import com.authvault.data.crypto.TotpEngine
import com.authvault.domain.model.Account
import javax.inject.Inject

class GenerateCodeUseCase @Inject constructor() {
    operator fun invoke(account: Account, nowMillis: Long): Pair<String, Int> {
        val code = if (account.type == "HOTP") {
            HotpEngine.generate(account.secretKey, account.counter, account.algorithm, account.digits)
        } else {
            TotpEngine.generate(account.secretKey, nowMillis, account.algorithm, account.digits, account.period)
        }
        val countdown = if (account.type == "HOTP") 0 else TotpEngine.countdown(nowMillis, account.period)
        return code to countdown
    }
}
