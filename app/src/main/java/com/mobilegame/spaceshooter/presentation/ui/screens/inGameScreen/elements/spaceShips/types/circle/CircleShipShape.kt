package com.mobilegame.spaceshooter.presentation.ui.screens.inGameScreen.elements.spaceShips.types.circle

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Fill
import androidx.compose.ui.graphics.drawscope.Stroke
import com.mobilegame.spaceshooter.logic.uiHandler.SpaceShip.types.CircleSpaceShipIconUI
import com.mobilegame.spaceshooter.presentation.theme.MyColor

@Composable
fun CircleShipShape(lifeRatio: Float, ui: CircleSpaceShipIconUI) {
    Canvas(
        Modifier
            .fillMaxSize()
    ) {
        drawCircle(
            color = ui.colors.body,
            style = Fill,
            alpha = lifeRatio,
        )
        drawCircle(
            color = ui.colors.strokes,
            style = Stroke(ui.sizes.strokeWidth)
        )
        drawLine(
            color = ui.colors.strokes,
            start = ui.points.pTopCentralBar,
            end = ui.points.pBottomCentralBar,
            strokeWidth = ui.sizes.strokeWidth,
            cap = StrokeCap.Round,
        )
    }
}

@Composable
fun ChargingCircleShipShape(ui: CircleSpaceShipIconUI) {
    Canvas(
        Modifier
            .size(ui.sizes.shipBoxDp)
    ) {
        drawCircle(
            color = MyColor.applicationContrast,
            style = Fill,
        )
    }
}