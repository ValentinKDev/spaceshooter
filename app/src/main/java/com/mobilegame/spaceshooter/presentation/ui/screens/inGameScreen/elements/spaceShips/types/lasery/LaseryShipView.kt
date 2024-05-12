package com.mobilegame.spaceshooter.presentation.ui.screens.inGameScreen.elements.spaceShips.types.lasery

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.geometry.Size
import com.mobilegame.spaceshooter.logic.uiHandler.SpaceShip.types.LaserySpaceShipIconUI

@Composable
fun LaseryShipView(
    lifeRatio: Float,
    magazine: Int,
    shipViewSizeBox: Size,
    visibleCharging: Boolean
) {
    val ui = remember { LaserySpaceShipIconUI(shipViewSizeBox) }

    Box(
        androidx.compose.ui.Modifier.size(ui.sizes.shipSizeDp)
    ) {
        LaseryShipShape(lifeRatio, ui)
        LaseryShipMagazine(magazine, ui)
    }

    AnimatedVisibility(
        visible = visibleCharging,
        enter = fadeIn(),
        exit = fadeOut(),
    ) {
        ChargingLaseryShipShape(ui)
    }
}