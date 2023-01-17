package com.mobilegame.spaceshooter.presentation.ui.screens.menu.letters

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.StrokeJoin
import androidx.compose.ui.graphics.drawscope.Fill
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.graphicsLayer
import com.mobilegame.spaceshooter.logic.uiHandler.screens.menu.LetterRUI
import com.mobilegame.spaceshooter.presentation.theme.MyColor

@Composable
fun DrawR(ui: LetterRUI) {
    Canvas(
        Modifier
            .width(ui.canvasSizeDp.width)
            .height(ui.canvasSizeDp.width)
//            .background(MyColor.Liver)
            .graphicsLayer(alpha = 0.99f)
    ) {
        drawRect(
            color = MyColor.applicationBackground,
            size = size,
            blendMode = BlendMode.Xor
        )
        drawPath(
            path = ui.pathVerticalBar,
            color = MyColor.Platinium,
            style = Fill,
            blendMode = BlendMode.Xor
        )
        drawPath(
            path = ui.pathElipse,
            color = MyColor.Platinium,
            style = Fill,
            blendMode = BlendMode.Xor
        )
        drawPath(
            path = ui.pathSquare1,
            color = MyColor.Platinium,
            style = Fill,
            blendMode = BlendMode.Xor
        )

        drawPath(
            path = ui.pathVerticalBar,
            color = MyColor.Platinium,
            style = Stroke(width = ui.strokeWidth, cap = StrokeCap.Round, join = StrokeJoin.Round),
        )
        drawPath(
            path = ui.pathElipse,
            color = MyColor.Platinium,
            style = Stroke(width = ui.strokeWidth, cap = StrokeCap.Round, join = StrokeJoin.Round),
        )
        drawPath(
            path = ui.pathSquare1,
            color = MyColor.Platinium,
            style = Stroke(width = ui.strokeWidth, cap = StrokeCap.Round, join = StrokeJoin.Round),
        )
    }
}