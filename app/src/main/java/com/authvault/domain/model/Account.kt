package com.authvault.domain.model

data class Account(
    val id: Int,
    val issuer: String,
    val accountName: String,
    val secretKey: String,
    val algorithm: String,
    val digits: Int,
    val period: Int,
    val type: String,
    val counter: Long,
    val position: Int,
    val createdAt: Long,
    val iconSlug: String?
)
