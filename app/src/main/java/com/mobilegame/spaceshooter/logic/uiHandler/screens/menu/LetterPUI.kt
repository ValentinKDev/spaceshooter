package com.mobilegame.spaceshooter.logic.uiHandler.screens.menu

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.unit.Dp
import com.mobilegame.spaceshooter.presentation.ui.screens.menu.letters.getListEllipseOffset
import com.mobilegame.spaceshooter.utils.extensions.DpToPixel
import com.mobilegame.spaceshooter.utils.extensions.sweepAngle
import com.mobilegame.spaceshooter.utils.extensions.degreeToRadianRange

class LetterPUI(val canvasSizeDp: Dp, val padding: Dp) {
    private val canvasSizePixel: Float = canvasSizeDp.DpToPixel()
    val strokeWidth = canvasSizePixel / 80F

    private val extEllipseRadiusAlpha = 0.36F
    private val extEllipseXAlpha = 1F
    private val extEllipseYAlpha = .8F
    private val extEllipseList = getExtEllipseList()
    private fun getExtEllipseList(): List<Offset> {
        val ellipseCenter = Offset(
            x = canvasSizePixel * (1 - (extEllipseRadiusAlpha * extEllipseXAlpha)) - strokeWidth,
            y = canvasSizePixel * (extEllipseRadiusAlpha * extEllipseYAlpha) + strokeWidth,
        )
        val ellipseAngleRange = (-90F sweepAngle 180F).degreeToRadianRange()
        val ellipseRadius = canvasSizePixel * extEllipseRadiusAlpha
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

    private val barWidth = 0.25F * canvasSizePixel
    private val inEllipseRadiusAlpha = 0.15F
    private val inEllipseXAlpha = 1.1F
    private val inEllipseYAlpha = .8F
    private val inEllipseList = getInEllipseList()
    private fun getInEllipseList(): List<Offset> {
        val ellipseCenter = Offset(
            x = canvasSizePixel * (1 - (extEllipseRadiusAlpha * extEllipseXAlpha)) - strokeWidth,
            y = canvasSizePixel * (extEllipseRadiusAlpha * extEllipseYAlpha) + strokeWidth,
        )
//        val ellipseCenter = Offset(
//            x = canvasSizePixel * (1 - (inEllipseRadiusAlpha * inEllipseXAlpha)) - strokeWidth,
//            y = canvasSizePixel * (inEllipseRadiusAlpha * inEllipseYAlpha) + strokeWidth,
//        )
        val ellipseAngleRange = (-90F sweepAngle 180F).degreeToRadianRange()
        val ellipseRadius = canvasSizePixel * inEllipseRadiusAlpha
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

    fun getsquarePath() = Path().apply {
        //bottom right
        moveTo(canvasSizePixel - strokeWidth, canvasSizePixel - strokeWidth)
        //top right
        lineTo(canvasSizePixel - strokeWidth, canvasSizePixel - strokeWidth - barWidth + strokeWidth)
        //top left
        lineTo(canvasSizePixel - strokeWidth - barWidth + strokeWidth, canvasSizePixel - strokeWidth - barWidth + strokeWidth)
        //bottom right
        lineTo(canvasSizePixel - strokeWidth - barWidth + strokeWidth, canvasSizePixel - strokeWidth)
        //bottom right
        lineTo(canvasSizePixel - strokeWidth, canvasSizePixel - strokeWidth)
    }

    fun getPathPIn() = Path().apply {
        moveTo(inEllipseList.first().x, inEllipseList.first().y)
        for (index in inEllipseList.indices) {
            val point = Offset(inEllipseList[index].x, inEllipseList[index].y)
            lineTo(point.x, point.y)
        }
        lineTo(inEllipseList.last().x - (0.1F * canvasSizePixel), inEllipseList.last().y)
        lineTo(inEllipseList.last().x - (0.1F * canvasSizePixel), inEllipseList.first().y)
        lineTo(inEllipseList.first().x, inEllipseList.first().y)
    }

    fun getPathPExt() = Path().apply {
        //top Left
        moveTo(strokeWidth, canvasSizePixel - strokeWidth)
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
        lineTo(barWidth, canvasSizePixel - strokeWidth)
        lineTo(strokeWidth, canvasSizePixel - strokeWidth)
    }

    private val ellipseHeight = extEllipseYAlpha * extEllipseRadiusAlpha * canvasSizePixel * 2F
    private val paddingRect = 0.15F * canvasSizePixel
    private val rectHeightPixelWithStroke = ellipseHeight - (2 * paddingRect)
    private val rectWidthPixelWithStroke = canvasSizePixel * 0.20F
    private val rectSidePixelWithoutStroke = rectHeightPixelWithStroke - 2F * strokeWidth
    fun getRectanglePath() = Path().apply {
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