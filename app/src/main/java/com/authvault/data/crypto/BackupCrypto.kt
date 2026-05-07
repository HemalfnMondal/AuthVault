package com.authvault.data.crypto

import android.util.Base64
import java.security.SecureRandom
import javax.crypto.Cipher
import javax.crypto.SecretKeyFactory
import javax.crypto.spec.GCMParameterSpec
import javax.crypto.spec.PBEKeySpec
import javax.crypto.spec.SecretKeySpec

data class BackupPackage(
    val salt: String,
    val iv: String,
    val cipherText: String
)

object BackupCrypto {
    private const val ITERATIONS = 100_000
    private const val KEY_SIZE_BITS = 256
    private const val GCM_TAG_BITS = 128

    fun encrypt(plainText: ByteArray, password: String): ByteArray {
        val salt = ByteArray(16).also { SecureRandom().nextBytes(it) }
        val iv = ByteArray(12).also { SecureRandom().nextBytes(it) }
        val secretKey = deriveKey(password, salt)
        val cipher = Cipher.getInstance("AES/GCM/NoPadding")
        cipher.init(Cipher.ENCRYPT_MODE, secretKey, GCMParameterSpec(GCM_TAG_BITS, iv))
        val cipherText = cipher.doFinal(plainText)
        val payload = BackupPackage(
            salt = Base64.encodeToString(salt, Base64.NO_WRAP),
            iv = Base64.encodeToString(iv, Base64.NO_WRAP),
            cipherText = Base64.encodeToString(cipherText, Base64.NO_WRAP)
        )
        return com.google.gson.Gson().toJson(payload).toByteArray(Charsets.UTF_8)
    }

    fun decrypt(encrypted: ByteArray, password: String): ByteArray {
        val payload = com.google.gson.Gson().fromJson(
            encrypted.toString(Charsets.UTF_8),
            BackupPackage::class.java
        )
        val salt = Base64.decode(payload.salt, Base64.NO_WRAP)
        val iv = Base64.decode(payload.iv, Base64.NO_WRAP)
        val cipherText = Base64.decode(payload.cipherText, Base64.NO_WRAP)
        val cipher = Cipher.getInstance("AES/GCM/NoPadding")
        cipher.init(Cipher.DECRYPT_MODE, deriveKey(password, salt), GCMParameterSpec(GCM_TAG_BITS, iv))
        return cipher.doFinal(cipherText)
    }

    private fun deriveKey(password: String, salt: ByteArray): SecretKeySpec {
        val factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256")
        val spec = PBEKeySpec(password.toCharArray(), salt, ITERATIONS, KEY_SIZE_BITS)
        val bytes = factory.generateSecret(spec).encoded
        return SecretKeySpec(bytes, "AES")
    }
}
