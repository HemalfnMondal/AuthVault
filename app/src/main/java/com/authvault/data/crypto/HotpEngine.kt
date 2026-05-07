package com.authvault.data.crypto

import android.util.Base64
import org.apache.commons.codec.binary.Base32
import javax.crypto.Mac
import javax.crypto.spec.SecretKeySpec

object HotpEngine {
    fun generate(
        secretKeyBase32: String,
        counter: Long,
        algorithm: String,
        digits: Int
    ): String {
        val normalized = secretKeyBase32.uppercase().replace(" ", "").replace("-", "").trimEnd('=')
        val key = Base32().decode(normalized)
        val mac = Mac.getInstance(algorithm.toHmacName())
        mac.init(SecretKeySpec(key, mac.algorithm))
        val counterBytes = ByteArray(8)
        var current = counter
        for (index in 7 downTo 0) {
            counterBytes[index] = (current and 0xff).toByte()
            current = current ushr 8
        }
        val hash = mac.doFinal(counterBytes)
        val offset = hash.last().toInt() and 0x0f
        val binary = ((hash[offset].toInt() and 0x7f) shl 24) or
            ((hash[offset + 1].toInt() and 0xff) shl 16) or
            ((hash[offset + 2].toInt() and 0xff) shl 8) or
            (hash[offset + 3].toInt() and 0xff)
        val modulo = 10.0.pow(digits).toInt()
        return (binary % modulo).toString().padStart(digits, '0')
    }

    private fun String.toHmacName(): String = when (uppercase()) {
        "SHA256" -> "HmacSHA256"
        "SHA512" -> "HmacSHA512"
        else -> "HmacSHA1"
    }

    private fun Double.pow(exponent: Int): Double {
        var result = 1.0
        repeat(exponent) { result *= this }
        return result
    }
}
