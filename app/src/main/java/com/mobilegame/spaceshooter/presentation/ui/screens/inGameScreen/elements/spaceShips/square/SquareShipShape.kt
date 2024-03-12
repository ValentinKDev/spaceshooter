package com.mobilegame.spaceshooter.presentation.ui.screens.inGameScreen.elements.spaceShips.square

import android.util.Log
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.drawscope.Fill
import androidx.compose.ui.graphics.drawscope.Stroke
import com.mobilegame.spaceshooter.logic.model.screen.inGameScreens.ship.LifeViewModel
import com.mobilegame.spaceshooter.logic.uiHandler.SpaceShip.types.SquareSpaceShipIconUI

@Composable
fun SquareShipShape(lifeVM: LifeViewModel, ui: SquareSpaceShipIconUI) {
    val lifeRatio by  remember {lifeVM.lifeRatio }.collectAsState()
//    Log.e("", "Ratio $lifeRatio")
//    val shoots by remember { vm.shipVM.motionVM.shootList }.collectAsState()

    Canvas(
        Modifier
            .size(ui.sizes.shipSizeDp)
    ) {
        drawRect(
            size = size,
            color = ui.colors.body,
            style = Fill,
            alpha = lifeRatio,
        )
        drawRect(
            size = size,
            color = ui.colors.outline,
            style = Stroke(width = ui.sizes.stroke)
        )
        drawLine(
            start = ui.points.startCentralBar,
            end = ui.points.bottomCentralBar,
            color = ui.colors.outline,
            strokeWidth = ui.sizes.stroke
        )
    }
}