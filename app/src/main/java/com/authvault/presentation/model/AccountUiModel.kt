package com.authvault.presentation.model

data class AccountUiModel(
    val id: Int,
    val issuer: String,
    val accountName: String,
    val code: String,
    val countdown: Int,
    val algorithm: String,
    val digits: Int,
    val period: Int,
    val type: String,
    val counter: Long,
    val position: Int,
    val createdAt: Long,
    val iconSlug: String?
)
