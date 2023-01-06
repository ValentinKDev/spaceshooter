package com.mobilegame.spaceshooter.logic.uiHandler.mainTemplate

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import com.mobilegame.spaceshooter.logic.uiHandler.Device
import com.mobilegame.spaceshooter.presentation.theme.MyColor
import com.mobilegame.spaceshooter.utils.analyze.displayDataUI
import com.mobilegame.spaceshooter.utils.analyze.wLog
import com.mobilegame.spaceshooter.utils.extensions.toDp

class BackButtonUI() {
    val padding = PaddingBackButton()
    val colors = ColorsBackButton()
    val points = PointsBackButton()
    val sizes = SizesBackButton()

    init { initPoints() }

    data class PaddingBackButton (
        var start: Float = 0.01F,
    )
    data class SizesBackButton (
        val boxHeight: Float = Device.height * (MainTemplateUI.headerHeightWeight / MainTemplateUI.allWeights),
        val boxHeightDp: Dp = boxHeight.toDp(),
        val canvas: Float = boxHeight * 0.75F,
        val canvasDp: Dp = canvas.toDp(),
    )
    data class ColorsBackButton (
        val contrast: Color = MyColor.applicationContrast,
        val background: Color = MyColor.applicationBackground,
    )
    data class PointsBackButton (
        var p1: Offset = Offset.Zero,
        var p1Long: Offset = Offset.Zero,
        var p2: Offset = Offset.Zero,
        var p3: Offset = Offset.Zero,
        var p4: Offset = Offset.Zero,
        var p5: Offset = Offset.Zero,
        var p6: Offset = Offset.Zero,
        var p7: Offset = Offset.Zero,
        var p8: Offset = Offset.Zero,
        var p9: Offset = Offset.Zero,
        var p9Long: Offset = Offset.Zero,
    )
    fun initPoints() {
        val delta = 0.05F * sizes.canvas
        val epsilon = 0.025F * sizes.canvas
        val halfSize = 0.5F * sizes.canvas

        val xArrowPoint = 3 * delta
        val oneFifth = sizes.canvas * (1F/5F)
        val twoFifth = sizes.canvas * (2F/5F) - delta
        val threeFifth = sizes.canvas * (3F/5F) + delta
        val fourthFifth = sizes.canvas * (4F/5F)

        val half1 = halfSize - (0.08F * sizes.canvas)
        val half2 = halfSize + (0.08F * sizes.canvas)

        points.p1 = Offset(x = sizes.canvas - epsilon, y = twoFifth)
        points.p1Long = Offset(x = sizes.canvas, y = twoFifth)
        points.p2 = Offset(x = half2, y = twoFifth)
        points.p3 = Offset(x = half2, y = oneFifth)
        points.p4 = Offset(x = half1, y = oneFifth)
        points.p5 = Offset(x = xArrowPoint, y = halfSize)
        points.p6 = Offset(x = half1, y = fourthFifth)
        points.p7 = Offset(x = half2, y = fourthFifth)
        points.p8 = Offset(x = half2, y = threeFifth)
        points.p9 = Offset(x = sizes.canvas - epsilon , y = threeFifth)
        points.p9Long = Offset(x = sizes.canvas, y = threeFifth)

        displayDataUI?.let {
            wLog("BackButton::initPoints", "points")
        }
    }

}
