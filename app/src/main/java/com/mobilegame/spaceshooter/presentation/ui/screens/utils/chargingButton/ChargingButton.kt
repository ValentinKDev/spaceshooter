package com.mobilegame.spaceshooter.presentation.ui.screens.utils

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import com.mobilegame.spaceshooter.domain.model.screen.mainScreen.PressureNavigationViewModel
import com.mobilegame.spaceshooter.presentation.theme.MyColor
import com.mobilegame.spaceshooter.presentation.ui.navigation.Navigator
import com.mobilegame.spaceshooter.presentation.ui.screens.utils.chargingButton.FilterRoundShape


@Composable
fun ChargingButton(
    handler: PressureNavigationViewModel,
    sizeDp: Dp,
    navigator: Navigator,
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

    val heightWeight by animateFloatAsState(
        targetValue = if (isPressed) 0.99F else 0.01F,
        animationSpec = tween(handler.timerValidationAnim)
    )

    //todo : add round corner to the box
    Box( Modifier.size(sizeDp)) {
        Column(Modifier.fillMaxSize()) {
            Box(
                Modifier
                    .fillMaxWidth()
                    .weight(1F - heightWeight)
            )
            Box(
                Modifier
                    .fillMaxWidth()
                    .weight(heightWeight)
                    .background(MyColor.applicationContrast)
            )
        }

        roundShape?.let { FilterRoundShape(sizeDp) }

        Box( Modifier.then(clickable)) {
            content.invoke()
        }
    }
}