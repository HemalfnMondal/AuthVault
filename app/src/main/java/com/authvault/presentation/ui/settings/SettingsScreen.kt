package com.authvault.presentation.ui.settings

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.hilt.navigation.compose.hiltViewModel
import com.authvault.BuildConfig
import com.authvault.data.repository.SettingsState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingsScreen(
    viewModel: SettingsViewModel = hiltViewModel(),
    onNavigateBack: () -> Unit,
    onExportBackup: () -> Unit,
    onImportBackup: () -> Unit
) {
    val settings by viewModel.settingsState.collectAsState()
    var showLicenses by remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Settings") },
                navigationIcon = {
                    IconButton(onClick = onNavigateBack) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(16.dp)
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            SectionHeader("Security")
            SettingSwitchRow(
                title = "App Lock",
                checked = settings.appLockEnabled,
                onCheckedChange = viewModel::setAppLock
            )
            if (settings.appLockEnabled) {
                ChoiceRow(
                    title = "Auto-lock timeout",
                    value = "${settings.autoLockTimeoutMinutes} minute${if (settings.autoLockTimeoutMinutes == 1) "" else "s"}",
                    options = listOf(1, 5, 10),
                    onSelected = viewModel::setAutoLockTimeout,
                    labelFor = { if (it == 1) "Immediately" else "$it minutes" }
                )
            }
            SettingSwitchRow(
                title = "Auto-clear clipboard",
                checked = settings.autoClearClipboard,
                onCheckedChange = viewModel::setAutoClearClipboard
            )
            if (settings.autoClearClipboard) {
                ChoiceRow(
                    title = "Clipboard clear delay",
                    value = if (settings.clipboardClearDelaySeconds == 30) "30 seconds" else "1 minute",
                    options = listOf(30, 60),
                    onSelected = viewModel::setClipboardDelay,
                    labelFor = { if (it == 30) "30 seconds" else "1 minute" }
                )
            }

            SectionHeader("Accounts")
            ChoiceRow(
                title = "Sort order",
                value = when (settings.sortOrder) { "az" -> "A-Z"; "date" -> "Date added"; else -> "Manual order" },
                options = listOf("manual", "az", "date"),
                onSelected = viewModel::setSortOrder,
                labelFor = { when (it) { "manual" -> "Manual order"; "az" -> "A-Z"; else -> "Date added" } }
            )
            ChoiceRow(
                title = "Default algorithm",
                value = settings.defaultAlgorithm,
                options = listOf("SHA1", "SHA256", "SHA512"),
                onSelected = viewModel::setDefaultAlgorithm,
                labelFor = { it }
            )
            ChoiceRow(
                title = "Default digits",
                value = settings.defaultDigits.toString(),
                options = listOf(6, 8),
                onSelected = viewModel::setDefaultDigits,
                labelFor = { it.toString() }
            )

            SectionHeader("Backup")
            Button(onClick = onExportBackup, modifier = Modifier.fillMaxWidth()) { Text("Export Backup") }
            OutlinedButton(onClick = onImportBackup, modifier = Modifier.fillMaxWidth()) { Text("Import Backup") }

            SectionHeader("About")
            Text("AuthVault")
            Text("Version ${BuildConfig.VERSION_NAME}")
            Text("A simple and reliable authenticator app for storing and generating 2FA codes.")
            TextButton(onClick = { showLicenses = true }) { Text("Open source licenses") }

            if (showLicenses) {
                androidx.compose.material3.AlertDialog(
                    onDismissRequest = { showLicenses = false },
                    confirmButton = { TextButton(onClick = { showLicenses = false }) { Text("Close") } },
                    title = { Text("Open source licenses") },
                    text = { Text("Jetpack Compose, Hilt, Room, Coil, CameraX, ML Kit, ZXing, SQLCipher, Commons Codec, and AndroidX libraries.") }
                )
            }
        }
    }
}

@Composable
private fun SectionHeader(title: String) {
    Text(title, style = MaterialTheme.typography.titleMedium)
}

@Composable
private fun SettingSwitchRow(
    title: String,
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit
) {
    Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.fillMaxWidth()) {
        Text(title, modifier = Modifier.weight(1f))
        Switch(checked = checked, onCheckedChange = onCheckedChange)
    }
}

@Composable
private fun <T> ChoiceRow(
    title: String,
    value: String,
    options: List<T>,
    onSelected: (T) -> Unit,
    labelFor: (T) -> String
) {
    var expanded by remember { mutableStateOf(false) }
    Column(modifier = Modifier.fillMaxWidth()) {
        Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.fillMaxWidth()) {
            Text(title, modifier = Modifier.weight(1f))
            TextButton(onClick = { expanded = !expanded }) { Text(value) }
        }
        androidx.compose.material3.DropdownMenu(expanded = expanded, onDismissRequest = { expanded = false }) {
            options.forEach { option ->
                DropdownMenuItem(
                    text = { Text(labelFor(option)) },
                    onClick = {
                        onSelected(option)
                        expanded = false
                    }
                )
            }
        }
    }
}
