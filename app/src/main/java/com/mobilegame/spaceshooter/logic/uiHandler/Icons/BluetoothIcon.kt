package com.mobilegame.spaceshooter.logic.uiHandler.Icons

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import com.mobilegame.spaceshooter.presentation.theme.MyColor
import com.mobilegame.spaceshooter.utils.analyze.displayDataUI
import com.mobilegame.spaceshooter.utils.analyze.vLog
import com.mobilegame.spaceshooter.utils.analyze.wLog
import com.mobilegame.spaceshooter.utils.extensions.toDp

class BluetoothIcon(
    squareSize: Float,
) {
    val id = "BlueToothIcon"
    val ratios = RatiosButtonBluetooth()
    val sizes = SizesButtonBluetooth()
    val points = PointsButtonBluetooth()
    val colors = ColorButtonBluetooth()

    data class RatiosButtonBluetooth (
         val squareStrokePercent: Float = 0.03F,
         val canvasHeightPercent: Float = 0.8F,
         val iconSmallStrokeHeightPercent: Float = 0.05F,
         val iconBigStrokeHeightPercent: Float = 0.10F,
    )
    data class SizesButtonBluetooth (
        var squareHeight: Float = 0F,
        var squareHeightDp: Dp = Dp.Unspecified,
        var squareStrokeDp: Dp = Dp.Unspecified,
        var canvas: Float = 0F,
        var canvasDp: Dp = Dp.Unspecified,
        var iconSmallStroke: Float = 0F,
        var iconBigStroke: Float = 0F,
    )
    data class PointsButtonBluetooth (
        var p1: Offset = Offset.Unspecified,
        var p2: Offset = Offset.Unspecified,
        var p3: Offset = Offset.Unspecified,
        var p4: Offset = Offset.Unspecified,
        var p5: Offset = Offset.Unspecified,
        var p6: Offset = Offset.Unspecified,
    )
    data class ColorButtonBluetooth (
        var transparentStart: Color = MyColor.applicationBackgroundBannerInitial,
        var transparentTarget: Color = MyColor.applicationBackgroundBannerTarget,
        var stroke: Color = MyColor.applicationContrast,
        var background: Color = MyColor.applicationBackground,
    )

    init {
        sizes.squareHeight = squareSize
        sizes.squareHeightDp = (sizes.squareHeight).toDp()
        sizes.squareStrokeDp = (squareSize * ratios.squareStrokePercent).toDp()
        sizes.canvas = (sizes.squareHeight * ratios.canvasHeightPercent)
        sizes.canvasDp = sizes.canvas.toDp()

        val size = sizes.canvas

        val oneFourthWidth = size * (1F/ 4F)
        val twoFourthWidth = size * (2F/ 4F)
        val threeFourthWidth = size * (3F/ 4F)

        val zeroFourthHeight = size * 0.10F
        val oneFourthHeight = zeroFourthHeight + size * 0.80F * (1F/ 4F)
        val threeFourthHeight = zeroFourthHeight + size * 0.80F * (3F/ 4F)
        val fourthFourthHeight = zeroFourthHeight + size * 0.80F

        points.p1 = Offset(x = twoFourthWidth, y = zeroFourthHeight)
        points.p2 = Offset(x = oneFourthWidth, y = oneFourthHeight)
        points.p3 = Offset(x = threeFourthWidth, y = oneFourthHeight)
        points.p4 = Offset(x = oneFourthWidth, y = threeFourthHeight)
        points.p5 = Offset(x = threeFourthWidth, y = threeFourthHeight)
        points.p6 = Offset(x = twoFourthWidth, y = fourthFourthHeight)

        sizes.iconSmallStroke = squareSize * ratios.iconSmallStrokeHeightPercent
        sizes.iconBigStroke = squareSize * ratios.iconBigStrokeHeightPercent

        displayDataUI?.let {
            wLog("BluetoothIconAdapter::init", "start")
            vLog("BluetoothIconAdapter::init", "squareHeight ${sizes.squareHeight}")
            vLog("BluetoothIconAdapter::init", "squareHeightDp ${sizes.squareHeightDp}")
            vLog("BluetoothIconAdapter::init", "squareStrokeDp ${sizes.squareStrokeDp}")
            vLog("BluetoothIconAdapter::init", "canvasHeight ${sizes.canvas}")
            vLog("BluetoothIconAdapter::init", "canvasHeightDp ${sizes.canvasDp}")
            vLog("BluetoothIconAdapter::init", "iconSmallStrokeDp ${sizes.iconSmallStroke}")
            vLog("BluetoothIconAdapter::init", "iconBigStrokeDp ${sizes.iconBigStroke}")
        }
    }
}