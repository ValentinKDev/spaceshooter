package com.mobilegame.spaceshooter.presentation.ui.screens.inGameScreen.elements.spaceShips.types.square

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import com.mobilegame.spaceshooter.logic.uiHandler.SpaceShip.types.SquareSpaceShipIconUI

@Composable
fun SquareShipView(lifeRatio: Float, magazine: Int, shipViewBox: Size, visibleCharging: Boolean) {
    val ui = remember { SquareSpaceShipIconUI(shipViewBox) }

    Box( Modifier.size(ui.sizes.shipViewBoxSizeDp)
    ) {
        SquareShipShape(lifeRatio, ui)
        SquareShipAmmunition(magazine, ui)
    }

    AnimatedVisibility(
        visible = visibleCharging,
        enter = fadeIn(),
        exit = fadeOut(),
    ) {
        ChargingSquareShipShape(ui = ui)
    }
}