package com.authvault.presentation.ui.common

import android.net.Uri
import com.authvault.domain.model.Account
import java.net.URLDecoder

data class ParsedOtpUri(
    val issuer: String,
    val accountName: String,
    val secretKey: String,
    val algorithm: String,
    val digits: Int,
    val period: Int,
    val type: String,
    val counter: Long,
    val iconSlug: String?
)

object OtpUriParser {
    fun parse(raw: String): ParsedOtpUri {
        require(raw.startsWith("otpauth://", ignoreCase = true)) { "Invalid otpauth URI" }
        val uri = Uri.parse(raw)
        val type = uri.host?.uppercase() ?: error("Missing OTP type")

        val path = uri.path?.removePrefix("/").orEmpty()
        val labelParts = path.split(":", limit = 2)
        val (issuer, accountName) = if (labelParts.size == 2) {
            URLDecoder.decode(labelParts[0], "UTF-8") to URLDecoder.decode(labelParts[1], "UTF-8")
        } else {
            val acct = URLDecoder.decode(path, "UTF-8")
            (uri.getQueryParameter("issuer") ?: acct) to acct
        }

        val secretRaw = uri.getQueryParameter("secret") ?: error("Missing secret")
        val cleanSecret = secretRaw
            .replace(" ", "")
            .replace("-", "")
            .uppercase()
            .trimEnd('=')

        val algorithm = uri.getQueryParameter("algorithm")?.uppercase() ?: "SHA1"
        val digits = uri.getQueryParameter("digits")?.toIntOrNull() ?: 6
        val period = uri.getQueryParameter("period")?.toIntOrNull() ?: 30
        val counter = uri.getQueryParameter("counter")?.toLongOrNull() ?: 0L

        return ParsedOtpUri(
            issuer = issuer.ifBlank { accountName },
            accountName = accountName.ifBlank { issuer },
            secretKey = cleanSecret,
            algorithm = algorithm,
            digits = digits,
            period = period,
            type = type,
            counter = counter,
            iconSlug = iconSlugForIssuer(issuer)
        )
    }

    private fun String.isBase32(): Boolean {
        val normalized = uppercase().replace(" ", "")
        return normalized.matches(Regex("^[A-Z2-7]+=*$"))
    }
}
