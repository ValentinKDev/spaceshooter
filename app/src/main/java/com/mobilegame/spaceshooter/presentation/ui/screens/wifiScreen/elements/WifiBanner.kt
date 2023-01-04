package com.mobilegame.spaceshooter.presentation.ui.screens.wifiScreen.elements

import androidx.compose.animation.animateColor
import androidx.compose.animation.core.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import com.mobilegame.spaceshooter.logic.uiHandler.Icons.WifiIcon
import com.mobilegame.spaceshooter.presentation.ui.screens.mainScreen.elements.buttons.WifiButton.WifiIcon.WifiIcon


@Composable
fun WifiBanner(ui: WifiIcon) {
    val infiniteTransition = rememberInfiniteTransition()
    val animateColor by infiniteTransition.animateColor(
        initialValue = ui.colors.transparentStart,
        targetValue = ui.colors.transparentTarget,
        animationSpec = infiniteRepeatable(
            tween(durationMillis = 1500, easing = FastOutLinearInEasing),
            RepeatMode.Reverse
        )
    )
    WifiIcon(ui = ui, animateColor)
}