package com.mobilegame.spaceshooter.presentation.ui.screens.inGameScreen.elements.spaceShips.types.square

import android.util.Log
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Fill
import com.mobilegame.spaceshooter.logic.uiHandler.SpaceShip.types.SquareSpaceShipIconUI
import com.mobilegame.spaceshooter.presentation.ui.screens.inGameScreen.elements.spaceShips.types.circle.MunitionsType

@Composable
fun SquareDrawMunition(topLeftOffset: Offset, ui: SquareSpaceShipIconUI, type: MunitionsType, particularBehavior: Int = 1) {
    val infiniteTransition = rememberInfiniteTransition()
    val alphaColor by infiniteTransition.animateFloat(
        initialValue = 0.7F,
        targetValue = 1F,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 100, easing = LinearEasing),
            repeatMode = RepeatMode.Reverse
        )
    )
    val sizeRatio by infiniteTransition.animateFloat(
        initialValue = 1F,
        targetValue = 1.25F,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 150, easing = LinearEasing),
            repeatMode = RepeatMode.Reverse
        )
    )
    Canvas(Modifier){
        when (type) {
            MunitionsType.InMagazine -> {
                drawRect(
                    topLeft = topLeftOffset,
                    size = ui.ammo.ammoSize,
                    style = Fill,
                    color = ui.colors.outline,
    //                alpha = alphaColor
                )
            }
            MunitionsType.UserProjectile -> {
                drawRect(
                    topLeft = topLeftOffset,
                    size = ui.ammo.getShootSize(particularBehavior) * sizeRatio,
                    style = Fill,
                    color = ui.colors.outline,
                    alpha = alphaColor
                )
            }
            MunitionsType.EnemiesProjectile -> {
                drawRect(
                    topLeft = topLeftOffset,
                    size = ui.ammo.getShootSize(particularBehavior)* sizeRatio,
                    style = Fill,
                    color = Color.Red,
                    alpha = alphaColor
                )
            }
        }
    }
}
