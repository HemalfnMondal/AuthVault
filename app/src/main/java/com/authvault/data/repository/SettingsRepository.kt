package com.authvault.data.repository

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

data class SettingsState(
    val appLockEnabled: Boolean = false,
    val autoLockTimeoutMinutes: Int = 1,
    val autoClearClipboard: Boolean = true,
    val clipboardClearDelaySeconds: Int = 30,
    val sortOrder: String = "manual",
    val defaultAlgorithm: String = "SHA1",
    val defaultDigits: Int = 6
)

@Singleton
class SettingsRepository @Inject constructor(
    private val dataStore: DataStore<Preferences>
) {
    private object Keys {
        val APP_LOCK = booleanPreferencesKey("app_lock")
        val AUTO_LOCK = intPreferencesKey("auto_lock_minutes")
        val AUTO_CLEAR = booleanPreferencesKey("auto_clear")
        val CLIP_DELAY = intPreferencesKey("clip_delay")
        val SORT_ORDER = stringPreferencesKey("sort_order")
        val DEFAULT_ALG = stringPreferencesKey("default_alg")
        val DEFAULT_DIGITS = intPreferencesKey("default_digits")
    }

    val state: Flow<SettingsState> = dataStore.data.map { prefs ->
        SettingsState(
            appLockEnabled = prefs[Keys.APP_LOCK] ?: false,
            autoLockTimeoutMinutes = prefs[Keys.AUTO_LOCK] ?: 1,
            autoClearClipboard = prefs[Keys.AUTO_CLEAR] ?: true,
            clipboardClearDelaySeconds = prefs[Keys.CLIP_DELAY] ?: 30,
            sortOrder = prefs[Keys.SORT_ORDER] ?: "manual",
            defaultAlgorithm = prefs[Keys.DEFAULT_ALG] ?: "SHA1",
            defaultDigits = prefs[Keys.DEFAULT_DIGITS] ?: 6
        )
    }

    suspend fun update(transform: suspend (SettingsState) -> SettingsState) {
        val current = state.map { it }.let { it }
        dataStore.edit { prefs ->
            val existing = SettingsState(
                appLockEnabled = prefs[Keys.APP_LOCK] ?: false,
                autoLockTimeoutMinutes = prefs[Keys.AUTO_LOCK] ?: 1,
                autoClearClipboard = prefs[Keys.AUTO_CLEAR] ?: true,
                clipboardClearDelaySeconds = prefs[Keys.CLIP_DELAY] ?: 30,
                sortOrder = prefs[Keys.SORT_ORDER] ?: "manual",
                defaultAlgorithm = prefs[Keys.DEFAULT_ALG] ?: "SHA1",
                defaultDigits = prefs[Keys.DEFAULT_DIGITS] ?: 6
            )
            val updated = transform(existing)
            prefs[Keys.APP_LOCK] = updated.appLockEnabled
            prefs[Keys.AUTO_LOCK] = updated.autoLockTimeoutMinutes
            prefs[Keys.AUTO_CLEAR] = updated.autoClearClipboard
            prefs[Keys.CLIP_DELAY] = updated.clipboardClearDelaySeconds
            prefs[Keys.SORT_ORDER] = updated.sortOrder
            prefs[Keys.DEFAULT_ALG] = updated.defaultAlgorithm
            prefs[Keys.DEFAULT_DIGITS] = updated.defaultDigits
        }
    }
}
