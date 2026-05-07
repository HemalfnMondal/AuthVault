package com.authvault.presentation.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

val AuthVaultTypography = Typography(
    bodyLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        fontWeight = FontWeight.Normal
    ),
    bodyMedium = TextStyle(
        fontFamily = FontFamily.Default,
        fontSize = 14.sp,
        lineHeight = 20.sp,
        fontWeight = FontWeight.Normal
    ),
    titleMedium = TextStyle(
        fontFamily = FontFamily.Default,
        fontSize = 16.sp,
        lineHeight = 22.sp,
        fontWeight = FontWeight.SemiBold
    ),
    titleSmall = TextStyle(
        fontFamily = FontFamily.Default,
        fontSize = 13.sp,
        lineHeight = 18.sp,
        fontWeight = FontWeight.Normal
    ),
    headlineSmall = TextStyle(
        fontFamily = FontFamily.Default,
        fontSize = 20.sp,
        lineHeight = 28.sp,
        fontWeight = FontWeight.SemiBold
    )
)
