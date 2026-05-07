package com.authvault.presentation.ui.add

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.clickable
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CameraAlt
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Image
import androidx.compose.material3.BottomSheetDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.ListItem
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddMethodSheet(
    viewModel: AddViewModel = hiltViewModel(),
    onNavigateBack: () -> Unit,
    onScanQr: () -> Unit,
    onUploadImage: () -> Unit,
    onManualEntry: () -> Unit
) {
    ModalBottomSheet(onDismissRequest = onNavigateBack, dragHandle = { BottomSheetDefaults.DragHandle() }) {
        Column(modifier = Modifier.padding(bottom = 16.dp)) {
            Text(text = "Add Account", modifier = Modifier.padding(horizontal = 16.dp))
            Spacer(modifier = Modifier.height(8.dp))
            ListItem(
                headlineContent = { Text("Scan QR Code") },
                leadingContent = { Icon(Icons.Default.CameraAlt, contentDescription = null) },
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable(onClick = onScanQr),
                tonalElevation = 0.dp,
                shadowElevation = 0.dp,
                supportingContent = { Text("Use the camera to scan an otpauth QR code") }
            )
            Divider()
            ListItem(
                headlineContent = { Text("Upload QR Image") },
                leadingContent = { Icon(Icons.Default.Image, contentDescription = null) },
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable(onClick = onUploadImage),
                supportingContent = { Text("Pick a screenshot or saved QR image") },
                tonalElevation = 0.dp,
                shadowElevation = 0.dp,
            )
            Divider()
            ListItem(
                headlineContent = { Text("Enter Manually") },
                leadingContent = { Icon(Icons.Default.Edit, contentDescription = null) },
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable(onClick = onManualEntry),
                supportingContent = { Text("Type the service, username, and secret key") },
                tonalElevation = 0.dp,
                shadowElevation = 0.dp,
            )
            Divider()
        }
    }
}
