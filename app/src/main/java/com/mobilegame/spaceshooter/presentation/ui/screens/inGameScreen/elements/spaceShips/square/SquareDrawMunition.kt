package com.mobilegame.spaceshooter.presentation.ui.screens.inGameScreen.elements.spaceShips.square

import androidx.compose.foundation.Canvas
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.drawscope.Fill
import com.mobilegame.spaceshooter.logic.uiHandler.SpaceShip.SquareSpaceShipIconUI
import com.mobilegame.spaceshooter.presentation.ui.screens.inGameScreen.elements.spaceShips.circle.MunitionsType

@Composable
fun SquareDrawMunition(topLeftOffset: Offset, ui: SquareSpaceShipIconUI, type: MunitionsType, particularBehavior: Int = 1) {
    Canvas(Modifier){
        drawRect(
            topLeft = topLeftOffset,
            size = ui.ammos.ammoSize,
            style = Fill,
            color = ui.colors.outline,
        )
        if (type == MunitionsType.Shoot) {
                drawRect(
                    topLeft = topLeftOffset,
                    size = ui.ammos.getShootSize(particularBehavior),
                    style = Fill,
                    color = ui.colors.outline,
                )
        }
    }
}
