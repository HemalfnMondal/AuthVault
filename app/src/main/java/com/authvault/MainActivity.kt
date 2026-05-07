package com.authvault

import android.os.Bundle
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.Alignment
import androidx.compose.ui.unit.dp
import androidx.compose.material3.MaterialTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.core.view.WindowCompat
import com.authvault.data.repository.SettingsRepository
import com.authvault.presentation.navigation.AppNavGraph
import com.authvault.presentation.theme.AuthVaultTheme
import dagger.hilt.EntryPoint
import dagger.hilt.InstallIn
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.EntryPointAccessors
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private val settingsRepository: SettingsRepository by lazy(LazyThreadSafetyMode.NONE) {
        EntryPointAccessors.fromApplication(
            applicationContext,
            MainActivityEntryPoint::class.java
        ).settingsRepository()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        // Apply persisted policy before first frame to avoid secure/non-secure flicker.
        val allowScreenshots = runBlocking { settingsRepository.state.first().allowScreenshots }
        applyScreenshotPolicy(allowScreenshots)
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        WindowCompat.setDecorFitsSystemWindows(window, false)
        setContent {
            AuthVaultTheme {
                AuthGate(
                    onAllowScreenshotsChanged = ::applyScreenshotPolicy
                ) {
                    AppRoot()
                }
            }
        }
    }

    private fun applyScreenshotPolicy(allowScreenshots: Boolean) {
        if (allowScreenshots) {
            window.clearFlags(WindowManager.LayoutParams.FLAG_SECURE)
        } else {
            window.addFlags(WindowManager.LayoutParams.FLAG_SECURE)
        }
    }
}

@EntryPoint
@InstallIn(SingletonComponent::class)
interface MainActivityEntryPoint {
    fun settingsRepository(): SettingsRepository
}

@Composable
private fun AppRoot() {
    AppNavGraph()
}

@Composable
private fun AuthGate(
    onAllowScreenshotsChanged: (Boolean) -> Unit,
    content: @Composable () -> Unit
) {
    // Simple biometric gate: if app lock enabled, prompt biometric before showing app
    val settingsViewModel: com.authvault.presentation.ui.settings.SettingsViewModel = androidx.hilt.navigation.compose.hiltViewModel()
    val settings by settingsViewModel.settingsState.collectAsState(initial = com.authvault.data.repository.SettingsState())
    val context = androidx.compose.ui.platform.LocalContext.current
    var unlocked by remember { mutableStateOf(!settings.appLockEnabled) }

    // react to settings change
    LaunchedEffect(settings.appLockEnabled) {
        if (settings.appLockEnabled) unlocked = false else unlocked = true
    }

    LaunchedEffect(settings.allowScreenshots) {
        onAllowScreenshotsChanged(settings.allowScreenshots)
    }

    if (!unlocked) {
        // show minimal lock UI and trigger biometric prompt
        val activity = context as androidx.fragment.app.FragmentActivity
        val executor = androidx.core.content.ContextCompat.getMainExecutor(context)
        val callback = object : androidx.biometric.BiometricPrompt.AuthenticationCallback() {
            override fun onAuthenticationSucceeded(result: androidx.biometric.BiometricPrompt.AuthenticationResult) {
                super.onAuthenticationSucceeded(result)
                android.util.Log.d("Biometric", "Authentication succeeded")
                unlocked = true
            }

            override fun onAuthenticationError(errorCode: Int, errString: CharSequence) {
                super.onAuthenticationError(errorCode, errString)
                android.util.Log.e("Biometric", "Auth error $errorCode: $errString")
            }

            override fun onAuthenticationFailed() {
                super.onAuthenticationFailed()
                android.util.Log.d("Biometric", "Authentication failed")
            }
        }

        val prompt = androidx.biometric.BiometricPrompt(activity, executor, callback)
        val promptInfo = androidx.biometric.BiometricPrompt.PromptInfo.Builder()
            .setTitle("Unlock AuthVault")
            .setSubtitle("Authenticate to view your accounts")
            .setNegativeButtonText("Use device credential")
            .build()

        // Prompt once when gate appears
        LaunchedEffect(Unit) {
            try {
                android.util.Log.d("Biometric", "Showing biometric prompt")
                prompt.authenticate(promptInfo)
            } catch (e: Exception) {
                android.util.Log.e("Biometric", "Failed to show prompt", e)
            }
        }

        // Simple lock UI
        androidx.compose.foundation.layout.Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            androidx.compose.material3.Card {
                androidx.compose.foundation.layout.Column(modifier = Modifier.padding(24.dp), horizontalAlignment = Alignment.CenterHorizontally) {
                    androidx.compose.material3.Text("AuthVault", style = MaterialTheme.typography.headlineSmall)
                    androidx.compose.foundation.layout.Spacer(modifier = Modifier.height(12.dp))
                    androidx.compose.material3.Button(onClick = { try { prompt.authenticate(promptInfo) } catch (_: Exception) {} }) {
                        androidx.compose.material3.Text("Unlock")
                    }
                }
            }
        }
    } else {
        content()
    }
}
