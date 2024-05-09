package com.mobilegame.spaceshooter.presentation.ui.screens.inGameScreen.elements.spaceShips.types.circle

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import com.mobilegame.spaceshooter.logic.uiHandler.SpaceShip.types.CircleSpaceShipIconUI

@Composable
fun CircleShipView(lifeRatio: Float, magazine: Int, shipBox: Size, visibleCharging: Boolean) {
    val ui = remember { CircleSpaceShipIconUI(shipBox) }

    Box(
        Modifier
            .size(ui.sizes.shipBoxDp)
    ) {
        CircleShipShape(lifeRatio, ui)
        CircleShipAmmunition(magazine, ui)
    }
    AnimatedVisibility(
        visible = visibleCharging,
        enter = fadeIn(),
        exit = fadeOut(),
    ) {
        ChargingCircleShipShape(ui)
    }
}