package com.mobilegame.spaceshooter.presentation.ui.screens.inGameScreen.elements.spaceShips.circle

import androidx.compose.foundation.Canvas
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Fill
import com.mobilegame.spaceshooter.logic.uiHandler.SpaceShip.types.CircleSpaceShipIconUI

@Composable
fun CircleDrawMunition(center: Offset, ui: CircleSpaceShipIconUI, type: MunitionsType) {
    Canvas(Modifier){
        drawCircle(
            center = center,
            radius = ui.ammunition.radius,
            style = Fill,
            color = ui.colors.ammo
        )
        if (type == MunitionsType.UserProjectile) {
            drawCircle(
                center = center,
                radius = ui.ammunition.innerRadius,
                style = Fill,
                color = ui.colors.shoot
            )
        } else if (type == MunitionsType.EnemiesProjectile) {
            drawCircle(
                center = center,
                radius = ui.ammunition.innerRadius,
                style = Fill,
                //Todo change the enemie projectile color
                color = Color.Red
            )
        }
    }
}

enum class MunitionsType {
    InMagazine, UserProjectile, EnemiesProjectile
}
