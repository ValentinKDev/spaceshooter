package com.mobilegame.spaceshooter.logic.uiHandler.screens.menu.startMenu.letters

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.unit.DpSize
import com.mobilegame.spaceshooter.presentation.ui.screens.menuScreen.letters.getListEllipseOffset
import com.mobilegame.spaceshooter.utils.extensions.dpToPixel
import com.mobilegame.spaceshooter.utils.extensions.degreeToRadianRange
import com.mobilegame.spaceshooter.utils.extensions.sweepAngle

class LetterRUI(val canvasSizeDp: DpSize) {
    private val canvasSizePx: Float = canvasSizeDp.width.dpToPixel()
    val strokeWidth = canvasSizePx / 80F
    private val xMin = strokeWidth
    private val xMax = canvasSizePx - strokeWidth
    private val yMin = xMin
    private val yMax = xMax
    private val center = canvasSizePx / 2F
    private val barWidth = canvasSizePx * 0.22F
    private val padding = canvasSizePx * 0.06F

    val pathVerticalBar = Path().apply {
        moveTo(xMin, yMin)
        lineTo(xMin + barWidth, yMin)
        lineTo(xMin + barWidth, yMax)
        lineTo(xMin, yMax)
        lineTo(xMin, yMin)
    }

    private val extEllipseRadiusAlpha = 0.36F
    private val extEllipseXAlpha = 1F
    private val extEllipseYAlpha = .8F
    private val extEllipseList = getExtEllipseList()
    private fun getExtEllipseList(): List<Offset> {
        val ellipseCenter = Offset(
            x = canvasSizePx * (1 - (extEllipseRadiusAlpha * extEllipseXAlpha)) - strokeWidth,
            y = canvasSizePx * (extEllipseRadiusAlpha * extEllipseYAlpha) + strokeWidth,
        )
        val ellipseAngleRange = (-90F sweepAngle 180F).degreeToRadianRange()
        val ellipseRadius = canvasSizePx * extEllipseRadiusAlpha
        val ellipseAlphaX = extEllipseXAlpha
        val ellipseBetaY = extEllipseYAlpha

        return getListEllipseOffset(
            center = ellipseCenter,
            radius = ellipseRadius,
            alphaX = ellipseAlphaX,
            betaY = ellipseBetaY,
            angleRange = ellipseAngleRange
        )
    }

    val pathElipse = Path().apply {
        moveTo(extEllipseList.first().x, extEllipseList.first().y)
        for (index in extEllipseList.indices) {
            val point = Offset(extEllipseList[index].x, extEllipseList[index].y)
            lineTo(point.x, point.y)
        }
        lineTo(xMin + barWidth + strokeWidth + padding, extEllipseList.last().y)
        lineTo(xMin + barWidth + strokeWidth + padding, extEllipseList.first().y)
        lineTo(extEllipseList.first().x, extEllipseList.first().y)
    }

    private val delta = canvasSizePx * 0.12F
    val pathSquare1 = Path().apply {
        moveTo(center, extEllipseList.last().y + padding + strokeWidth)
        lineTo(center, extEllipseList.last().y + padding + strokeWidth + barWidth)
        lineTo(center + delta, yMax)
        lineTo(center + barWidth + delta, yMax)
        lineTo(center + barWidth,  extEllipseList.last().y + padding + strokeWidth)
        lineTo(center,  extEllipseList.last().y + padding + strokeWidth)
    }
}