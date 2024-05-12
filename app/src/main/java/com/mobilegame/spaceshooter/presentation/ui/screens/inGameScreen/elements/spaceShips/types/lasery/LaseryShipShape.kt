package com.mobilegame.spaceshooter.presentation.ui.screens.inGameScreen.elements.spaceShips.types.lasery

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.drawscope.Fill
import androidx.compose.ui.graphics.drawscope.Stroke
import com.mobilegame.spaceshooter.logic.uiHandler.SpaceShip.types.LaserySpaceShipIconUI

@Composable
fun LaseryShipShape(lifeRatio: Float, ui: LaserySpaceShipIconUI) {
    Canvas( Modifier.size(ui.sizes.shipSizeDp) ) {
        drawPath(
            path = ui.path.shape,
            color = ui.colors.ship,
            style = Fill,
            alpha = lifeRatio
        )
        drawPath(
            path = ui.path.shape,
            color = ui.colors.border,
            style = Stroke(ui.path.stroke),
//            alpha = lifeRatio,
        )
    }
}

@Composable
fun ChargingLaseryShipShape(ui: LaserySpaceShipIconUI) {
    Canvas( Modifier.size(ui.sizes.shipSizeDp) ) {
        drawPath(
            path = ui.path.shape,
            color = ui.colors.border,
            style = Fill,
        )
    }
}