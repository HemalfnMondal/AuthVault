package com.authvault.presentation.ui.add

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.IconButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import kotlinx.coroutines.flow.collectLatest
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.authvault.presentation.ui.common.ParsedOtpUri

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ConfirmAccountScreen(
    viewModel: AddViewModel = hiltViewModel(),
    onCancel: () -> Unit,
    onAdded: () -> Unit
) {
    val state by viewModel.uiState.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.events.collectLatest { event ->
            when (event) {
                AddUiEvent.Saved -> onAdded()
                is AddUiEvent.Error -> Unit
            }
        }
    }

    val parsed: ParsedOtpUri? = state.parsedAccount

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Account Found") },
                navigationIcon = {
                    IconButton(onClick = onCancel) {
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
            if (parsed == null) {
                Text("No account to confirm.")
            } else {
                Text(parsed.issuer)
                Text(parsed.accountName)
                Text("${parsed.algorithm} • ${parsed.digits} digits • ${parsed.period}s")
                Column(modifier = Modifier.fillMaxWidth(), verticalArrangement = Arrangement.spacedBy(8.dp)) {
                    Button(onClick = { viewModel.saveParsedAccount() }, modifier = Modifier.fillMaxWidth()) {
                        Text("Add Account")
                    }
                    OutlinedButton(onClick = onCancel, modifier = Modifier.fillMaxWidth()) {
                        Text("Cancel")
                    }
                }
            }
        }
    }
}
