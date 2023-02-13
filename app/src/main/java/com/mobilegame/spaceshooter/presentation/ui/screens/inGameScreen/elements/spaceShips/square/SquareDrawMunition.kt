package com.mobilegame.spaceshooter.presentation.ui.screens.inGameScreen.elements.spaceShips.square

import androidx.compose.foundation.Canvas
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.drawscope.Fill
import com.mobilegame.spaceshooter.logic.uiHandler.SpaceShip.SquareSpaceShipIconUI
import com.mobilegame.spaceshooter.presentation.ui.screens.inGameScreen.elements.spaceShips.circle.MunitionsType

@Composable
fun SquareDrawMunition(topLeft: Offset, ui: SquareSpaceShipIconUI, type: MunitionsType) {
    Canvas(Modifier){
        drawRect(
            topLeft = topLeft,
            size = ui.magazine.ammoSize,
            style = Fill,
            color = ui.colors.outline,
        )
        if (type == MunitionsType.Shoot) {
            drawRect(
                topLeft = topLeft,
                size = ui.magazine.innerSize,
                style = Fill,
//                topLeft = ,
                color = ui.colors.outline,
            )
        }
    }
}
