package com.mobilegame.spaceshooter.presentation.ui.screens.menu.letters

import androidx.compose.animation.animateColor
import androidx.compose.animation.core.*
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.*
import androidx.compose.ui.graphics.drawscope.Fill
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import com.mobilegame.spaceshooter.logic.model.screen.Screens
import com.mobilegame.spaceshooter.logic.model.screen.mainScreen.PressureNavigationViewModel
import com.mobilegame.spaceshooter.logic.uiHandler.Device
import com.mobilegame.spaceshooter.logic.uiHandler.screens.menu.LetterSUI
import com.mobilegame.spaceshooter.presentation.theme.MyColor
import com.mobilegame.spaceshooter.presentation.ui.navigation.Navigator
import com.mobilegame.spaceshooter.presentation.ui.screens.utils.ChargingButton
import com.mobilegame.spaceshooter.presentation.ui.screens.utils.ClickableBoundaries
import com.mobilegame.spaceshooter.utils.analyze.wLog
import com.mobilegame.spaceshooter.utils.extensions.*

@Composable
fun DrawS(ui: LetterSUI) {
    val uPath = remember { ui.getUPath() }
    val topSquare1Path = remember {ui.getTopSquare1()}
    val topSquare2Path = remember {ui.getTopSquare2()}
    val bottomSquarePath = remember {ui.getBottomSquare()}
    val centerOffset = remember { Offset(x = 0F, y = (Device.height / 2F) - (ui.canvasSizeDp.DpToPixel() / 2F)) }

    Canvas(
        Modifier
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
            path = bottomSquarePath,
            color = MyColor.Platinium,
            style = Fill,
            blendMode = BlendMode.Xor
        )
        drawPath(
            path = topSquare1Path,
            color = MyColor.Platinium,
            style = Fill,
            blendMode = BlendMode.Xor
        )
        drawPath(
            path = topSquare2Path,
            color = MyColor.Platinium,
            style = Fill,
            blendMode = BlendMode.Xor
        )
        drawPath(
            path = uPath,
            color = MyColor.Platinium,
            style = Fill,
            blendMode = BlendMode.Xor
        )
        drawPath(
            path = uPath,
            color = MyColor.Platinium,
            style = Stroke(width = ui.strokeWidth, cap = StrokeCap.Round, join = StrokeJoin.Round),
        )

        drawPath(
            path = bottomSquarePath,
            color = MyColor.Platinium,
            style = Stroke(width = ui.strokeWidth, cap = StrokeCap.Round, join = StrokeJoin.Round),
        )
        drawPath(
            path = topSquare1Path,
            color = MyColor.Platinium,
            style = Stroke(width = ui.strokeWidth, cap = StrokeCap.Round, join = StrokeJoin.Round),
        )
        drawPath(
            path = topSquare2Path,
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
//            path = uPath,
//            color = MyColor.Platinium,
//            style = Stroke(width = 5F, cap = StrokeCap.Round, join = StrokeJoin.Round),
//            blendMode = BlendMode.Xor
//        )
//    }
//}
