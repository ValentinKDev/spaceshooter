package com.mobilegame.spaceshooter.presentation.ui.screens.utils

import android.app.Activity
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.DpSize
import com.google.accompanist.systemuicontroller.SystemUiController
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.mobilegame.spaceshooter.logic.model.screen.mainScreen.PressureNavigationViewModel
import com.mobilegame.spaceshooter.presentation.theme.MyColor
import com.mobilegame.spaceshooter.presentation.ui.navigation.Navigator
import com.mobilegame.spaceshooter.presentation.ui.screens.utils.chargingButton.FilterRoundShape


@Composable
fun ChargingButton(
    handler: PressureNavigationViewModel,
//    sizeDp: Dp,
    sizeDp: DpSize,
    navigator: Navigator,
    roundShape: Boolean? = null,
    clickableBoundaries: ClickableBoundaries = ClickableBoundaries.InContent,
    content: @Composable () -> Unit
) {
    val minWeight = remember { 0.001F }
    val maxWeight = remember { 0.999F }
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
        targetValue = if (isPressed) maxWeight else minWeight,
        animationSpec = tween(handler.timerValidationAnim)
    )

    val chargingBoxModifier = when (clickableBoundaries) {
        ClickableBoundaries.InContent -> Modifier.size(sizeDp)
        ClickableBoundaries.OutContent -> Modifier.then(clickable).size(sizeDp)
    }
    //todo : add round corner to the box
    Box( chargingBoxModifier
    ) {
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

        Column(Modifier.fillMaxSize()) {
            Box(
                Modifier
                    .fillMaxWidth()
                    .weight(maxWeight)
            )
            Box(
                Modifier
                    .fillMaxWidth()
                    .weight(minWeight)
                    .background(MyColor.applicationBackground)
            )
        }

        val contentBoxModifier = when (clickableBoundaries) {
            ClickableBoundaries.InContent -> Modifier.wrapContentSize().then(clickable)
            ClickableBoundaries.OutContent -> Modifier.wrapContentSize()
        }
        roundShape?.let { FilterRoundShape(sizeDp) }
//        Box( Modifier.then(clickable)) {
        Box(modifier = contentBoxModifier) {
            content.invoke()
        }
    }
}

enum class ClickableBoundaries {
    InContent, OutContent
}