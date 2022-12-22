package com.mobilegame.spaceshooter.domain.model.screen.uiHandler.Icons

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.unit.Dp
import com.mobilegame.spaceshooter.presentation.theme.MyColor
import com.mobilegame.spaceshooter.utils.extensions.toDp

class WifiIcon(
    squareSize: Float,
    strokeCap: StrokeCap,
    val outlined: Boolean? = null
) {
    val ratios = RatiosWifiIconAdapter()
    val sizes = SizesWifiIconAdapter()
    val shapes = ShapesWifiIconAdapter()
    val colors = ColorButtonWifi()
    val stroke = StrokeWifiIconAdapter()

    data class RatiosWifiIconAdapter (
         val squareHeightPercent: Float = 0.30F,
         val squareStrokePercent: Float = 0.03F,
         val canvasHeightPercent: Float = 1F,
         val rSmallCircle: Float = 0.04F,
         val rBigCircle: Float = 0.065F,
         val r1size: Float = (1F/ 3F),
         val r2size: Float = (4F/ 6F),
         val r3size: Float = 1F,
         val r1offset: Float = (1F/ 3F),
         val r2offset: Float = (1F/ 6F),
         val r3offset: Float = 0F,
    )

    data class SizesWifiIconAdapter (
        var squareHeight: Float = 0F,
        var squareHeightDp: Dp = Dp.Unspecified,
        var canvas: Float = 0F,
        var canvasDp: Dp = Dp.Unspecified,

        var size1: Size = Size.Zero,
        var size2: Size = Size.Zero,
        var size3: Size = Size.Zero,
        var smallCircleSize: Float = 0F,
        var bigCircleSize: Float = 0F,
    )

    data class StrokeWifiIconAdapter (
        var squareStrokeDp: Dp = Dp.Unspecified,
        var strokeCap: StrokeCap = StrokeCap.Square,
        var small: Stroke = Stroke(width = 0F, cap = StrokeCap.Butt),
        var big: Stroke = Stroke(width = 0F, cap = StrokeCap.Butt),
    )

    data class ShapesWifiIconAdapter (
        var topLeft1: Offset = Offset.Zero,
        var topLeft2: Offset = Offset.Zero,
        var topLeft3: Offset = Offset.Zero,
        var smallCircleCenter: Offset = Offset.Zero,
        var bigCircleCenter: Offset = Offset.Zero,
    )

    data class ColorButtonWifi (
        var icon: Color = MyColor.applicationContrast,
        var iconInner: Color = MyColor.applicationBackground,
        var transparentStart: Color = MyColor.applicationBackgroundBannerInitial,
        var transparentTarget: Color = MyColor.applicationBackgroundBannerTarget,
    )

    init {
        sizes.squareHeight = squareSize
        sizes.squareHeightDp = squareSize.toDp()
        stroke.squareStrokeDp = (squareSize * ratios.squareStrokePercent).toDp()
        sizes.canvas = sizes.squareHeight * ratios.canvasHeightPercent
        sizes.canvasDp = sizes.canvas.toDp()

        sizes.size1 = Size(sizes.canvas * ratios.r1size, sizes.canvas * ratios.r1size)
        sizes.size2 = Size(sizes.canvas * ratios.r2size, sizes.canvas * ratios.r2size)
        sizes.size3 = Size(sizes.canvas * ratios.r3size, sizes.canvas * ratios.r3size)
        sizes.smallCircleSize = sizes.canvas * ratios.rSmallCircle
        sizes.bigCircleSize = sizes.canvas * ratios.rBigCircle

        shapes.topLeft1 = Offset(sizes.canvas * (ratios.r1offset), sizes.canvas * ratios.r1offset)
        shapes.topLeft2 = Offset(sizes.canvas * (ratios.r2offset), sizes.canvas * ratios.r2offset)
        shapes.topLeft3 = Offset(ratios.r3offset, ratios.r3offset)
        shapes.smallCircleCenter = Offset(sizes.canvas * 0.5F, sizes.canvas * 0.5F)
        shapes.bigCircleCenter = Offset(sizes.canvas * 0.5F, sizes.canvas * 0.5F)

        stroke.big = Stroke(width = (sizes.canvas * 0.1F), cap = strokeCap)
        stroke.small = Stroke(width = (sizes.canvas * 0.05F), cap = strokeCap)
    }
}
