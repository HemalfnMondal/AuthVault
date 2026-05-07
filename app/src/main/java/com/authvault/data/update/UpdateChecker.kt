package com.authvault.data.update

import com.authvault.BuildConfig
import com.google.gson.Gson
import com.google.gson.annotations.SerializedName
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.net.HttpURLConnection
import java.net.URL
import java.util.concurrent.TimeUnit
import javax.inject.Inject

data class UpdateInfo(
    val versionCode: Int,
    val versionName: String,
    val releaseNotes: String,
    val downloadUrl: String,
    val forceUpdate: Boolean
)

private data class VersionResponse(
    @SerializedName("version_code") val versionCode: Int,
    @SerializedName("version_name") val versionName: String,
    @SerializedName("release_notes") val releaseNotes: String,
    @SerializedName("download_url") val downloadUrl: String?,
    @SerializedName("force_update") val forceUpdate: Boolean
)

sealed interface UpdateCheckOutcome {
    data class Available(val info: UpdateInfo) : UpdateCheckOutcome
    data object Latest : UpdateCheckOutcome
    data object Error : UpdateCheckOutcome
}

class UpdateChecker @Inject constructor() {
    private val gson = Gson()

    suspend fun checkForUpdate(): UpdateInfo? = withContext(Dispatchers.IO) {
        when (val result = checkForUpdateOutcome()) {
            is UpdateCheckOutcome.Available -> result.info
            else -> null
        }
    }

    suspend fun checkForUpdateOutcome(): UpdateCheckOutcome = withContext(Dispatchers.IO) {
        try {
            val connection = (URL(VERSION_JSON_URL).openConnection() as HttpURLConnection).apply {
                connectTimeout = TimeUnit.SECONDS.toMillis(5).toInt()
                readTimeout = TimeUnit.SECONDS.toMillis(5).toInt()
                requestMethod = "GET"
                instanceFollowRedirects = true
                useCaches = false
            }
            connection.inputStream.bufferedReader().use { reader ->
                val responseBody = reader.readText()
                val parsed = gson.fromJson(responseBody, VersionResponse::class.java)
                if (parsed.versionCode > BuildConfig.VERSION_CODE) {
                    UpdateCheckOutcome.Available(
                        UpdateInfo(
                            versionCode = parsed.versionCode,
                            versionName = parsed.versionName,
                            releaseNotes = parsed.releaseNotes,
                            downloadUrl = parsed.downloadUrl?.takeIf { it.isNotBlank() } ?: RELEASES_URL,
                            forceUpdate = parsed.forceUpdate
                        )
                    )
                } else {
                    UpdateCheckOutcome.Latest
                }
            }
        } catch (_: Exception) {
            UpdateCheckOutcome.Error
        }
    }

    companion object {
        const val VERSION_JSON_URL = "https://raw.githubusercontent.com/HemalfnMondal/AuthVault/main/version.json"
        const val RELEASES_URL = "https://github.com/HemalfnMondal/AuthVault/releases/latest"
    }
}
