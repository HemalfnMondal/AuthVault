package com.authvault.presentation.ui.update

import androidx.activity.compose.BackHandler
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import com.authvault.data.update.UpdateInfo

@Composable
fun UpdateDialog(
    updateInfo: UpdateInfo,
    onUpdateNow: () -> Unit,
    onLater: () -> Unit
) {
    val forceUpdate = updateInfo.forceUpdate
    BackHandler(enabled = forceUpdate) { }

    AlertDialog(
        onDismissRequest = {
            if (!forceUpdate) onLater()
        },
        title = { Text("Update Available") },
        text = {
            Text("Version ${updateInfo.versionName} is available.\n\n${updateInfo.releaseNotes}")
        },
        confirmButton = {
            Button(onClick = onUpdateNow) {
                Text("Update Now")
            }
        },
        dismissButton = if (forceUpdate) {
            null
        } else {
            {
                TextButton(onClick = onLater) {
                    Text("Later")
                }
            }
        }
    )
}
