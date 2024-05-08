package com.mobilegame.spaceshooter.logic.uiHandler.screens.menu.startMenu.letters

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.unit.DpSize
import com.mobilegame.spaceshooter.presentation.ui.screens.menuScreen.letters.getListEllipseOffset
import com.mobilegame.spaceshooter.utils.extensions.dpToPixel
import com.mobilegame.spaceshooter.utils.extensions.degreeToRadianRange
import com.mobilegame.spaceshooter.utils.extensions.reverseElements
import com.mobilegame.spaceshooter.utils.extensions.sweepAngle

class LetterDUI(val canvasSizeDp: DpSize) {
    private val canvasSizePx = canvasSizeDp.width.dpToPixel()
    internal val strokeWidth = canvasSizePx / 80F
    private val spaceBetweenElements = canvasSizePx * 0.03F
    private val offsetTopLeft = Offset(x = strokeWidth, y = strokeWidth)
    private val offsetTopRight = Offset(x = canvasSizePx - strokeWidth, y = strokeWidth)
    private val offsetBottomLeft = Offset(x = strokeWidth, y = canvasSizePx - strokeWidth)
    private val offsetBottomRight = Offset(x = canvasSizePx - strokeWidth, y = canvasSizePx - strokeWidth)
    private val standardBarWidth = canvasSizePx * 0.25F


    private val verticalLine = canvasSizePx * 0.35F

    private val ellipseInnerRadiusAlpha = 0.18F
    private val ellipseInnerXAlpha = 1.15F
    private val ellipseInnerYAlpha = 1.3F
    private val innerEllipseList = getInnerEllipseList().reverseElements()
    private fun getInnerEllipseList(): List<Offset> {
        val ellipseCenter = Offset(
            x = canvasSizePx * 0.5F,
            y = (canvasSizePx / 2F),
        )
        val ellipseAngleRange = (-90F sweepAngle 180F).degreeToRadianRange()
        val ellipseRadius = canvasSizePx * ellipseInnerRadiusAlpha
        val ellipseAlphaX = ellipseInnerXAlpha
        val ellipseBetaY = ellipseInnerYAlpha

        return getListEllipseOffset(
            center = ellipseCenter,
            radius = ellipseRadius,
            alphaX = ellipseAlphaX,
            betaY = ellipseBetaY,
            angleRange = ellipseAngleRange
        )
    }

    private val outerEllipseRadiusAlpha = 0.45F
    private val outerEllipseXAlpha = 0.85F
    private val outerEllipseYAlpha = 1.09F
    private val outerEllipseList = getOuterEllipse()
    private fun getOuterEllipse(): List<Offset> {
        val ellipseCenter = Offset(
            x = canvasSizePx * 0.60F,
            y = (canvasSizePx / 2F),
        )
        val ellipseAngleRange = (-90F sweepAngle 180F).degreeToRadianRange()
        val ellipseRadius = canvasSizePx * outerEllipseRadiusAlpha
        val ellipseAlphaX = outerEllipseXAlpha
        val ellipseBetaY = outerEllipseYAlpha

        return getListEllipseOffset(
            center = ellipseCenter,
            radius = ellipseRadius,
            alphaX = ellipseAlphaX,
            betaY = ellipseBetaY,
            angleRange = ellipseAngleRange
        )
    }
    val topEllipsePart = Path().apply {
        //top outer
        moveTo(x = verticalLine + spaceBetweenElements + (2 * strokeWidth), y = offsetTopLeft.y)
        //top inner ellipse
        lineTo(x = verticalLine + spaceBetweenElements + (2 * strokeWidth), y = standardBarWidth + offsetTopLeft.y)
        //inner ellipse
        for (offset in innerEllipseList.withIndex().reversed()) { lineTo(offset.value.x, offset.value.y) }
        //bottom inner
        lineTo(x = verticalLine + spaceBetweenElements + (2 * strokeWidth),y = offsetBottomLeft.y - standardBarWidth)
        //bottom outer
        lineTo(x = verticalLine + spaceBetweenElements + (2 * strokeWidth),y = offsetBottomLeft.y)
        //outer ellipse
        for (offset in outerEllipseList.withIndex().reversed()) { lineTo(offset.value.x, offset.value.y) }
        close()
    }

    val leftLetterPart = Path().apply {
        //top left outer
        moveTo(x = offsetTopLeft.x, y = offsetTopLeft.y)
        //bottom left outer
        lineTo(x = offsetBottomLeft.x, y = offsetBottomLeft.y)
        //bottom right outer
        lineTo(x = verticalLine , y = offsetBottomLeft.y)
        //bottom right inner
        lineTo(x = verticalLine , y = offsetBottomLeft.y - standardBarWidth)
        //bottom left inner
        lineTo(x = offsetBottomLeft.x + standardBarWidth - (2 * strokeWidth), y = offsetBottomLeft.y - standardBarWidth)
        //top left inner
        lineTo(x = offsetBottomLeft.x + standardBarWidth - (2 * strokeWidth), y = standardBarWidth + offsetTopLeft.y)
        //top right inner
        lineTo(x = verticalLine , y = standardBarWidth + offsetTopLeft.y)
        //top right outer
        lineTo(x = verticalLine, y = offsetTopLeft.y)
        //top left outer
//        lineTo(x = offsetTopLeft.x, y = offsetTopLeft.y)
        close()
    }
}