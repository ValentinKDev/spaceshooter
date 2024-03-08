package com.mobilegame.spaceshooter.presentation.ui.screens.inGameScreen.elements.spaceShips

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Fill
import androidx.compose.ui.graphics.drawscope.Stroke
import com.mobilegame.spaceshooter.logic.uiHandler.SpaceShip.types.CircleSpaceShipIconUI

@Composable
fun CircleShipShape(ui: CircleSpaceShipIconUI) {
    Canvas(
        Modifier
            .fillMaxSize()
    ) {
        drawCircle(
            color = ui.colors.body,
            style = Fill
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
