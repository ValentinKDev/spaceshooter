package com.mobilegame.spaceshooter.presentation.ui.screens.inGameScreen.backGrounds

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.mobilegame.spaceshooter.logic.model.screen.inGameScreens.motions.MotionsViewModel
import com.mobilegame.spaceshooter.logic.uiHandler.screens.games.background.BackgroundUI
import com.mobilegame.spaceshooter.logic.uiHandler.screens.games.background.Star

@Composable
fun AnimatedBackGroundInMotion(ui: BackgroundUI, motionVM: MotionsViewModel) {
    val offset by remember { motionVM.backgroundDpOffset }.collectAsState()
    val infiniteTransition = rememberInfiniteTransition()
    val matrix: Array<Array<Star>> = remember { ui.matrix.matrix }
    val alphaColor by infiniteTransition.animateFloat(
        initialValue = ui.matrix.initialColorValue,
        targetValue = ui.matrix.targetColorValue,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 3500, easing = LinearEasing),
            repeatMode = RepeatMode.Reverse
        )
    )

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
    ) {
        Canvas(
            modifier = Modifier
                .size(ui.matrix.littleStarDp)
                .offset(offset.x, offset.y)
        ) {
            for (arrayLine in matrix) {
                for (star in arrayLine) {
                    star.bigOrNull()?.let {
                        for (patternOffset in star.patternArray) {
                            drawRect(
                                topLeft = patternOffset,
                                size = ui.matrix.dotCanvasSize,
                                color = ui.shipType.info.color,
                                alpha = alphaColor,
                            )
                        }
                    } ?: drawRect(
                        topLeft = star.anchor,
                        size = ui.matrix.dotCanvasSize,
//                        color = ui.matrix.color,
                        color = ui.shipType.info.color,
                            alpha = alphaColor,
                    )
                }
            } 
       }
    }
}

@Composable
fun AnimatedBackGroundStatic(ui: BackgroundUI) {
    val infiniteTransition = rememberInfiniteTransition()
    val alphaColor by infiniteTransition.animateFloat(
        initialValue = ui.matrix.initialColorValue,
        targetValue = ui.matrix.targetColorValue,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 3500, easing = LinearEasing),
            repeatMode = RepeatMode.Reverse
        )
    )

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
    ) {
        Canvas(
            modifier = Modifier
                .size(ui.matrix.littleStarDp)
        ) {
            for (arrayLine in ui.matrix.matrix) {
                for (star in arrayLine) {
                    star.bigOrNull()?.let {
                        for (patternOffset in star.patternArray) {
                            drawRect(
                                topLeft = patternOffset,
                                size = ui.matrix.dotCanvasSize,
                                color = ui.shipType.info.color,
                                alpha = alphaColor,
                            )
                        }
                    } ?: drawRect(
                        topLeft = star.anchor,
                        size = ui.matrix.dotCanvasSize,
                        color = ui.shipType.info.color,
                        alpha = alphaColor,
                    )
                }
            }
        }
    }
}
