package com.authvault.presentation.ui.edit

import android.text.format.DateFormat
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.authvault.presentation.theme.Cyan
import com.authvault.presentation.ui.common.QrCodeGenerator
import com.authvault.presentation.ui.common.SecureScreen
import com.authvault.presentation.ui.common.ServiceIconView
import com.authvault.presentation.ui.settings.SettingsViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditAccountScreen(
    viewModel: EditAccountViewModel = hiltViewModel(),
    onNavigateBack: () -> Unit,
    onSaved: () -> Unit
) {
    val state by viewModel.uiState.collectAsState()
    val settingsViewModel: SettingsViewModel = hiltViewModel()
    val settings by settingsViewModel.settingsState.collectAsState()
    val snackbarHostState = remember { SnackbarHostState() }
    SecureScreen(forceSecure = state.secretVisible, baseSecure = !settings.allowScreenshots)

    LaunchedEffect(Unit) {
        viewModel.uiEvents.collect { event ->
            when (event) {
                EditAccountEvent.Saved -> onSaved()
                is EditAccountEvent.Error -> snackbarHostState.showSnackbar(event.message)
            }
        }
    }

    BackHandler(enabled = true) {
        if (state.hasChanges) viewModel.requestDiscardDialog() else onNavigateBack()
    }

    Scaffold(
        snackbarHost = { SnackbarHost(hostState = snackbarHostState) },
        topBar = {
            TopAppBar(
                title = { Text("Edit Account") },
                navigationIcon = {
                    IconButton(onClick = {
                        if (state.hasChanges) viewModel.requestDiscardDialog() else onNavigateBack()
                    }) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
                    }
                },
                actions = {
                    TextButton(
                        onClick = viewModel::saveChanges,
                        enabled = state.saveEnabled
                    ) {
                        Text("Save")
                    }
                }
            )
        }
    ) { padding ->
        if (state.loading) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                CircularProgressIndicator(color = MaterialTheme.colorScheme.primary)
            }
        } else {
            val otpauth = buildOtpAuthUri(state)
            val qrBitmap = remember(otpauth) { QrCodeGenerator.generate(otpauth, 360) }

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding)
                    .padding(horizontal = 16.dp)
                    .verticalScroll(rememberScrollState()),
                verticalArrangement = Arrangement.spacedBy(14.dp)
            ) {
                Spacer(modifier = Modifier.height(8.dp))
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    ServiceIconView(
                        issuer = state.issuer,
                        iconSlug = state.iconSlug,
                        modifier = Modifier.size(56.dp)
                    )
                }

                SectionCard(title = "Account") {
                    OutlinedTextField(
                        value = state.issuer,
                        onValueChange = viewModel::onIssuerChanged,
                        label = { Text("Issuer") },
                        singleLine = true,
                        modifier = Modifier.fillMaxWidth(),
                        colors = outlinedColors()
                    )
                    Spacer(modifier = Modifier.height(10.dp))
                    OutlinedTextField(
                        value = state.accountName,
                        onValueChange = viewModel::onAccountNameChanged,
                        label = { Text("Account name") },
                        singleLine = true,
                        modifier = Modifier.fillMaxWidth(),
                        colors = outlinedColors()
                    )
                }

                SectionCard(title = "Secret Key") {
                    OutlinedTextField(
                        value = state.secretKey,
                        onValueChange = viewModel::onSecretChanged,
                        label = { Text("Secret key (Base32)") },
                        visualTransformation = if (state.secretVisible) VisualTransformation.None else PasswordVisualTransformation(),
                        trailingIcon = {
                            IconButton(onClick = viewModel::toggleSecretVisibility) {
                                Icon(
                                    imageVector = if (state.secretVisible) Icons.Default.VisibilityOff else Icons.Default.Visibility,
                                    contentDescription = "Toggle secret visibility"
                                )
                            }
                        },
                        isError = state.secretError != null,
                        supportingText = {
                            state.secretError?.let { Text(it, color = MaterialTheme.colorScheme.error) }
                        },
                        singleLine = true,
                        modifier = Modifier.fillMaxWidth(),
                        colors = outlinedColors()
                    )
                }

                SectionCard(title = "Details") {
                    ReadOnlyRow(label = "Algorithm", value = state.algorithm)
                    ReadOnlyRow(label = "Digits", value = state.digits.toString())
                    ReadOnlyRow(label = "Period", value = if (state.type == "TOTP") "${state.period}s" else "-")
                    ReadOnlyRow(label = "Type", value = state.type)
                    ReadOnlyRow(label = "Created", value = DateFormat.format("yyyy-MM-dd HH:mm", state.createdAt).toString())
                }

                SectionCard(title = "Scan to transfer") {
                    Column(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        androidx.compose.foundation.Image(
                            bitmap = qrBitmap,
                            contentDescription = "Transfer QR",
                            modifier = Modifier.size(160.dp)
                        )
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))
            }
        }
    }

    if (state.showDiscardDialog) {
        AlertDialog(
            onDismissRequest = viewModel::dismissDiscardDialog,
            title = { Text("Discard changes?") },
            text = { Text("You have unsaved changes. Discard them and go back?") },
            confirmButton = {
                Button(onClick = {
                    viewModel.discardChanges()
                    onNavigateBack()
                }) {
                    Text("Discard")
                }
            },
            dismissButton = {
                TextButton(onClick = viewModel::dismissDiscardDialog) {
                    Text("Keep editing")
                }
            }
        )
    }
}

@Composable
private fun SectionCard(
    title: String,
    content: @Composable () -> Unit
) {
    Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
        Text(text = title, style = MaterialTheme.typography.titleMedium, color = MaterialTheme.colorScheme.primary)
        Card {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(12.dp)
            ) {
                content()
            }
        }
    }
}

@Composable
private fun ReadOnlyRow(label: String, value: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(text = label, style = MaterialTheme.typography.bodyMedium)
        Text(text = value, style = MaterialTheme.typography.bodyMedium, textAlign = TextAlign.End)
    }
}

@Composable
private fun outlinedColors() = OutlinedTextFieldDefaults.colors(
    focusedBorderColor = MaterialTheme.colorScheme.primary,
    focusedLabelColor = MaterialTheme.colorScheme.primary,
    cursorColor = MaterialTheme.colorScheme.primary,
    focusedTrailingIconColor = MaterialTheme.colorScheme.primary,
    focusedTextColor = MaterialTheme.colorScheme.onSurface,
    unfocusedTextColor = MaterialTheme.colorScheme.onSurface,
    errorCursorColor = MaterialTheme.colorScheme.error
)

private fun buildOtpAuthUri(state: EditAccountUiState): String {
    val encodedIssuer = android.net.Uri.encode(state.issuer)
    val encodedAccount = android.net.Uri.encode(state.accountName)
    val secret = state.secretKey.replace(" ", "").replace("-", "").uppercase().trimEnd('=')
    val base = "otpauth://${state.type.lowercase()}/$encodedIssuer:$encodedAccount?secret=$secret&issuer=$encodedIssuer&algorithm=${state.algorithm}&digits=${state.digits}"
    return if (state.type == "TOTP") {
        "$base&period=${state.period}"
    } else {
        "$base&counter=${state.counter}"
    }
}
