package com.authvault.data.db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "accounts")
data class AccountEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val issuer: String,
    val accountName: String,
    val secretKey: String,
    val algorithm: String,
    val digits: Int,
    val period: Int,
    val type: String,
    val counter: Long = 0,
    val position: Int = 0,
    val createdAt: Long = System.currentTimeMillis(),
    val iconSlug: String? = null
)
