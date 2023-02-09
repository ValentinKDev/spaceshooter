package com.mobilegame.spaceshooter.presentation.ui.screens.utils

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.DpSize
import com.mobilegame.spaceshooter.logic.model.screen.pression.PressureVMInterface
import com.mobilegame.spaceshooter.presentation.ui.navigation.Navigator
import com.mobilegame.spaceshooter.presentation.ui.screens.utils.chargingEffect.ChargingAnimation
import com.mobilegame.spaceshooter.presentation.ui.screens.utils.chargingEffect.chargingButton.FilterRoundShape


@Composable
fun ChargingButton(
    handler: PressureVMInterface,
    sizeDp: DpSize,
    navigator: Navigator,
    alphaAnimation: Float = 1F,
    roundShape: Boolean? = null,
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

    //todo : add round corner to the box
    Box(Modifier.size(sizeDp)) {
        ChargingAnimation(isPressed = isPressed, timer = handler.timerValidation.toInt(), alpha = alphaAnimation)

        roundShape?.let { FilterRoundShape(sizeDp) }
        Box(modifier = Modifier
            .wrapContentSize()
            .then(clickable)) {
            content.invoke()
        }
    }
}