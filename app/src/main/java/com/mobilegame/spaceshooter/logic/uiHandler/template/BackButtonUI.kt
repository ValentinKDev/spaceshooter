package com.mobilegame.spaceshooter.logic.uiHandler.template

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import com.mobilegame.spaceshooter.data.device.Device
import com.mobilegame.spaceshooter.presentation.theme.MyColor
import com.mobilegame.spaceshooter.presentation.ui.screens.menuScreen.letters.getListEllipseOffset
import com.mobilegame.spaceshooter.utils.extensions.dpToPixel
import com.mobilegame.spaceshooter.utils.extensions.degreeToRadianRange
import com.mobilegame.spaceshooter.utils.extensions.sweepAngle
import com.mobilegame.spaceshooter.utils.extensions.toDp
import com.mobilegame.spaceshooter.utils.extensions.toSquare

class BackButtonUI(percent: TemplateUI.TemplatePercents, val instant: Boolean = false) {
    val id = "back_button"
//    val instant = true
    val padding = PaddingBackButton()
    val colors = ColorsBackButton()
    val points = PointsBackButton()
    val sizes = SizesBackButton(percent)
    lateinit var path: Path
    val stroke = Stroke(3.dp.dpToPixel(), cap = StrokeCap.Round)

    init {
        initPoints()
        initPath()
    }


    data class PaddingBackButton (
        var start: Float = 0.01F,
    )
    class SizesBackButton(percent: TemplateUI.TemplatePercents) {
        val boxHeight: Float = percent.header * Device.metrics.height
        val boxHeightDp: Dp = boxHeight.toDp()
        val canvas: Float = boxHeight * 0.75F
        val canvasDp: Dp = canvas.toDp()
        val canvasSizeDp: DpSize = canvasDp.toSquare()
    }
    data class ColorsBackButton (
        val contrast: Color = MyColor.applicationContrast,
        val background: Color = MyColor.applicationBackground,
//        val background: Color = MyColor.transparent,
    )
    data class PointsBackButton (
        var center: Offset = Offset.Zero,
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

        var listOffsetRound: List<Offset> = listOf()
    )
    fun initPoints() {
        //init arrowPoints
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

        points.center = Offset(halfSize, halfSize)
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

        //init arc points
        val radius = halfSize
        val alpha = 1F
        val beta = 1F
        val angleRange = (17F sweepAngle 323F).degreeToRadianRange()

        points.listOffsetRound = getListEllipseOffset(
            center = points.center,
            radius = radius,
            alphaX = alpha,
            betaY = beta,
            angleRange = angleRange
        )
    }
    private fun initPath() {
        path = Path().apply {
            moveTo(points.listOffsetRound.first().x, points.listOffsetRound.first().y)
            for (index in points.listOffsetRound.indices) {
                val point = Offset(points.listOffsetRound[index].x, points.listOffsetRound[index].y)
                lineTo(point.x, point.y)
            }
            lineTo(points.p1.x, points.p1.y)
            lineTo(points.p2.x, points.p2.y)
            lineTo(points.p3.x, points.p3.y)
            lineTo(points.p4.x, points.p4.y)
            lineTo(points.p5.x, points.p5.y)
            lineTo(points.p6.x, points.p6.y)
            lineTo(points.p7.x, points.p7.y)
            lineTo(points.p8.x, points.p8.y)
            lineTo(points.p9.x, points.p9.y)
        }
    }
}
