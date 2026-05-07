package com.authvault.presentation.ui.common

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.SubcomposeAsyncImage
import coil.compose.SubcomposeAsyncImageContent
import kotlin.math.absoluteValue

@Composable
fun ServiceIconView(issuer: String, iconSlug: String?, modifier: Modifier = Modifier) {
    val resolvedSlug = iconSlug ?: iconSlugForIssuer(issuer)
    android.util.Log.d("ServiceIcon", "issuer='$issuer' iconSlug='$iconSlug' resolvedSlug='$resolvedSlug'")

    if (resolvedSlug != null) {
        val iconUrl = "https://cdn.simpleicons.org/$resolvedSlug"
        SubcomposeAsyncImage(
            model = iconUrl,
            contentDescription = issuer,
            modifier = modifier
                .clip(RoundedCornerShape(8.dp)),
            loading = {
                android.util.Log.d("ServiceIcon", "loading icon url=$iconUrl")
                Placeholder(issuer, modifier)
            },
            error = {
                android.util.Log.e("ServiceIcon", "failed icon url=$iconUrl")
                Placeholder(issuer, modifier)
            },
            success = {
                android.util.Log.d("ServiceIcon", "loaded icon url=$iconUrl")
                SubcomposeAsyncImageContent()
            }
        )
    } else {
        android.util.Log.d("ServiceIcon", "no icon slug matched for issuer='$issuer', using placeholder")
        Placeholder(issuer, modifier)
    }
}

@Composable
private fun Placeholder(issuer: String, modifier: Modifier) {
    val family = listOf(Color(0xFF1565C0), Color(0xFF00838F), Color(0xFF00BCD4), Color(0xFF1976D2))
    val background = family[(issuer.hashCode().absoluteValue) % family.size]
    Box(
        modifier = modifier
            .clip(CircleShape)
            .background(background),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = issuer.firstOrNull()?.uppercase() ?: "?",
            color = Color.White,
            fontWeight = FontWeight.Bold,
            fontSize = 18.sp
        )
    }
}
