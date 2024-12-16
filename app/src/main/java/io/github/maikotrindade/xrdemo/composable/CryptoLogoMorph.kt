
package io.github.maikotrindade.xrdemo.composable

import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import io.github.maikotrindade.xrdemo.R

@Composable
fun CryptoAnimation(modifier: Modifier) {
    var isBitcoin by remember { mutableStateOf(true) }

    // Animation properties
    val scale by animateFloatAsState(
        targetValue = if (isBitcoin) 1f else 0.5f,
        animationSpec = tween(durationMillis = 1200, easing = FastOutSlowInEasing)
    )
    val rotation by animateFloatAsState(
        targetValue = if (isBitcoin) 0f else 360f,
        animationSpec = tween(durationMillis = 1400, easing = FastOutSlowInEasing)
    )
    val alpha by animateFloatAsState(
        targetValue = if (isBitcoin) 1f else 0f,
        animationSpec = tween(durationMillis = 800)
    )

    Box(
        modifier = modifier
            .fillMaxSize()
            .clickable { isBitcoin = !isBitcoin },
        contentAlignment = Alignment.Center
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_btc),
            contentDescription = "Bitcoin Logo",
            modifier = Modifier
                .size(400.dp)
                .graphicsLayer(
                    scaleX = scale,
                    scaleY = scale,
                    rotationZ = rotation,
                    alpha = alpha
                )
        )
        Image(
            painter = painterResource(id = R.drawable.ic_eth),
            contentDescription = "Ethereum Logo",
            modifier = Modifier
                .size(400.dp)
                .graphicsLayer(
                    scaleX = 1f - scale,
                    scaleY = 1f - scale,
                    rotationZ = -rotation,
                    alpha = 1f - alpha
                )
        )
    }
}