package com.authvault.presentation.theme

import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext

private val LightColors = lightColorScheme(
    primary = Color(0xFF1565C0),
    onPrimary = Color.White,
    primaryContainer = Color(0xFFE3F2FD),
    secondary = Color(0xFF00BCD4),
    background = Color(0xFFF5F5F5),
    surface = Color.White,
    surfaceVariant = Color(0xFFF0F4F8),
    onBackground = Color(0xFF1A1A1A),
    onSurface = Color(0xFF1A1A1A),
    onSurfaceVariant = Color(0xFF757575),
    outline = Color(0xFFE0E0E0),
    error = Color(0xFFE53935)
)

private val DarkColors = darkColorScheme(
    primary = Color(0xFF00BCD4),
    onPrimary = Color(0xFF003544),
    primaryContainer = Color(0xFF004D5E),
    secondary = Color(0xFF1565C0),
    background = Color(0xFF0D1B2A),
    surface = Color(0xFF1A2744),
    surfaceVariant = Color(0xFF1E2E4A),
    onBackground = Color(0xFFE0E0E0),
    onSurface = Color(0xFFE0E0E0),
    onSurfaceVariant = Color(0xFF9E9E9E),
    outline = Color(0xFF2A3A5C),
    error = Color(0xFFEF5350)
)

@Composable
fun AuthVaultTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    dynamicColor: Boolean = false,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }
        darkTheme -> DarkColors
        else -> LightColors
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = AuthVaultTypography,
        content = content
    )
}
