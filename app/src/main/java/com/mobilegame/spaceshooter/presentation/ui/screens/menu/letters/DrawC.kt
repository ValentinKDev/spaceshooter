package com.mobilegame.spaceshooter.presentation.ui.screens.menu.letters

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.DrawCacheModifier
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.StrokeJoin
import androidx.compose.ui.graphics.drawscope.Fill
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.unit.DpSize
import com.mobilegame.spaceshooter.logic.uiHandler.Device
import com.mobilegame.spaceshooter.logic.uiHandler.screens.menu.LetterCUI
import com.mobilegame.spaceshooter.presentation.theme.MyColor
import com.mobilegame.spaceshooter.utils.extensions.add
import com.mobilegame.spaceshooter.utils.extensions.time

@Composable
fun DrawC(ui: LetterCUI) {
    val topEllipse = remember { ui.getTopEllipsePath() }
    val bottomEllipse = remember { ui.getBottomEllipsePath() }
    Canvas(
        Modifier
//            .size(ui.canvasSizeDp)
//            .background(MyColor.Liver)
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
            path = bottomEllipse,
            color = MyColor.Platinium,
            style = Fill,
            blendMode = BlendMode.Xor
        )
        drawPath(
            path = topEllipse,
            color = MyColor.Platinium,
            style = Fill,
            blendMode = BlendMode.Xor
        )

        drawPath(
            path = bottomEllipse,
            color = MyColor.Platinium,
            style = Stroke(width = ui.strokeWidth, cap = StrokeCap.Round, join = StrokeJoin.Round),
        )
        drawPath(
            path = topEllipse,
            color = MyColor.Platinium,
            style = Stroke(width = ui.strokeWidth, cap = StrokeCap.Round, join = StrokeJoin.Round),
        )
    }
}