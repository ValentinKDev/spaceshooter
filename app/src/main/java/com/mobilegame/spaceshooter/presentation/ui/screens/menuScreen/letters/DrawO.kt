package com.mobilegame.spaceshooter.presentation.ui.screens.menuScreen.letters

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.StrokeJoin
import androidx.compose.ui.graphics.drawscope.Fill
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.graphicsLayer
import com.mobilegame.spaceshooter.logic.uiHandler.screens.menu.startMenu.MenuScreenUI
import com.mobilegame.spaceshooter.logic.uiHandler.screens.menu.startMenu.letters.LetterOUI
import com.mobilegame.spaceshooter.presentation.theme.MyColor

@Composable
fun DrawO(menuUI: MenuScreenUI) {
    val ui: LetterOUI = remember { LetterOUI(menuUI.letterSizeDp) }

    Canvas(
        Modifier
            .width(ui.canvasSizeDp.width)
            .height(ui.canvasSizeDp.width)
//            .background(Color.DarkGray)
            .graphicsLayer(alpha = 0.99f)
    ) {
        drawRect(
            color = MyColor.applicationBackground,
            size = size,
            blendMode = BlendMode.Xor
        )
        drawPath(
            path = ui.bottomEllipse,
            color = MyColor.Platinium,
            style = Fill,
            blendMode = BlendMode.Xor
        )
        drawPath(
            path = ui.leftEllipse,
            color = MyColor.Platinium,
            style = Fill,
            blendMode = BlendMode.Xor
        )
        drawPath(
            path = ui.rightEllipse,
            color = MyColor.Platinium,
            style = Fill,
            blendMode = BlendMode.Xor
        )
        drawPath(
            path = ui.bottomEllipse,
            color = MyColor.Platinium,
            style = Stroke(width = ui.letter.strokeWidth, cap = StrokeCap.Round, join = StrokeJoin.Round),
        )
        drawPath(
            path = ui.leftEllipse,
            color = MyColor.Platinium,
            style = Stroke(width = ui.letter.strokeWidth, cap = StrokeCap.Round, join = StrokeJoin.Round),
        )
        drawPath(
            path = ui.rightEllipse,
            color = MyColor.Platinium,
            style = Stroke(width = ui.letter.strokeWidth, cap = StrokeCap.Round, join = StrokeJoin.Round),
        )
    }
}
