package com.authvault.data.repository

import com.authvault.data.crypto.BackupCrypto
import com.authvault.domain.model.Account
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class BackupRepository @Inject constructor() {
    private val gson = Gson()

    fun export(accounts: List<Account>, password: String): ByteArray {
        val json = gson.toJson(accounts)
        return BackupCrypto.encrypt(json.toByteArray(Charsets.UTF_8), password)
    }

    fun importBackup(bytes: ByteArray, password: String): List<Account> {
        val decrypted = BackupCrypto.decrypt(bytes, password)
        val type = object : TypeToken<List<Account>>() {}.type
        return gson.fromJson(decrypted.toString(Charsets.UTF_8), type)
    }
}
