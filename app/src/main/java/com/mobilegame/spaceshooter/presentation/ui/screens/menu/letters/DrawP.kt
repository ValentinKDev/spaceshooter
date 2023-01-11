package com.mobilegame.spaceshooter.presentation.ui.screens.menu.letters

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.*
import androidx.compose.ui.graphics.drawscope.Fill
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.unit.DpSize
import com.mobilegame.spaceshooter.logic.uiHandler.Device
import com.mobilegame.spaceshooter.logic.uiHandler.screens.menu.LetterPUI
import com.mobilegame.spaceshooter.presentation.theme.MyColor
import com.mobilegame.spaceshooter.utils.extensions.add
import com.mobilegame.spaceshooter.utils.extensions.time

@Composable
fun DrawP(ui: LetterPUI) {
    val pathPExt = remember { ui.getPathPExt() }
    val rectPath = remember { ui.getRectanglePath() }
    val pathPIn = remember { ui.getPathPIn() }
    val squarePath = remember { ui.getsquarePath() }

    Canvas(
        Modifier
//            .size(ui.canvasSizeDp)
//            .background(Color.Black)
            .size(
                DpSize(
                    width = ui.canvasSizeDp add (ui.padding time 2F),
                    height = Device.sizeDp.height
                )
            )
            .graphicsLayer(alpha = 0.99f)
    ) {
        drawRect(
            color = MyColor.applicationBackground,
            size = size,
            blendMode = BlendMode.Xor
        )
        drawPath(
            path = pathPExt,
            color = MyColor.Platinium,
            style = Fill,
            blendMode = BlendMode.Xor
        )
        drawPath(
            path = squarePath,
            color = MyColor.Platinium,
            style = Fill,
            blendMode = BlendMode.Xor
        )

        drawPath(
            path = pathPExt,
            color = MyColor.Platinium,
            style = Stroke(width = ui.strokeWidth, cap = StrokeCap.Round, join = StrokeJoin.Round),
        )
        drawPath(
            path = squarePath,
            color = MyColor.Platinium,
            style = Stroke(width = ui.strokeWidth, cap = StrokeCap.Round, join = StrokeJoin.Round),
        )
        drawPath(
            path = pathPIn,
            color = MyColor.applicationBackground,
            style = Fill,
        )
        drawPath(
            path = pathPIn,
            color = MyColor.Platinium,
            style = Stroke(width = ui.strokeWidth, cap = StrokeCap.Round, join = StrokeJoin.Round),
        )
        drawPath(
            path = rectPath,
            color = MyColor.applicationBackground,
            style = Fill,
        )
        drawPath(
            path = rectPath,
            color = MyColor.Platinium,
            style = Stroke(width = ui.strokeWidth, cap = StrokeCap.Round, join = StrokeJoin.Round),
        )
    }
}