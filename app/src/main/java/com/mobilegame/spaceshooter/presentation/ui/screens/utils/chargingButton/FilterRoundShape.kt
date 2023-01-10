package com.mobilegame.spaceshooter.presentation.ui.screens.utils.chargingButton

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.DpSize
import com.mobilegame.spaceshooter.presentation.theme.MyColor

@Composable
internal fun FilterRoundShape(sizeDp: DpSize) {
    Box(
        Modifier
            .size(sizeDp)
    ) {
        Canvas(
            Modifier
                .fillMaxSize()
                .graphicsLayer(alpha = 0.99f)
        ) {
            drawRect(
                color = MyColor.applicationBackground,
                size = size,
                blendMode = BlendMode.Xor
            )
            drawCircle(
                color = Color.Black,
                blendMode = BlendMode.Xor
            )
        }
    }
}