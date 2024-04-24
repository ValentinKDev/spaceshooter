package com.mobilegame.spaceshooter.presentation.ui.screens.menuScreen.letters

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.*
import androidx.compose.ui.graphics.drawscope.Fill
import androidx.compose.ui.graphics.drawscope.Stroke
import com.mobilegame.spaceshooter.logic.uiHandler.screens.menu.startMenu.MenuScreenUI
import com.mobilegame.spaceshooter.logic.uiHandler.screens.menu.startMenu.letters.LetterSUI
import com.mobilegame.spaceshooter.presentation.theme.MyColor

@Composable
fun DrawS(menuUI: MenuScreenUI) {
    val ui: LetterSUI = remember { LetterSUI(menuUI.letterSizeDp) }

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
            path = ui.bottomSquare,
            color = MyColor.Platinium,
            style = Fill,
            blendMode = BlendMode.Xor
        )
        drawPath(
            path = ui.topSquare1,
            color = MyColor.Platinium,
            style = Fill,
            blendMode = BlendMode.Xor
        )
        drawPath(
            path = ui.topSquare2,
            color = MyColor.Platinium,
            style = Fill,
            blendMode = BlendMode.Xor
        )
        drawPath(
            path = ui.uPath,
            color = MyColor.Platinium,
            style = Fill,
            blendMode = BlendMode.Xor
        )
        drawPath(
            path = ui.uPath,
            color = MyColor.Platinium,
            style = Stroke(width = ui.strokeWidth, cap = StrokeCap.Round, join = StrokeJoin.Round),
        )

        drawPath(
            path = ui.bottomSquare,
            color = MyColor.Platinium,
            style = Stroke(width = ui.strokeWidth, cap = StrokeCap.Round, join = StrokeJoin.Round),
        )
        drawPath(
            path = ui.topSquare1,
            color = MyColor.Platinium,
            style = Stroke(width = ui.strokeWidth, cap = StrokeCap.Round, join = StrokeJoin.Round),
        )
        drawPath(
            path = ui.topSquare2,
            color = MyColor.Platinium,
            style = Stroke(width = ui.strokeWidth, cap = StrokeCap.Round, join = StrokeJoin.Round),
        )
    }
}

//todo: use this for splash animation
//ChargingButton(
//handler = PressureNavigationViewModel(Screens.MenuScreen),
//sizeDp = 100.dp,
//navigator = navigator
//) {
//    Canvas(
//        Modifier
//            .size(ui.canvasSizeDp)
//            .graphicsLayer(alpha = 0.99f)
////                .background(Color.Black)
//    ) {
//        drawRect(
//            color = MyColor.applicationBackground,
//            size = size,
//            blendMode = BlendMode.Xor
//        )
//        drawPath(
//            path = ui.uPath,
//            color = MyColor.Platinium,
//            style = Stroke(width = 5F, cap = StrokeCap.Round, join = StrokeJoin.Round),
//            blendMode = BlendMode.Xor
//        )
//    }
//}
