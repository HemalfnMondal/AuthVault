package com.authvault.data.crypto

object TotpEngine {
    fun generate(
        secretKeyBase32: String,
        timeMillis: Long,
        algorithm: String,
        digits: Int,
        period: Int
    ): String {
        val counter = timeMillis / 1000L / period
        return HotpEngine.generate(secretKeyBase32, counter, algorithm, digits)
    }

    fun countdown(timeMillis: Long, period: Int): Int {
        val second = timeMillis / 1000L
        return period - (second % period).toInt()
    }

    // Backwards-compatible wrappers matching requested API
    fun generateTotp(
        secretKey: String,
        algorithm: String,
        digits: Int,
        period: Int,
        timestamp: Long = System.currentTimeMillis()
    ): String = generate(secretKey, timestamp, algorithm, digits, period)

    fun getCountdown(period: Int = 30, timestamp: Long = System.currentTimeMillis()): Int = countdown(timestamp, period)
}
