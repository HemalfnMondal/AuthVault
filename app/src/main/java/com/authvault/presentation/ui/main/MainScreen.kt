package com.authvault.presentation.ui.main

import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectDragGesturesAfterLongPress
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.BottomSheetDefaults
import androidx.compose.material3.Button
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import android.net.Uri
import androidx.compose.ui.platform.LocalContext
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.consumePositionChange
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalClipboardManager
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.hilt.navigation.compose.hiltViewModel
import com.authvault.presentation.model.AccountUiModel
import com.authvault.presentation.theme.Cyan
import com.authvault.presentation.ui.update.UpdateViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import androidx.compose.ui.platform.LocalLifecycleOwner

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(
    viewModel: MainViewModel = hiltViewModel(),
    updateViewModel: UpdateViewModel = hiltViewModel(),
    onOpenSettings: () -> Unit,
    onAddAccount: () -> Unit,
    onOpenDetail: (Int) -> Unit,
    onNavigateToScan: () -> Unit
) {
    val state by viewModel.uiState.collectAsState()
    val context = LocalContext.current
    val snackbarHostState = remember { SnackbarHostState() }
    val clipboardManager = LocalClipboardManager.current
    val scope = rememberCoroutineScope()
    val lifecycleOwner = LocalLifecycleOwner.current
    var dragIndex by rememberSaveable { mutableStateOf(-1) }
    val orderIds = remember { mutableStateListOf<Int>() }

    LaunchedEffect(state.accounts.map { it.id }) {
        if (dragIndex == -1) {
            orderIds.clear()
            orderIds.addAll(state.accounts.map { it.id })
        }
    }

    LaunchedEffect(Unit) {
        viewModel.events().collect { event ->
            when (event) {
                is MainUiEvent.CopyToClipboard -> {
                    clipboardManager.setText(AnnotatedString(event.text))
                    scope.launch {
                        delay(state.settings.clipboardClearDelaySeconds * 1000L)
                        clipboardManager.setText(AnnotatedString(""))
                    }
                }
                is MainUiEvent.ShowSnackbar -> snackbarHostState.showSnackbar(event.message)
            }
        }
    }

    DisposableEffect(lifecycleOwner) {
        val observer = LifecycleEventObserver { _, event ->
            if (event == Lifecycle.Event.ON_RESUME) {
                updateViewModel.checkForUpdatesIfDue()
            }
        }
        lifecycleOwner.lifecycle.addObserver(observer)
        onDispose { lifecycleOwner.lifecycle.removeObserver(observer) }
    }

    val imagePickerLauncher = rememberLauncherForActivityResult(ActivityResultContracts.GetContent()) { uri: Uri? ->
        uri?.let { viewModel.decodeAndSaveFromImage(context, it) }
    }

    val cameraPermissionLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission()
    ) { isGranted: Boolean ->
        if (isGranted) {
            onNavigateToScan()
        } else {
            scope.launch { snackbarHostState.showSnackbar("Camera permission required to scan QR codes") }
        }
    }

    LaunchedEffect(Unit) {
        viewModel.imagePickerRequests.collect { imagePickerLauncher.launch("image/*") }
    }

    LaunchedEffect(Unit) {
        viewModel.scanRequests.collect {
            when {
                androidx.core.content.ContextCompat.checkSelfPermission(
                    context,
                    android.Manifest.permission.CAMERA
                ) == android.content.pm.PackageManager.PERMISSION_GRANTED -> {
                    onNavigateToScan()
                }
                else -> cameraPermissionLauncher.launch(android.Manifest.permission.CAMERA)
            }
        }
    }

    Scaffold(
        snackbarHost = { SnackbarHost(hostState = snackbarHostState) },
        floatingActionButton = {
            FloatingActionButton(onClick = onAddAccount) {
                Icon(imageVector = Icons.Default.Add, contentDescription = "Add account")
            }
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(horizontal = 16.dp)
                .navigationBarsPadding()
        ) {
            TopBar(
                searchExpanded = state.searchExpanded,
                searchQuery = state.searchQuery,
                onSearchClicked = viewModel::onSearchClicked,
                onSearchQueryChanged = viewModel::onSearchQueryChanged,
                onSettingsClicked = onOpenSettings
            )
            Spacer(modifier = Modifier.height(12.dp))

            if (state.accounts.isEmpty()) {
                EmptyState(onAddAccount = onAddAccount)
            } else {
                LazyColumn(
                    modifier = Modifier.fillMaxSize(),
                    contentPadding = PaddingValues(bottom = 96.dp),
                    verticalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    itemsIndexed(
                        items = state.accounts,
                        key = { _, item -> item.id }
                    ) { index, account ->
                        val dragModifier = Modifier.pointerInput(account.id, state.reorderMode) {
                            if (!state.reorderMode) return@pointerInput
                            detectDragGesturesAfterLongPress(
                                onDragStart = { dragIndex = index },
                                onDragEnd = {
                                    dragIndex = -1
                                    viewModel.reorder(orderIds.toList())
                                },
                                onDragCancel = { dragIndex = -1 },
                                onDrag = { change, dragAmount ->
                                    change.consumePositionChange()
                                    val currentIndex = dragIndex
                                    if (currentIndex >= 0) {
                                        val direction = when {
                                            dragAmount.y > 30 -> 1
                                            dragAmount.y < -30 -> -1
                                            else -> 0
                                        }
                                        val targetIndex = currentIndex + direction
                                        if (direction != 0 && targetIndex in orderIds.indices) {
                                            orderIds.removeAt(currentIndex)
                                            orderIds.add(targetIndex, account.id)
                                            dragIndex = targetIndex
                                        }
                                    }
                                }
                            )
                        }

                        AccountCard(
                            account = account,
                            isReorderMode = state.reorderMode,
                            onCopyCode = { viewModel.onCodeClicked(account) },
                            onDelete = { viewModel.onDeleteRequested(account) },
                            onDetails = { onOpenDetail(account.id) },
                            onLongPress = { viewModel.toggleReorderMode() },
                            onDragHandle = dragModifier
                        )
                    }
                }
            }
        }
    }

    state.deleteTarget?.let { target ->
        DeleteAccountSheet(
            account = target,
            onCancel = viewModel::dismissDeleteSheet,
            onDelete = viewModel::confirmDelete
        )
    }
}

