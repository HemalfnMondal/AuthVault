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
    primary = Color(0xFF1976D2),
    onPrimary = Color.White,
    primaryContainer = Color(0xFFE3F2FD),
    secondary = Color(0xFF03A9F4),
    background = Color(0xFFFAFAFA),
    surface = Color(0xFFFFFFFF),
    surfaceVariant = Color(0xFFF5F5F5),
    onBackground = Color(0xFF212121),
    onSurface = Color(0xFF212121),
    onSurfaceVariant = Color(0xFF757575),
    outline = Color(0xFFBDBDBD),
    error = Color(0xFFD32F2F)
)

private val DarkColors = darkColorScheme(
    primary = Color(0xFF03A9F4),
    onPrimary = Color(0xFF000000),
    primaryContainer = Color(0xFF0D47A1),
    secondary = Color(0xFF1976D2),
    background = Color(0xFF121212),
    surface = Color(0xFF1E1E1E),
    surfaceVariant = Color(0xFF2C2C2C),
    onBackground = Color(0xFFE0E0E0),
    onSurface = Color(0xFFE0E0E0),
    onSurfaceVariant = Color(0xFFB0BEC5),
    outline = Color(0xFF424242),
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
