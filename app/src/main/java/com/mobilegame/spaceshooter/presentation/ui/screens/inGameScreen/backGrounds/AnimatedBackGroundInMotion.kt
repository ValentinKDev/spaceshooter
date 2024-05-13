package com.mobilegame.spaceshooter.presentation.ui.screens.inGameScreen.backGrounds

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
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
import com.mobilegame.spaceshooter.logic.model.screen.inGameScreens.duelGameScreen.HitAnimationViewModel
import com.mobilegame.spaceshooter.logic.model.screen.inGameScreens.motions.MotionsViewModel
import com.mobilegame.spaceshooter.logic.model.screen.inGameScreens.ship.MunitionsViewModel
import com.mobilegame.spaceshooter.logic.uiHandler.screens.games.background.BackgroundUI
import com.mobilegame.spaceshooter.logic.uiHandler.screens.games.background.Star
import com.mobilegame.spaceshooter.presentation.theme.MyColor

@Composable
fun AnimatedBackGroundInMotion(
    ui: BackgroundUI,
    motionVM: MotionsViewModel,
    hitVM: HitAnimationViewModel,
    ammoVM: MunitionsViewModel
) {
    val pairOfIsHitAndColor by remember { hitVM.visibleOpponentColor }.collectAsState()
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
    val chargingVisible by remember { ammoVM.chargingAnimation }.collectAsState()
    val chargingAnimation by remember { ammoVM.chargingAnimation }.collectAsState()
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
//                                color = if (chargingAnimation) Color.Black else pairOfIsHitAndColor.second,
                                color = if (chargingAnimation) Color.Black else if (pairOfIsHitAndColor.first) pairOfIsHitAndColor.second else ui.shipType.info.color,
                                alpha = if (pairOfIsHitAndColor.first) alphaColor + 0.15F else if(chargingVisible) alphaColor + 0.15F else  alphaColor
                            )
                        }
                    } ?: drawRect(
                        topLeft = star.anchor,
                        size = ui.matrix.dotCanvasSize,
                        color = if (chargingAnimation) Color.Black else if (pairOfIsHitAndColor.first) pairOfIsHitAndColor.second else ui.shipType.info.color,
//                        color = if (chargingAnimation) Color.Black else pairOfIsHitAndColor.second,
                        alpha = if (pairOfIsHitAndColor.first) alphaColor + 0.15F else alphaColor
                    )
                }
            }
        }
    }

    AnimatedVisibility(
        visible = chargingAnimation,
        enter = fadeIn(),
        exit = fadeOut(),
    ) {
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
                                    color = MyColor.applicationContrast,
                                    alpha = 0.9F,
                                )
                            }
                        } ?: drawRect(
                            topLeft = star.anchor,
                            size = ui.matrix.dotCanvasSize,
                            color = MyColor.applicationContrast,
                            alpha = 0.9F,
                        )
                    }
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
