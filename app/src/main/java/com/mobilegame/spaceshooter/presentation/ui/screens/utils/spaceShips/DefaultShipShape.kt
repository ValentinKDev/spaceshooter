package com.mobilegame.spaceshooter.presentation.ui.screens.utils.spaceShips

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Fill
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.mobilegame.spaceshooter.domain.model.screen.uiAdapter.SpaceShip.SpaceShipIconAdapter
import com.mobilegame.spaceshooter.presentation.theme.MyColor
import com.mobilegame.spaceshooter.utils.analyze.vLog

@Composable
fun DefaultShipShape(ui: SpaceShipIconAdapter) {
    Box(
        Modifier
            .size(ui.sizes.shipBoxDp)
    ) {
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
}
