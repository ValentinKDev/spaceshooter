package com.mobilegame.spaceshooter.presentation.ui.screens.inGameScreen.elements

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import com.mobilegame.spaceshooter.logic.model.screen.inGameScreens.ship.MunitionsViewModel

@Composable
fun AmmunitionView(vm: MunitionsViewModel) {
    val interactionSource = remember { MutableInteractionSource() }
    val isPressed by interactionSource.collectIsPressedAsState()

    LaunchedEffect(isPressed) {
//        vm.updatePressureStateTo(isPressed)
        when (isPressed) {
            true -> { vm.chargingShoot() }
//            true -> { vm.chargingProjectile() }
            false -> { vm.shoot() }
        }
    }

    val clickable = Modifier.clickable (
        interactionSource = interactionSource,
        indication = null,
//        onClick = {vm.hasBeenPressedOnce = true},
        onClick = {},
    )

    Box(
        Modifier
            .fillMaxSize()
            .then(clickable)
    )
}