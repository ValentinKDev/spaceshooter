package com.mobilegame.spaceshooter.logic.uiHandler.screens.menu

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.unit.Dp
import com.mobilegame.spaceshooter.presentation.ui.screens.menu.letters.getListEllipseOffset
import com.mobilegame.spaceshooter.utils.extensions.*

class LetterSUI(val canvasSizeDp: Dp, val padding: Dp) {
    private val canvasSizePixel = canvasSizeDp.DpToPixel()
    val strokeWidth = canvasSizePixel / 80

    private val inEllipseXOffsetAlpha = 0.33F
    private val inEllipseYOffsetAlpha = 0.31F
    private val inEllipseRadiusAlpha = 0.11F
    private val inEllipseXAlpha = 1F
    private val inEllipseYAlpha = .8F
    private val inEllipseRadianRotation = 170F

    private val extEllipseXOffsetAlpha = 0.38F
    private val extEllipseYOffsetAlpha = 0.32F
    private val extEllipseRadiusAlpha = 0.37F
    private val extEllipseXAlpha = 1F
    private val extEllipseYAlpha = .8F
    private val extEllipseRadianRotation = 170F

    private val topInEllipseList = getTopInEllipseList()
    private val topExtEllipseList = getTopExtEllipseList()
    private val bottomInEllipseList = getBottomInEllipseList()
    private val bottomExtEllipseList = getBottomExtEllipseList()

    private val topLineHeightWithoutStroke = topInEllipseList.first().y - topExtEllipseList.last().y - strokeWidth
    private val squareSizeWithoutStroke = topLineHeightWithoutStroke
    private val squareSizeWithStroke = topLineHeightWithoutStroke + strokeWidth
    private val paddingBetweenLetterAndSquare = canvasSizePixel * 0.05F

    private fun getTopExtEllipseList(): List<Offset> {
        val topExtEllipseCenter = Offset(canvasSizePixel * extEllipseXOffsetAlpha, canvasSizePixel * extEllipseYOffsetAlpha )
        val topExtEllipseAngleRange = (90F sweepAngle extEllipseRadianRotation).degreeToRadianRange()
        val topExtEllipseRadius = canvasSizePixel * extEllipseRadiusAlpha
        val topExtEllipseAlphaX = extEllipseXAlpha
        val topExtEllipseBetaY = extEllipseYAlpha

        return getListEllipseOffset(
            center = topExtEllipseCenter,
            radius = topExtEllipseRadius,
            alphaX = topExtEllipseAlphaX,
            betaY = topExtEllipseBetaY,
            angleRange = topExtEllipseAngleRange
        )
    }
    private fun getBottomExtEllipseList(): List<Offset> {
        val bottomExtEllipseCenter = Offset(canvasSizePixel * (1F - extEllipseXOffsetAlpha), (canvasSizePixel * (1F - extEllipseYOffsetAlpha)))
        val bottomExtEllipseAngleRange = (-90F sweepAngle extEllipseRadianRotation).degreeToRadianRange()
        val bottomExtEllipseRadius = canvasSizePixel * extEllipseRadiusAlpha
        val bottomExtEllipseAlphaX = extEllipseXAlpha
        val bottomExtEllipseBetaY = extEllipseYAlpha

        return getListEllipseOffset(
            center = bottomExtEllipseCenter,
            radius = bottomExtEllipseRadius,
            alphaX = bottomExtEllipseAlphaX,
            betaY = bottomExtEllipseBetaY,
            angleRange = bottomExtEllipseAngleRange
        )
    }

    private fun getTopInEllipseList(): List<Offset> {
        val topInEllipseCenter = Offset(canvasSizePixel * inEllipseXOffsetAlpha, ( canvasSizePixel * inEllipseYOffsetAlpha ))
        val topInEllipseAngleRange = (90F sweepAngle inEllipseRadianRotation).degreeToRadianRange()
        val topInEllipseRadius = canvasSizePixel * inEllipseRadiusAlpha
        val topInEllipseAlphaX = inEllipseXAlpha
        val topInEllipseBetaY = inEllipseYAlpha

        return getListEllipseOffset(
            center = topInEllipseCenter,
            radius = topInEllipseRadius,
            alphaX = topInEllipseAlphaX,
            betaY = topInEllipseBetaY,
            angleRange = topInEllipseAngleRange
        ).reverseElements()
    }

