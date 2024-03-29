package com.mobilegame.spaceshooter.logic.uiHandler.screens.menu.letters

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.unit.DpSize
import com.mobilegame.spaceshooter.presentation.ui.screens.menuScreen.letters.getListEllipseOffset
import com.mobilegame.spaceshooter.utils.extensions.*

class LetterPUI(val canvasSizeDp: DpSize) {
    private val canvasSizePx: Float = canvasSizeDp.width.DpToPixel()
    val strokeWidth = canvasSizePx / 80F

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

    private val barWidth = 0.25F * canvasSizePx
    private val inEllipseRadiusAlpha = 0.15F
    private val inEllipseXAlpha = 1.1F
    private val inEllipseYAlpha = .8F
    private val inEllipseList = getInEllipseList()
    private fun getInEllipseList(): List<Offset> {
        val ellipseCenter = Offset(
            x = canvasSizePx * (1 - (extEllipseRadiusAlpha * extEllipseXAlpha)) - strokeWidth,
            y = canvasSizePx * (extEllipseRadiusAlpha * extEllipseYAlpha) + strokeWidth,
        )
        val ellipseAngleRange = (-90F sweepAngle 180F).degreeToRadianRange()
        val ellipseRadius = canvasSizePx * inEllipseRadiusAlpha
        val ellipseAlphaX = inEllipseXAlpha
        val ellipseBetaY = inEllipseYAlpha

        return getListEllipseOffset(
            center = ellipseCenter,
            radius = ellipseRadius,
            alphaX = ellipseAlphaX,
            betaY = ellipseBetaY,
            angleRange = ellipseAngleRange
        )
    }

    val squarePath = Path().apply {
        //bottom right
        moveTo(canvasSizePx - strokeWidth, canvasSizePx - strokeWidth)
        //top right
        lineTo(canvasSizePx - strokeWidth, canvasSizePx - strokeWidth - barWidth + strokeWidth)
        //top left
        lineTo(canvasSizePx - strokeWidth - barWidth + strokeWidth, canvasSizePx - strokeWidth - barWidth + strokeWidth)
        //bottom right
        lineTo(canvasSizePx - strokeWidth - barWidth + strokeWidth, canvasSizePx - strokeWidth)
        //bottom right
        lineTo(canvasSizePx - strokeWidth, canvasSizePx - strokeWidth)
    }

    val pathPIn = Path().apply {
        moveTo(inEllipseList.first().x, inEllipseList.first().y)
        for (index in inEllipseList.indices) {
            val point = Offset(inEllipseList[index].x, inEllipseList[index].y)
            lineTo(point.x, point.y)
        }
        lineTo(inEllipseList.last().x - (0.1F * canvasSizePx), inEllipseList.last().y)
        lineTo(inEllipseList.last().x - (0.1F * canvasSizePx), inEllipseList.first().y)
        lineTo(inEllipseList.first().x, inEllipseList.first().y)
    }

    val pathPExt = Path().apply {
        //top Left
        moveTo(strokeWidth, canvasSizePx - strokeWidth)
        lineTo(strokeWidth, extEllipseList.first().y)
        lineTo(extEllipseList.first().x, extEllipseList.first().y)
        //top Ellipse
        for (index in extEllipseList.indices) {
            val point = Offset(extEllipseList[index].x, extEllipseList[index].y)
            lineTo(point.x, point.y)
        }
        //bottom Ellipse
        lineTo(barWidth, extEllipseList.last().y)
        //bottom Left
        lineTo(barWidth, canvasSizePx - strokeWidth)
        lineTo(strokeWidth, canvasSizePx - strokeWidth)
    }

    private val ellipseHeight = extEllipseYAlpha * extEllipseRadiusAlpha * canvasSizePx * 2F
    private val paddingRect = 0.15F * canvasSizePx
    private val rectHeightPixelWithStroke = ellipseHeight - (2 * paddingRect)
    private val rectWidthPixelWithStroke = canvasSizePx * 0.20F
    private val rectSidePixelWithoutStroke = rectHeightPixelWithStroke - 2F * strokeWidth
    val rectanglePath = Path().apply {
        //top left
        moveTo( barWidth, extEllipseList.first().y + paddingRect + strokeWidth)
        //bottom left
        lineTo( barWidth, extEllipseList.first().y + paddingRect - strokeWidth + rectHeightPixelWithStroke)
        //bottom right
        lineTo(barWidth + rectWidthPixelWithStroke, extEllipseList.first().y + paddingRect - strokeWidth + rectHeightPixelWithStroke)
        //top right
        lineTo(barWidth + rectWidthPixelWithStroke, extEllipseList.first().y + paddingRect + strokeWidth )
        //top left
        lineTo( barWidth, extEllipseList.first().y + paddingRect + strokeWidth)
    }


}