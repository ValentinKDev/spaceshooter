package com.mobilegame.spaceshooter.presentation.ui.screens.inGameScreen.elements.spaceShips.types.square

import android.util.Log
import androidx.compose.foundation.Canvas
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Fill
import com.mobilegame.spaceshooter.logic.uiHandler.SpaceShip.types.SquareSpaceShipIconUI
import com.mobilegame.spaceshooter.presentation.ui.screens.inGameScreen.elements.spaceShips.types.circle.MunitionsType

@Composable
fun SquareDrawMunition(topLeftOffset: Offset, ui: SquareSpaceShipIconUI, type: MunitionsType, particularBehavior: Int = 1) {
    Canvas(Modifier){
        drawRect(
            topLeft = topLeftOffset,
            size = ui.ammo.ammoSize,
            style = Fill,
            color = ui.colors.outline,
        )
        if (type == MunitionsType.UserProjectile) {
//            Log.v("SquareDrawMunition", "SquareDrawMunition: User")
                drawRect(
                    topLeft = topLeftOffset,
                    size = ui.ammo.getShootSize(particularBehavior),
                    style = Fill,
                    color = ui.colors.outline,
                )
        } else if (type == MunitionsType.EnemiesProjectile) {
//            Log.v("SquareDrawMunition", "SquareDrawMunition: Enemie")
            drawRect(
                topLeft = topLeftOffset,
                size = ui.ammo.getShootSize(particularBehavior),
                style = Fill,
                //Todo change the enemie projectile color
                color = Color.Red
            )
        }
    }
}
