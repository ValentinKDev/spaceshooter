package com.mobilegame.spaceshooter.presentation.ui.screens.menu.letters

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.*
import androidx.compose.ui.graphics.drawscope.Fill
import androidx.compose.ui.graphics.drawscope.Stroke
import com.mobilegame.spaceshooter.logic.uiHandler.screens.menu.LetterPUI
import com.mobilegame.spaceshooter.presentation.theme.MyColor

@Composable
fun DrawP(ui: LetterPUI) {
    Canvas(
        Modifier
            .width(ui.canvasSizeDp.width)
            .height(ui.canvasSizeDp.width)
            .graphicsLayer(alpha = 0.99f)
    ) {
        drawRect(
            color = MyColor.applicationBackground,
            size = size,
            blendMode = BlendMode.Xor
        )
        drawPath(
            path = ui.pathPExt,
            color = MyColor.Platinium,
            style = Fill,
            blendMode = BlendMode.Xor
        )
        drawPath(
            path = ui.squarePath,
            color = MyColor.Platinium,
            style = Fill,
            blendMode = BlendMode.Xor
        )

        drawPath(
            path = ui.pathPExt,
            color = MyColor.Platinium,
            style = Stroke(width = ui.strokeWidth, cap = StrokeCap.Round, join = StrokeJoin.Round),
        )
        drawPath(
            path = ui.squarePath,
            color = MyColor.Platinium,
            style = Stroke(width = ui.strokeWidth, cap = StrokeCap.Round, join = StrokeJoin.Round),
        )
        drawPath(
            path = ui.pathPIn,
            color = MyColor.applicationBackground,
            style = Fill,
        )
        drawPath(
            path = ui.pathPIn,
            color = MyColor.Platinium,
            style = Stroke(width = ui.strokeWidth, cap = StrokeCap.Round, join = StrokeJoin.Round),
        )
        drawPath(
            path = ui.rectanglePath,
            color = MyColor.applicationBackground,
            style = Fill,
        )
        drawPath(
            path = ui.rectanglePath,
            color = MyColor.Platinium,
            style = Stroke(width = ui.strokeWidth, cap = StrokeCap.Round, join = StrokeJoin.Round),
        )
    }
}