    private fun getBottomInEllipseList(): List<Offset> {
        val bottomInEllipseCenter = Offset(canvasSizePixel * (1F - inEllipseXOffsetAlpha), (canvasSizePixel * (1F - inEllipseYOffsetAlpha)))
        val bottomInEllipseAngleRange = (-90F sweepAngle inEllipseRadianRotation).degreeToRadianRange()
        val bottomInEllipseRadius = canvasSizePixel * inEllipseRadiusAlpha
        val bottomInEllipseAlphaX = inEllipseXAlpha
        val bottomInEllipseBetaY = inEllipseYAlpha

        return getListEllipseOffset(
            center = bottomInEllipseCenter,
            radius = bottomInEllipseRadius,
            alphaX = bottomInEllipseAlphaX,
            betaY = bottomInEllipseBetaY,
            angleRange = bottomInEllipseAngleRange
        ).reverseElements()
    }

    fun getUPath() = Path().apply {
        // Bottom Exterior Ellipse
        moveTo(bottomExtEllipseList.first().x, bottomExtEllipseList.first().y)
        for (index in bottomExtEllipseList.indices) {
            val point = Offset(bottomExtEllipseList[index].x, bottomExtEllipseList[index].y)
            lineTo(point.x, point.y)
        }
        lineTo(strokeWidth + squareSizeWithStroke + paddingBetweenLetterAndSquare, bottomExtEllipseList.last().y)

        // Bottom Interior Ellipse
        lineTo(strokeWidth + squareSizeWithStroke + paddingBetweenLetterAndSquare, bottomInEllipseList.first().y)
        lineTo(bottomInEllipseList.first().x, bottomInEllipseList.first().y)
        for (index in bottomInEllipseList.indices) {
            val point = Offset(bottomInEllipseList[index].x, bottomInEllipseList[index].y)
            lineTo(point.x, point.y)
        }

        // Top Exterior Ellipse
        lineTo(topExtEllipseList[0].x, topExtEllipseList[0].y)
        for (index in topExtEllipseList.indices) {
            val point = Offset(topExtEllipseList[index].x, topExtEllipseList[index].y)
            lineTo(point.x, point.y)
        }
        lineTo(canvasSizePixel - strokeWidth - (2 * (squareSizeWithStroke + paddingBetweenLetterAndSquare)), topExtEllipseList.last().y)

        // Top Interior Ellipse
        lineTo(canvasSizePixel - strokeWidth - (2 * (squareSizeWithStroke + paddingBetweenLetterAndSquare)), topInEllipseList.first().y)
        for (index in topInEllipseList.indices) {
            val point = Offset(topInEllipseList[index].x, topInEllipseList[index].y)
            lineTo(point.x, point.y)
        }

        lineTo(bottomExtEllipseList.first().x, bottomExtEllipseList.first().y)
    }

    fun getBottomSquare() = Path().apply {
        //topRight
        moveTo(strokeWidth + squareSizeWithoutStroke, bottomInEllipseList.first().y)
        //bottomRight
        lineTo(strokeWidth + squareSizeWithoutStroke, bottomExtEllipseList.last().y)
        //bottomLeft
        lineTo(strokeWidth, bottomExtEllipseList.last().y)
        //topLeft
        lineTo(strokeWidth, bottomInEllipseList.first().y)
        //topRight
        lineTo(strokeWidth + squareSizeWithoutStroke, bottomInEllipseList.first().y)
    }

    fun getTopSquare1() = Path().apply {
        //topRight
        moveTo(canvasSizePixel - strokeWidth - paddingBetweenLetterAndSquare - squareSizeWithStroke, topExtEllipseList.last().y)
        //bottomRight
        lineTo(canvasSizePixel - strokeWidth - paddingBetweenLetterAndSquare - squareSizeWithStroke, topInEllipseList.first().y)
        //bottomLeft
        lineTo(canvasSizePixel - strokeWidth - squareSizeWithoutStroke - paddingBetweenLetterAndSquare - squareSizeWithStroke, topInEllipseList.first().y)
        //topLeft
        lineTo(canvasSizePixel - strokeWidth - squareSizeWithoutStroke - paddingBetweenLetterAndSquare - squareSizeWithStroke, topExtEllipseList.last().y)
        //topRight
        lineTo(canvasSizePixel - strokeWidth - paddingBetweenLetterAndSquare - squareSizeWithStroke, topExtEllipseList.last().y)
    }

    fun getTopSquare2() = Path().apply {
        //topRight
        moveTo(canvasSizePixel - strokeWidth, topExtEllipseList.last().y)
        //bottomRight
        lineTo(canvasSizePixel - strokeWidth, topInEllipseList.first().y)
        //bottomLeft
        lineTo(canvasSizePixel - strokeWidth - squareSizeWithoutStroke, topInEllipseList.first().y)
        //topLeft
        lineTo(canvasSizePixel - strokeWidth - squareSizeWithoutStroke, topExtEllipseList.last().y)
        //topRight
        lineTo(canvasSizePixel - strokeWidth, topExtEllipseList.last().y)
    }
}