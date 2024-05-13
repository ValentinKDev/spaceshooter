package com.mobilegame.spaceshooter.presentation.ui.screens.inGameScreen.elements.spaceShips.types.circle

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
import com.mobilegame.spaceshooter.logic.uiHandler.SpaceShip.types.CircleSpaceShipIconUI

@Composable
fun CircleDrawMunition(center: Offset, ui: CircleSpaceShipIconUI, type: MunitionsType) {
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
                drawCircle(
                    center = center,
                    radius = ui.ammunition.radius,
                    style = Fill,
                    color = ui.colors.ammo
                )
            }
            MunitionsType.UserProjectile -> {
                drawCircle(
                    center = center,
                    radius = ui.ammunition.innerRadius * sizeRatio,
                    style = Fill,
                    color = ui.colors.shoot,
                    alpha = alphaColor
                )
            }
            MunitionsType.EnemiesProjectile -> {
                drawCircle(
                    center = center,
                    radius = ui.ammunition.innerRadius * sizeRatio,
                    style = Fill,
                    color = Color.Red,
                    alpha = alphaColor
                )
            }
        }
    }
}

enum class MunitionsType {
    InMagazine, UserProjectile, EnemiesProjectile
}
