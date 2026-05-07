package com.authvault.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.authvault.presentation.ui.add.AddMethodSheet
import com.authvault.presentation.ui.add.AddViewModel
import com.authvault.presentation.ui.add.ManualEntryScreen
import com.authvault.presentation.ui.add.ScanQrScreen
// Upload flow converted to direct image picker; UploadQrScreen removed
import com.authvault.presentation.ui.detail.AccountDetailScreen
import com.authvault.presentation.ui.detail.DetailViewModel
import com.authvault.presentation.ui.edit.EditAccountScreen
import com.authvault.presentation.ui.edit.EditAccountViewModel
import com.authvault.presentation.ui.main.MainScreen
import com.authvault.presentation.ui.main.MainViewModel
import com.authvault.presentation.ui.settings.Mode
import com.authvault.presentation.ui.settings.BackupScreen
import com.authvault.presentation.ui.settings.SettingsScreen
import com.authvault.presentation.ui.settings.SettingsViewModel

@Composable
fun AppNavGraph() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Screen.Main.route) {
        composable(Screen.Main.route) {
            val viewModel = hiltViewModel<MainViewModel>()
            MainScreen(
                viewModel = viewModel,
                onOpenSettings = { navController.navigate(Screen.Settings.route) },
                onAddAccount = { navController.navigate(Screen.AddMethod.route) },
                onOpenDetail = { accountId -> navController.navigate(Screen.EditAccount.createRoute(accountId)) },
                onNavigateToScan = { navController.navigate(Screen.ScanQr.route) }
            )
        }
        composable(Screen.Settings.route) {
            val viewModel = hiltViewModel<SettingsViewModel>()
            SettingsScreen(
                viewModel = viewModel,
                onNavigateBack = { navController.popBackStack() },
                onExportBackup = { navController.navigate(Screen.ExportBackup.route) },
                onImportBackup = { navController.navigate(Screen.ImportBackup.route) }
            )
        }
        composable(Screen.ExportBackup.route) {
            val viewModel = hiltViewModel<SettingsViewModel>()
            BackupScreen(
                viewModel = viewModel,
                mode = Mode.Export,
                onNavigateBack = { navController.popBackStack() }
            )
        }
        composable(Screen.ImportBackup.route) {
            val viewModel = hiltViewModel<SettingsViewModel>()
            BackupScreen(
                viewModel = viewModel,
                mode = Mode.Import,
                onNavigateBack = { navController.popBackStack() }
            )
        }
        composable(Screen.AddMethod.route) {
            val addViewModel = hiltViewModel<AddViewModel>()
            val parentEntry = remember(navController) { navController.getBackStackEntry(Screen.Main.route) }
            val mainViewModel = hiltViewModel<MainViewModel>(parentEntry)
            AddMethodSheet(
                viewModel = addViewModel,
                onNavigateBack = { navController.popBackStack() },
                onScanQr = {
                    // dismiss sheet and request MainScreen to handle permission + navigation
                    navController.popBackStack()
                    mainViewModel.requestScan()
                },
                onUploadImage = {
                    // close sheet and request MainScreen to open image picker
                    navController.popBackStack()
                    mainViewModel.requestImagePick()
                },
                onManualEntry = { navController.navigate(Screen.ManualEntry.route) }
            )
        }
        composable(Screen.ScanQr.route) {
            val parentEntry = remember(navController) { navController.getBackStackEntry(Screen.Main.route) }
            val mainViewModel = hiltViewModel<MainViewModel>(parentEntry)
            ScanQrScreen(
                viewModel = mainViewModel,
                onNavigateBack = { navController.popBackStack() },
                onNavigateHome = {
                    navController.navigate(Screen.Main.route) {
                        popUpTo(Screen.Main.route) { inclusive = true }
                    }
                }
            )
        }
        // Upload QR route removed; image upload now handled via system picker and MainViewModel
        composable(Screen.ManualEntry.route) {
            val viewModel = hiltViewModel<AddViewModel>()
            ManualEntryScreen(
                viewModel = viewModel,
                onNavigateBack = { navController.popBackStack() },
                onAccountSaved = { navController.navigate(Screen.ConfirmAccount.route) }
            )
        }
        composable(Screen.ConfirmAccount.route) {
            val viewModel = hiltViewModel<AddViewModel>()
            com.authvault.presentation.ui.add.ConfirmAccountScreen(
                viewModel = viewModel,
                onCancel = { navController.popBackStack() },
                onAdded = { navController.popBackStack(Screen.Main.route, false) }
            )
        }
        composable(
            route = Screen.EditAccount.route,
            arguments = listOf(navArgument("accountId") { type = NavType.IntType })
        ) {
            val viewModel = hiltViewModel<EditAccountViewModel>()
            EditAccountScreen(
                viewModel = viewModel,
                onNavigateBack = { navController.popBackStack() },
                onSaved = {
                    navController.popBackStack(Screen.Main.route, false)
                }
            )
        }
        composable(
            route = Screen.Detail.route,
            arguments = listOf(navArgument("accountId") { type = NavType.IntType })
        ) {
            val viewModel = hiltViewModel<DetailViewModel>()
            AccountDetailScreen(
                viewModel = viewModel,
                onNavigateBack = { navController.popBackStack() }
            )
        }
    }
}
