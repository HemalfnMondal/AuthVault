package com.authvault.presentation.ui.add

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.Button
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import kotlinx.coroutines.flow.collectLatest

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ManualEntryScreen(
    viewModel: AddViewModel = hiltViewModel(),
    onNavigateBack: () -> Unit,
    onAccountSaved: () -> Unit
) {
    val state by viewModel.uiState.collectAsState()
    LaunchedEffect(Unit) {
        viewModel.events.collectLatest { event ->
            when (event) {
                AddUiEvent.Saved -> onAccountSaved()
                is AddUiEvent.Error -> Unit
            }
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Manual Entry") },
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
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            OutlinedTextField(
                value = state.manual.issuer,
                onValueChange = viewModel::onIssuerChanged,
                modifier = Modifier.fillMaxWidth(),
                label = { Text("Issuer / Service Name") },
                singleLine = true
            )
            OutlinedTextField(
                value = state.manual.accountName,
                onValueChange = viewModel::onAccountChanged,
                modifier = Modifier.fillMaxWidth(),
                label = { Text("Account") },
                singleLine = true
            )
            OutlinedTextField(
                value = state.manual.secretKey,
                onValueChange = viewModel::onSecretChanged,
                modifier = Modifier.fillMaxWidth(),
                label = { Text("Secret Key (Base32)") },
                singleLine = true,
                isError = state.manual.secretError != null,
                supportingText = { state.manual.secretError?.let { Text(it) } },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Ascii)
            )
            // Algorithm, digits, period and type are auto-detected — do not show fields here.
            Spacer(modifier = Modifier.height(8.dp))
            Button(onClick = {
                viewModel.prepareManualForConfirm()
                onAccountSaved()
            }, enabled = state.manual.canSave, modifier = Modifier.fillMaxWidth()) {
                Text("Review & Add")
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun SimpleDropdown(
    label: String,
    value: String,
    options: List<String>,
    onSelected: (String) -> Unit
) {
    var expanded by remember { mutableStateOf(false) }
    ExposedDropdownMenuBox(expanded = expanded, onExpandedChange = { expanded = !expanded }) {
        OutlinedTextField(
            value = value,
            onValueChange = {},
            modifier = Modifier
                .fillMaxWidth()
                .menuAnchor(),
            label = { Text(label) },
            readOnly = true,
            trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded) }
        )
        ExposedDropdownMenu(expanded = expanded, onDismissRequest = { expanded = false }) {
            options.forEach { option ->
                DropdownMenuItem(
                    text = { Text(option) },
                    onClick = {
                        onSelected(option)
                        expanded = false
                    }
                )
            }
        }
    }
}
