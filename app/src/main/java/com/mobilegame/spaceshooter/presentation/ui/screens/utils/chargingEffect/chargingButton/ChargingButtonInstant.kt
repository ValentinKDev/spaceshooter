package com.mobilegame.spaceshooter.presentation.ui.screens.utils.chargingEffect.chargingButton

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
import com.mobilegame.spaceshooter.logic.model.navigation.PressureHandler

@Composable
fun ChargingButtonInstant(
    sizeDp: DpSize,
    contentUncharged: @Composable () -> Unit,
    contentCharged: @Composable () -> Unit,
    handler: PressureHandler,
) {
    val interactionSource = remember { MutableInteractionSource() }
    val isPressed by interactionSource.collectIsPressedAsState()

    LaunchedEffect(isPressed) {
        when (isPressed) {
            true -> handler.setFullAt(true)
            false -> handler.setFullAt(false)
        }
    }

    val clickable = Modifier.clickable (
        interactionSource = interactionSource,
        indication = null,
        onClick = {}
    )

    //todo : add round corner to the box
    Box(Modifier.size(sizeDp)) {
        Box(modifier = Modifier
            .wrapContentSize()
            .then(clickable)) {
            when (isPressed) {
                true -> contentCharged.invoke()
                false -> contentUncharged.invoke()
            }
        }
    }
}