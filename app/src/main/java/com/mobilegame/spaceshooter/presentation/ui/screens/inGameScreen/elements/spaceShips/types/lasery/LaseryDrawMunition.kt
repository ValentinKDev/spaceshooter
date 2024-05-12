package com.mobilegame.spaceshooter.presentation.ui.screens.inGameScreen.elements.spaceShips.types.lasery

import android.util.Log
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import com.mobilegame.spaceshooter.logic.uiHandler.SpaceShip.types.LaserySpaceShipIconUI

@Composable
fun LaseryDrawMagazineAmmo(ui: LaserySpaceShipIconUI, n: Int) {
    Canvas( Modifier.size(ui.sizes.shipSizeDp) ) {
        drawPath(
            path = ui.magazine.getPathAmmo(n),
            color = ui.colors.border,
            style = Stroke(width = ui.magazine.stroke, cap = StrokeCap.Round),
        )
    }
}

@Composable
fun LaseryDrawProjectile(
    laser: Pair<Offset, Offset>,
    ui: LaserySpaceShipIconUI,
    particularBehavior: Int,
    color: Color
) {
    LaunchedEffect(key1 = "") {
        Log.i("LaseryDrawProjectile", "LaseryDrawProjectile: laser $laser")
    }
    val infiniteTransition = rememberInfiniteTransition()
    val ratioSize by infiniteTransition.animateFloat(
        initialValue = 0.8F,
        targetValue = 1.3F,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 170, easing = LinearEasing),
            repeatMode = RepeatMode.Reverse
        )
    )
    val ratioSize2 by infiniteTransition.animateFloat(
        initialValue = 0.8F,
        targetValue = 1.3F,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 50, easing = LinearEasing),
            repeatMode = RepeatMode.Reverse
        )
    )
    val ratioSize3 by infiniteTransition.animateFloat(
        initialValue = 0.8F,
        targetValue = 1.3F,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 50, easing = LinearEasing),
            repeatMode = RepeatMode.Reverse
        )
    )
    val alphaColor by infiniteTransition.animateFloat(
        initialValue = 0.2F,
        targetValue = 0.6F,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 100, easing = LinearEasing),
            repeatMode = RepeatMode.Reverse
        )
    )

    Canvas(Modifier) {
        drawLine(
            start = laser.first,
            end = laser.second,
//            color = ui.colors.ship,
            color = color,
            strokeWidth = ui.laser.sideLight * ratioSize * particularBehavior,
            cap = StrokeCap.Round
        )
        drawLine(
            start = laser.first,
            end = laser.second,
            color = Color.White,
            alpha = alphaColor,
            strokeWidth = ui.laser.middleLightSurrounding * particularBehavior,
            cap = StrokeCap.Round
        )
        drawLine(
            start = laser.first,
            end = laser.second,
            color = Color.White,
            strokeWidth = ui.laser.middleLight * ratioSize3 * particularBehavior,
            cap = StrokeCap.Round
        )
    }
}