@Composable
private fun TopBar(
    searchExpanded: Boolean,
    searchQuery: String,
    onSearchClicked: () -> Unit,
    onSearchQueryChanged: (String) -> Unit,
    onSettingsClicked: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = "AuthVault",
            style = MaterialTheme.typography.headlineSmall,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.weight(1f)
        )
        IconButton(onClick = onSearchClicked) {
            Icon(imageVector = Icons.Default.Search, contentDescription = "Search")
        }
        IconButton(onClick = onSettingsClicked) {
            Icon(imageVector = Icons.Default.Settings, contentDescription = "Settings")
        }
    }
    if (searchExpanded) {
        OutlinedTextField(
            value = searchQuery,
            onValueChange = onSearchQueryChanged,
            modifier = Modifier.fillMaxWidth(),
            placeholder = { Text("Search by issuer or account") },
            singleLine = true
        )
    }
}

@Composable
private fun EmptyState(onAddAccount: () -> Unit) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Icon(
            imageVector = Icons.Default.Lock,
            contentDescription = null,
            modifier = Modifier.size(56.dp),
            tint = Cyan
        )
        Spacer(modifier = Modifier.height(12.dp))
        Text(text = "No accounts yet", style = MaterialTheme.typography.titleMedium)
        Spacer(modifier = Modifier.height(4.dp))
        Text(text = "Add your first account", style = MaterialTheme.typography.bodyMedium)
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = onAddAccount) {
            Text("Add your first account")
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun DeleteAccountSheet(
    account: AccountUiModel,
    onCancel: () -> Unit,
    onDelete: () -> Unit
) {
    ModalBottomSheet(onDismissRequest = onCancel, dragHandle = { BottomSheetDefaults.DragHandle() }) {
        Column(modifier = Modifier.padding(16.dp)) {
            Icon(
                imageVector = Icons.Default.Lock,
                contentDescription = null,
                tint = androidx.compose.ui.graphics.Color.Red,
                modifier = Modifier.size(32.dp)
            )
            Spacer(modifier = Modifier.height(12.dp))
            Text(text = "Delete Account?", style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = "${account.issuer} • ${account.accountName}")
            Spacer(modifier = Modifier.height(4.dp))
            Text(text = "This action cannot be undone.")
            Spacer(modifier = Modifier.height(16.dp))
            Row(horizontalArrangement = Arrangement.spacedBy(12.dp), modifier = Modifier.fillMaxWidth()) {
                OutlinedButton(onClick = onCancel, modifier = Modifier.weight(1f)) {
                    Text("Cancel")
                }
                Button(onClick = onDelete, modifier = Modifier.weight(1f)) {
                    Text("Delete")
                }
            }
            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}
