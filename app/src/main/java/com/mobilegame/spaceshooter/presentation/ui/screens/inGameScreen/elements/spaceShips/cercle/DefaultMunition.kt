package com.mobilegame.spaceshooter.presentation.ui.screens.inGameScreen.elements.spaceShips.cercle

import androidx.compose.foundation.Canvas
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.drawscope.Fill
import com.mobilegame.spaceshooter.logic.uiHandler.SpaceShip.SpaceShipIconUI

@Composable
fun DrawMunition(center: Offset, ui: SpaceShipIconUI, type: MunitionsType) {
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
    Ammunition, Shoot
}
