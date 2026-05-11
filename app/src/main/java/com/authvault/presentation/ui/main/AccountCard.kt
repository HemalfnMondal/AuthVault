package com.authvault.presentation.ui.main

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.clickable
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ContentCopy
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.DragHandle
import androidx.compose.material.icons.outlined.Edit
import androidx.compose.material.icons.outlined.DeleteOutline
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.authvault.presentation.model.AccountUiModel
import com.authvault.presentation.theme.Cyan
import com.authvault.presentation.theme.DeepBlue
import com.authvault.presentation.theme.MutedTextDark
import com.authvault.presentation.theme.CardDark
import com.authvault.presentation.theme.CardLight
import com.authvault.presentation.theme.DividerDark
import com.authvault.presentation.theme.DividerLight
import com.authvault.presentation.ui.common.ServiceIconView
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.BorderStroke

@OptIn(androidx.compose.foundation.ExperimentalFoundationApi::class)
@Composable
fun AccountCard(
    account: AccountUiModel,
    isReorderMode: Boolean,
    onCopyCode: () -> Unit,
    onDelete: () -> Unit,
    onDetails: () -> Unit,
    onLongPress: () -> Unit,
    onDragHandle: Modifier = Modifier
) {
    val isDarkMode = isSystemInDarkTheme()
    val cardBackground = if (isDarkMode) CardDark else CardLight
    val cardBorder = if (isDarkMode) Color(0xFF424242) else Color(0xFFE0E0E0)
    val issuerTextColor = if (isDarkMode) Color(0xFFE0E0E0) else Color(0xFF212121)
    val accountTextColor = if (isDarkMode) Color(0xFFB0BEC5) else Color(0xFF757575)
    val codeTextColor = if (isDarkMode) Cyan else DeepBlue
    val deleteIconColor = if (isDarkMode) Color(0xFFEF5350) else Color(0xFFD32F2F)
    val dividerColor = if (isDarkMode) Color(0xFF2C2C2C) else Color(0xFFE0E0E0)

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .combinedClickable(
                onClick = onCopyCode,
                onLongClick = onLongPress
            )
            .padding(horizontal = 0.dp),
        shape = RoundedCornerShape(10.dp),
        colors = CardDefaults.cardColors(containerColor = cardBackground),
        border = BorderStroke(1.dp, cardBorder),
        elevation = CardDefaults.cardElevation(defaultElevation = if (isDarkMode) 0.dp else 2.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 12.dp, vertical = 8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(modifier = Modifier.size(36.dp), contentAlignment = Alignment.Center) {
                ServiceIconView(issuer = account.issuer, iconSlug = account.iconSlug, modifier = Modifier.size(36.dp))
            }
            Spacer(modifier = Modifier.width(8.dp))
            Column(modifier = Modifier.weight(1f)) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(
                        text = account.issuer,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = issuerTextColor,
                        maxLines = 1,
                        modifier = Modifier.weight(1f)
                    )
                    IconButton(onClick = onDetails, modifier = Modifier.size(20.dp)) {
                        Icon(
                            imageVector = Icons.Outlined.Edit,
                            contentDescription = "Edit account",
                            tint = accountTextColor,
                            modifier = Modifier.size(16.dp)
                        )
                    }
                }
                Spacer(modifier = Modifier.height(1.dp))
                Text(
                    text = account.accountName,
                    fontSize = 11.sp,
                    color = accountTextColor,
                    maxLines = 1
                )
                Spacer(modifier = Modifier.height(6.dp))
                Text(
                    text = formatCode(account.code),
                    fontSize = 22.sp,
                    fontWeight = FontWeight.Bold,
                    fontFamily = FontFamily.Monospace,
                    color = codeTextColor,
                    letterSpacing = 2.sp,
                    modifier = Modifier.clickable(onClick = onCopyCode)
                )
            }
            Spacer(modifier = Modifier.width(8.dp))
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                CountdownRing(countdown = account.countdown, period = account.period, size = 40.dp)
                Spacer(modifier = Modifier.height(2.dp))
                IconButton(onClick = onDelete, modifier = Modifier.size(24.dp)) {
                    Icon(
                        imageVector = Icons.Outlined.DeleteOutline,
                        contentDescription = "Delete account",
                        tint = deleteIconColor,
                        modifier = Modifier.size(18.dp)
                    )
                }
                if (isReorderMode) {
                    Icon(
                        imageVector = Icons.Filled.DragHandle,
                        contentDescription = "Drag handle",
                        tint = DeepBlue,
                        modifier = onDragHandle
                            .size(24.dp)
                    )
                }
            }
        }
    }
}

@Composable
private fun CountdownRing(countdown: Int, period: Int, size: Dp = 44.dp, modifier: Modifier = Modifier) {
    val isDarkMode = isSystemInDarkTheme()
    val progress = countdown.toFloat() / period.toFloat()

    val ringColor = when {
        countdown > 10 -> if (isDarkMode) Cyan else DeepBlue
        countdown > 5 -> Color(0xFFFF9800)
        else -> Color(0xFFEF5350)
    }

    val animatedColor by animateColorAsState(targetValue = ringColor, animationSpec = tween(durationMillis = 500))
    val animatedProgress by animateFloatAsState(targetValue = progress, animationSpec = tween(durationMillis = 800))

    val trackColor = if (isDarkMode) DividerDark else DividerLight

    Box(contentAlignment = Alignment.Center, modifier = modifier.size(size)) {
        Canvas(modifier = Modifier.size(size)) {
            val strokeWidth = 3.5.dp.toPx()
            val diameter = size.toPx() - strokeWidth
            val topLeft = androidx.compose.ui.geometry.Offset(strokeWidth / 2, strokeWidth / 2)
            val arcSize = androidx.compose.ui.geometry.Size(diameter, diameter)

            drawArc(
                color = trackColor,
                startAngle = -90f,
                sweepAngle = 360f,
                useCenter = false,
                topLeft = topLeft,
                size = arcSize,
                style = Stroke(width = strokeWidth)
            )

            drawArc(
                color = animatedColor,
                startAngle = -90f,
                sweepAngle = 360f * animatedProgress.toFloat(),
                useCenter = false,
                topLeft = topLeft,
                size = arcSize,
                style = Stroke(width = strokeWidth)
            )
        }

        Text(
            text = countdown.toString(),
            fontSize = when {
                size >= 48.dp -> 14.sp
                size >= 44.dp -> 13.sp
                else -> 11.sp
            },
            fontWeight = FontWeight.Bold,
            color = animatedColor,
            textAlign = TextAlign.Center
        )
    }
}

// Format code as "123 456" for 6 digits or "1234 5678" for 8 digits
private fun formatCode(code: String): String {
    return when (code.length) {
        6 -> "${code.substring(0, 3)} ${code.substring(3)}"
        8 -> "${code.substring(0, 4)} ${code.substring(4)}"
        else -> code
    }
}
