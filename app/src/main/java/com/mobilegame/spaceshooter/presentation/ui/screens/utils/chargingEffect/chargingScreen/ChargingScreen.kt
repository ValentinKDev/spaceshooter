package com.mobilegame.spaceshooter.presentation.ui.screens.utils.chargingEffect.chargingScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import com.mobilegame.spaceshooter.logic.model.screen.mainScreen.PressureNavigationViewModel
import com.mobilegame.spaceshooter.presentation.theme.MyColor
import com.mobilegame.spaceshooter.presentation.ui.navigation.Navigator
import com.mobilegame.spaceshooter.presentation.ui.screens.utils.chargingEffect.ChargingAnimation

@Composable
fun ChargingScreen(
    handler: PressureNavigationViewModel,
    navigator: Navigator,
    contentSize: DpSize,
    screenSize: DpSize,
    startPadding: Dp = 0.dp,
    endPadding: Dp = 0.dp,
    topPadding: Dp = 0.dp,
    bottomPadding: Dp = 0.dp,
    content: @Composable () -> Unit
) {
    val interactionSource = remember { MutableInteractionSource() }
    val isPressed by interactionSource.collectIsPressedAsState()

    LaunchedEffect(isPressed) {
        when (isPressed) {
            true -> handler.handlePressureStart(navigator)
            false -> handler.handlePressureRelease()
        }
    }

    val clickable = Modifier.clickable (
        interactionSource = interactionSource,
        indication = null,
        onClick = {}
    )

    Column(
        Modifier
            .size(screenSize)
            .then(clickable)
            .background(MyColor.applicationBackground)
    ) {
        Spacer(
            Modifier
                .background(MyColor.applicationBackground)
                .width(screenSize.width)
                .height(topPadding))
        Row {
            Spacer(
                Modifier
                    .background(MyColor.applicationBackground)
                    .height(contentSize.height)
                    .width(startPadding))
            Box(
                Modifier
                    .size(contentSize)
//                    .wrapContentSize()
//                    .background(Color.Red)
            ) {
                ChargingAnimation(isPressed = isPressed, timer = handler.timerValidation.toInt())
                content.invoke()
            }
            Spacer(
                Modifier
                    .background(MyColor.applicationBackground)
                    .height(contentSize.height)
                    .width(endPadding))
        }
        Spacer(
            Modifier
                .background(MyColor.applicationBackground)
                .width(screenSize.width)
                .height(bottomPadding))
    }
}
