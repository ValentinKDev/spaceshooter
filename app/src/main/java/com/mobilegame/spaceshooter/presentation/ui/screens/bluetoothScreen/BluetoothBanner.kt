package com.mobilegame.spaceshooter.presentation.ui.screens.bluetoothScreen

import androidx.compose.animation.animateColor
import androidx.compose.animation.core.*
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import com.mobilegame.spaceshooter.domain.model.screen.uiAdapter.BluetoothIconAdapter
import com.mobilegame.spaceshooter.presentation.ui.screens.utils.CenterComposable

@Composable
fun BluetoothBanner(ui: BluetoothIconAdapter) {
    Box(
        androidx.compose.ui.Modifier
            .size(ui.sizes.squareHeightDp)
    ) {
        CenterComposable {
            val infiniteTransition = rememberInfiniteTransition()
            val animateColor by infiniteTransition.animateColor(
                initialValue = ui.colors.transparentStart,
                targetValue = ui.colors.transparentTarget,
                animationSpec = infiniteRepeatable(
                    tween(durationMillis = 1500, easing = FastOutLinearInEasing),
                    RepeatMode.Reverse
                )
            )

            Canvas(
                androidx.compose.ui.Modifier
                    .size(ui.sizes.canvasDp)
            ) {
                val bluetoothSymbol = Path().apply {
                    moveTo(ui.points.p2.x, ui.points.p2.y)
                    lineTo(ui.points.p5.x, ui.points.p5.y)
                    lineTo(ui.points.p6.x, ui.points.p6.y)
                    lineTo(ui.points.p1.x, ui.points.p1.y)
                    lineTo(ui.points.p3.x, ui.points.p3.y)
                    lineTo(ui.points.p4.x, ui.points.p4.y)

                }

                drawPath(
                    color = animateColor,
                    path = bluetoothSymbol,
                    style = Stroke(ui.sizes.iconSmallStroke, cap = StrokeCap.Square)
                )
            }
        }
    }
}