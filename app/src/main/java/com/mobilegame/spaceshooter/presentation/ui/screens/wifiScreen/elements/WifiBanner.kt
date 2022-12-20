package com.mobilegame.spaceshooter.presentation.ui.screens.wifiScreen.elements

import androidx.compose.animation.animateColor
import androidx.compose.animation.core.*
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Fill
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.unit.dp
import com.mobilegame.spaceshooter.domain.model.screen.uiAdapter.WifiIconAdapter
import com.mobilegame.spaceshooter.presentation.theme.MyColor
import com.mobilegame.spaceshooter.presentation.ui.screens.mainScreen.elements.buttons.WifiButton.WifiIcon.WifiIcon
import com.mobilegame.spaceshooter.presentation.ui.screens.utils.CenterComposable
import com.mobilegame.spaceshooter.utils.extensions.alpha


@Composable
fun WifiBanner(ui: WifiIconAdapter) {
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