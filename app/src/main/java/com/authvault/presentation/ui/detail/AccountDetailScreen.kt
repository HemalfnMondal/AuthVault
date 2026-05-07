package com.authvault.presentation.ui.detail

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
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ContentCopy
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalClipboardManager
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.hilt.navigation.compose.hiltViewModel
import com.authvault.presentation.theme.Cyan
import com.authvault.presentation.ui.common.QrCodeGenerator
import com.authvault.presentation.ui.common.ServiceIconView

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AccountDetailScreen(
    viewModel: DetailViewModel = hiltViewModel(),
    onNavigateBack: () -> Unit
) {
    val state by viewModel.uiState.collectAsState()
    val clipboard = LocalClipboardManager.current

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Account Details") },
                navigationIcon = {
                    IconButton(onClick = onNavigateBack) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        }
    ) { padding ->
        val account = state.account
        if (account == null) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text("Loading...")
            }
        } else {
            val otpauth = remember(account) {
                buildString {
                    append("otpauth://")
                    append(account.type.lowercase())
                    append("/")
                    append(account.issuer)
                    append(":")
                    append(account.accountName)
                    append("?secret=")
                    append(account.secretKey)
                    append("&issuer=")
                    append(account.issuer)
                    append("&algorithm=")
                    append(account.algorithm)
                    append("&digits=")
                    append(account.digits)
                    if (account.type == "TOTP") {
                        append("&period=")
                        append(account.period)
                    } else {
                        append("&counter=")
                        append(account.counter)
                    }
                }
            }
            val qrBitmap = remember(otpauth) { QrCodeGenerator.generate(otpauth, 480) }
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding)
                    .padding(16.dp)
                    .verticalScroll(rememberScrollState()),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    ServiceIconView(issuer = account.issuer, iconSlug = account.iconSlug, modifier = Modifier.size(56.dp))
                    Spacer(modifier = Modifier.size(12.dp))
                    Column {
                        Text(account.issuer, style = MaterialTheme.typography.headlineSmall, fontWeight = FontWeight.Bold)
                        Text(account.accountName)
                    }
                }

                SectionTitle("Secret Key")
                Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.fillMaxWidth()) {
                    Text(
                        text = if (state.secretVisible) account.secretKey else "••••••••••",
                        modifier = Modifier.weight(1f),
                        fontFamily = androidx.compose.ui.text.font.FontFamily.Monospace
                    )
                    IconButton(onClick = { viewModel.toggleSecretVisibility() }) {
                        Icon(
                            imageVector = if (state.secretVisible) Icons.Default.VisibilityOff else Icons.Default.Visibility,
                            contentDescription = "Toggle secret"
                        )
                    }
                    IconButton(onClick = { clipboard.setText(AnnotatedString(account.secretKey)) }) {
                        Icon(Icons.Default.ContentCopy, contentDescription = "Copy secret")
                    }
                }

                SectionTitle("QR Code")
                androidx.compose.foundation.Image(
                    bitmap = qrBitmap,
                    contentDescription = "QR code",
                    modifier = Modifier.size(240.dp)
                )

                SectionTitle("Details")
                DetailRow("Algorithm", account.algorithm)
                DetailRow("Digits", account.digits.toString())
                DetailRow("Period", if (account.type == "TOTP") "${account.period}s" else "-")
                DetailRow("Counter", if (account.type == "HOTP") account.counter.toString() else "-")
            }
        }
    }
}

@Composable
private fun SectionTitle(text: String) {
    Text(text = text, style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold, color = Cyan)
}

@Composable
private fun DetailRow(label: String, value: String) {
    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
        Text(label, fontWeight = FontWeight.Medium)
        Text(value)
    }
}
