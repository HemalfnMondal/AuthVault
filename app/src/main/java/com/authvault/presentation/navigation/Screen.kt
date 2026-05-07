package com.authvault.presentation.navigation

sealed class Screen(val route: String) {
    data object Main : Screen("main")
    data object Settings : Screen("settings")
    data object ExportBackup : Screen("export_backup")
    data object ImportBackup : Screen("import_backup")
    data object AddMethod : Screen("add_method")
    data object ScanQr : Screen("scan_qr")
    data object ManualEntry : Screen("manual_entry")
    data object ConfirmAccount : Screen("confirm_account")

    data object Detail : Screen("detail/{accountId}") {
        fun createRoute(accountId: Int): String = "detail/$accountId"
    }
}