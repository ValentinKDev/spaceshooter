package com.mobilegame.spaceshooter.domain.model.screen.uiAdapter

import android.content.Context
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import com.mobilegame.spaceshooter.presentation.theme.MyColor
import com.mobilegame.spaceshooter.utils.analyze.displayDataUI
import com.mobilegame.spaceshooter.utils.analyze.vLog
import com.mobilegame.spaceshooter.utils.analyze.wLog
import com.mobilegame.spaceshooter.utils.extensions.toDp

class WifiIconAdapter(
    context: Context,
    squareSize: Float,
) {
    val ratios = RatiosButtonWifi()
    val sizes = SizesButtonWifi()
    val circles = CircleButtonWifi()
    val color = ColorButtonWifi()
    val stroke = StrokeButtonWifi()

    data class RatiosButtonWifi (
         val squareHeightPercent: Float = 0.30F,
         val squareStrokePercent: Float = 0.03F,
         val canvasHeightPercent: Float = 1F,
         val p1strokeHeightPercent: Float = 0.007F,
         val p1InnerHeightPercent: Float = 0.0035F,
         val iconSmallStrokeHeightPercent: Float = 0.05F,
         val iconBigStrokeHeightPercent: Float = 0.10F,
    )

    data class SizesButtonWifi (
        var squareHeight: Float = 0F,
        var squareHeightDp: Dp = Dp.Unspecified,
        var canvas: Float = 0F,
        var canvasDp: Dp = Dp.Unspecified,
    )

    data class StrokeButtonWifi (
        var squareStrokeDp: Dp = Dp.Unspecified,
        var bigStroke: Float = 0F,
        var smallStroke: Float = 0F,
    )

    data class CircleButtonWifi (
        var p1Radius: Float = 0F,
        var p1RadiusInner: Float = 0F,
        var p1CanvasSize: Float = 0F,
        var p1CanvasSizeDp: Dp = Dp.Unspecified,
        var p2CanvasSizeDp: Dp = Dp.Unspecified,
        var p3CanvasSizeDp: Dp = Dp.Unspecified,
        var p4CanvasSizeDp: Dp = Dp.Unspecified,
    )

    data class ColorButtonWifi (
        var icon: Color = MyColor.applicationContrast,
        var iconInner: Color = MyColor.applicationBackground,
    )

    private var density = 0F

    init {
        density = context.resources.displayMetrics.density

        sizes.squareHeight = squareSize
        sizes.squareHeightDp = sizes.squareHeight.toDp( density )
        stroke.squareStrokeDp = (squareSize * ratios.squareStrokePercent).toDp( density )
        sizes.canvas = sizes.squareHeight * ratios.canvasHeightPercent
        sizes.canvasDp = sizes.canvas.toDp( density )

        circles.p1CanvasSize = sizes.canvas * 0.2F
        circles.p1CanvasSizeDp = circles.p1CanvasSize.toDp( density )
        circles.p1Radius = circles.p1CanvasSize / 3F
        circles.p1RadiusInner = circles.p1CanvasSize * (2F / 9F)
        circles.p2CanvasSizeDp = sizes.canvasDp * 0.40F
        circles.p3CanvasSizeDp = sizes.canvasDp * 0.70F
        circles.p4CanvasSizeDp = sizes.canvasDp * 1F

        stroke.smallStroke = squareSize * ratios.iconSmallStrokeHeightPercent
        stroke.bigStroke = squareSize * ratios.iconBigStrokeHeightPercent

        displayDataUI?.let {
            wLog("MainScreenObj::initWifiButton", "start")
            vLog("MainScreenObj::initWifiButton", "squareHeight ${sizes.squareHeight}")
            vLog("MainScreenObj::initWifiButton", "squareHeightDp ${sizes.squareHeightDp}")
            vLog("MainScreenObj::initWifiButton", "squareStrokeDp ${stroke.squareStrokeDp}")
            vLog("MainScreenObj::initWifiButton", "canvas ${sizes.canvas}")
            vLog("MainScreenObj::initWifiButton", "canvasDp ${sizes.canvasDp}")
            vLog("MainScreenObj::initWifiButton", "p1Stroke ${2 * density}")
            vLog("MainScreenObj::initWifiButton", "bigStroke ${8 * density}")
            vLog("MainScreenObj::initWifiButton", "bigStroke ${stroke.bigStroke}")
            vLog("MainScreenObj::initWifiButton", "smallStroke ${3.5 * density}")
            vLog("MainScreenObj::initWifiButton", "smallStroke ${stroke.smallStroke}")
        }
    }
}
