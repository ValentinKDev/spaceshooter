package com.mobilegame.spaceshooter.presentation.ui.screens.inGameScreen.elements.spaceShips.circle

import androidx.compose.foundation.Canvas
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.drawscope.Fill
import com.mobilegame.spaceshooter.logic.uiHandler.SpaceShip.CircleSpaceShipIconUI

@Composable
fun CircleDrawMunition(center: Offset, ui: CircleSpaceShipIconUI, type: MunitionsType) {
    Canvas(Modifier){
        drawCircle(
            center = center,
            radius = ui.ammunition.radius,
            style = Fill,
            color = ui.colors.ammo
        )
        if (type == MunitionsType.Shoot) {
            drawCircle(
                center = center,
                radius = ui.ammunition.innerRadius,
                style = Fill,
                color = ui.colors.shoot
            )
        }
    }
}

enum class MunitionsType {
    InMagazine, Shoot
}
