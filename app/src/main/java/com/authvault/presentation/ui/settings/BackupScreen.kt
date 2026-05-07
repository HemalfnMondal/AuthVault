package com.authvault.presentation.ui.settings

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.hilt.navigation.compose.hiltViewModel
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BackupScreen(
    viewModel: SettingsViewModel = hiltViewModel(),
    mode: Mode,
    onNavigateBack: () -> Unit
) {
    val context = LocalContext.current
    val scope = rememberCoroutineScope()
    var password by remember { mutableStateOf("") }
    var confirm by remember { mutableStateOf("") }
    var selectedBytes by remember { mutableStateOf<ByteArray?>(null) }
    var importCount by remember { mutableStateOf<Int?>(null) }
    var successMessage by remember { mutableStateOf<String?>(null) }
    var errorMessage by remember { mutableStateOf<String?>(null) }
    var replaceDialog by remember { mutableStateOf(false) }
    var fileUri by remember { mutableStateOf<Uri?>(null) }
    var backupBytes by remember { mutableStateOf<ByteArray?>(null) }

    val createDocumentLauncher = rememberLauncherForActivityResult(ActivityResultContracts.CreateDocument("application/octet-stream")) { uri ->
        val bytes = backupBytes ?: return@rememberLauncherForActivityResult
        if (uri != null) {
            context.contentResolver.openOutputStream(uri)?.use { output -> output.write(bytes) }
            successMessage = "Backup saved"
        }
    }
    val openDocumentLauncher = rememberLauncherForActivityResult(ActivityResultContracts.OpenDocument()) { uri ->
        if (uri != null) {
            context.contentResolver.takePersistableUriPermission(uri, android.content.Intent.FLAG_GRANT_READ_URI_PERMISSION)
            fileUri = uri
            context.contentResolver.openInputStream(uri)?.use { input ->
                selectedBytes = input.readBytes()
            }
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(if (mode == Mode.Export) "Export Backup" else "Import Backup") },
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
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            if (mode == Mode.Export) {
                OutlinedTextField(value = password, onValueChange = { password = it }, label = { Text("Backup password") }, modifier = Modifier.fillMaxWidth())
                OutlinedTextField(value = confirm, onValueChange = { confirm = it }, label = { Text("Confirm password") }, modifier = Modifier.fillMaxWidth())
                Button(
                    onClick = {
                        scope.launch {
                            runCatching { viewModel.exportBackup(password) }
                                .onSuccess { bytes ->
                                    backupBytes = bytes
                                    createDocumentLauncher.launch("authvault.2fabak")
                                    errorMessage = null
                                }
                                .onFailure { errorMessage = it.message ?: "Could not export backup" }
                        }
                    },
                    enabled = password.length >= 8 && password == confirm,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Export")
                }
            } else {
                Button(onClick = { openDocumentLauncher.launch(arrayOf("*/*")) }, modifier = Modifier.fillMaxWidth()) {
                    Text("Choose Backup File")
                }
                OutlinedTextField(value = password, onValueChange = { password = it }, label = { Text("Backup password") }, modifier = Modifier.fillMaxWidth())
                Button(
                    onClick = {
                        val bytes = selectedBytes ?: return@Button
                        scope.launch {
                            runCatching { viewModel.previewImport(bytes, password) }
                                .onSuccess { count ->
                                    importCount = count
                                    replaceDialog = true
                                    errorMessage = null
                                }
                                .onFailure { errorMessage = it.message ?: "Could not read backup" }
                        }
                    },
                    enabled = selectedBytes != null && password.length >= 8,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Preview Import")
                }
            }

            successMessage?.let { Text(it) }
            errorMessage?.let { Text(it, color = androidx.compose.material3.MaterialTheme.colorScheme.error) }
            importCount?.let { Text("Found $it accounts") }
        }
    }

    if (replaceDialog && importCount != null) {
        AlertDialog(
            onDismissRequest = { replaceDialog = false },
            title = { Text("Restore Backup") },
            text = { Text("Found $importCount accounts. Replace all existing accounts or merge?") },
            confirmButton = {
                TextButton(onClick = {
                    val bytes = selectedBytes ?: return@TextButton
                    scope.launch {
                            runCatching { viewModel.importBackup(bytes, password, replaceAll = false) }
                                .onSuccess { restored ->
                                    successMessage = "$restored accounts restored"
                                    replaceDialog = false
                                }
                                .onFailure { errorMessage = it.message ?: "Could not restore backup" }
                    }
                }) { Text("Merge") }
            },
            dismissButton = {
                Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    TextButton(onClick = { replaceDialog = false }) { Text("Cancel") }
                    TextButton(onClick = {
                        val bytes = selectedBytes ?: return@TextButton
                        scope.launch {
                                runCatching { viewModel.importBackup(bytes, password, replaceAll = true) }
                                    .onSuccess { restored ->
                                        successMessage = "$restored accounts restored"
                                        replaceDialog = false
                                    }
                                    .onFailure { errorMessage = it.message ?: "Could not restore backup" }
                        }
                    }) { Text("Replace All") }
                }
            }
        )
    }
}

enum class Mode { Export, Import }
