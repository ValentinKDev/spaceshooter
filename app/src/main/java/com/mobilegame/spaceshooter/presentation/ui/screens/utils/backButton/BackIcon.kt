package com.mobilegame.spaceshooter.presentation.ui.screens.utils.backButton

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.*
import androidx.compose.ui.graphics.drawscope.Fill
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.unit.dp
import com.mobilegame.spaceshooter.logic.model.screen.uiHandler.mainTemplate.BackButtonUI

@Composable
fun BackIcon(ui: BackButtonUI) {
    Canvas(
        Modifier
            .size(ui.sizes.canvasDp)
    ) {
        val innerStroke = 2.5.dp.toPx()

        val arrow = Path().apply {
            moveTo(ui.points.p1.x, ui.points.p1.y)
            lineTo(ui.points.p2.x, ui.points.p2.y)
            lineTo(ui.points.p3.x, ui.points.p3.y)
            lineTo(ui.points.p4.x, ui.points.p4.y)
            lineTo(ui.points.p5.x, ui.points.p5.y)
            lineTo(ui.points.p6.x, ui.points.p6.y)
            lineTo(ui.points.p7.x, ui.points.p7.y)
            lineTo(ui.points.p8.x, ui.points.p8.y)
            lineTo(ui.points.p9.x, ui.points.p9.y)
        }

        val arrowLong = Path().apply {
            moveTo(ui.points.p1Long.x, ui.points.p1Long.y)
            lineTo(ui.points.p2.x, ui.points.p2.y)
            lineTo(ui.points.p3.x, ui.points.p3.y)
            lineTo(ui.points.p4.x, ui.points.p4.y)
            lineTo(ui.points.p5.x, ui.points.p5.y)
            lineTo(ui.points.p6.x, ui.points.p6.y)
            lineTo(ui.points.p7.x, ui.points.p7.y)
            lineTo(ui.points.p8.x, ui.points.p8.y)
            lineTo(ui.points.p9Long.x, ui.points.p9Long.y)
        }

        drawArc(
            sweepAngle = 320f,
            startAngle = 20f,
            useCenter = false,
            color = ui.colors.contrast,
            style = Stroke(3.dp.toPx(), cap = StrokeCap.Round)
        )

        drawPath(
            color = ui.colors.background,
            path = arrowLong,
            style = Fill
        )

        drawPath(
            color = ui.colors.contrast,
            path = arrow,
            style = Stroke( innerStroke , cap = StrokeCap.Round )
        )
    }
}