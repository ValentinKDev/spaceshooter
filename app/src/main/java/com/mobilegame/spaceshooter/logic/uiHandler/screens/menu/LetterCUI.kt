package com.mobilegame.spaceshooter.logic.uiHandler.screens.menu

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.unit.Dp
import com.mobilegame.spaceshooter.presentation.ui.screens.menu.letters.getListEllipseOffset
import com.mobilegame.spaceshooter.utils.extensions.DpToPixel
import com.mobilegame.spaceshooter.utils.extensions.degreeToRadianRange
import com.mobilegame.spaceshooter.utils.extensions.reverseElements
import com.mobilegame.spaceshooter.utils.extensions.sweepAngle

class LetterCUI(val canvasSizeDp: Dp, val padding: Dp) {
    private val canvasSizePx: Float = canvasSizeDp.DpToPixel()
    val strokeWidth = canvasSizePx / 80F

    private val spaceBetweenElements = canvasSizePx * 0.03F

    private val topEllipseRadiusAlpha = 0.38F
    private val topEllipseXAlpha = .9F
    private val topEllipseYAlpha = 1.1F
    private val topEllipseExtList = getTopExtEllipse()
    private fun getTopExtEllipse(): List<Offset> {
        val ellipseCenter = Offset(
            x = canvasSizePx * 0.36F,
            y = (canvasSizePx / 2F) - spaceBetweenElements,
        )
        val ellipseAngleRange = (90F sweepAngle 90F).degreeToRadianRange()
        val ellipseRadius = canvasSizePx * topEllipseRadiusAlpha
        val ellipseAlphaX = topEllipseXAlpha
        val ellipseBetaY = topEllipseYAlpha

        return getListEllipseOffset(
            center = ellipseCenter,
            radius = ellipseRadius,
            alphaX = ellipseAlphaX,
            betaY = ellipseBetaY,
            angleRange = ellipseAngleRange
        )
    }
    private val bottomExtEllipseList = getBottomExtEllipse()
    private fun getBottomExtEllipse(): List<Offset> {
        val ellipseCenter = Offset(
            x = canvasSizePx * 0.36F,
            y = (canvasSizePx / 2F) + spaceBetweenElements,
        )
        val ellipseAngleRange = (180F sweepAngle 90F).degreeToRadianRange()
        val ellipseRadius = canvasSizePx * topEllipseRadiusAlpha
        val ellipseAlphaX = topEllipseXAlpha
        val ellipseBetaY = topEllipseYAlpha

        return getListEllipseOffset(
            center = ellipseCenter,
            radius = ellipseRadius,
            alphaX = ellipseAlphaX,
            betaY = ellipseBetaY,
            angleRange = ellipseAngleRange
        )
    }
    private val ellipseInRadiusAlpha = 0.18F
    private val ellipseInXAlpha = .9F
    private val ellipseInYAlpha = 1.15F
    private val topEllipseInList = getTopInEllipse().reverseElements()
    private fun getTopInEllipse(): List<Offset> {
        val ellipseCenter = Offset(
            x = canvasSizePx * 0.38F,
            y = (canvasSizePx / 2F) - spaceBetweenElements,
        )
        val ellipseAngleRange = (90F sweepAngle 90F).degreeToRadianRange()
        val ellipseRadius = canvasSizePx * ellipseInRadiusAlpha
        val ellipseAlphaX = ellipseInXAlpha
        val ellipseBetaY = ellipseInYAlpha

        return getListEllipseOffset(
            center = ellipseCenter,
            radius = ellipseRadius,
            alphaX = ellipseAlphaX,
            betaY = ellipseBetaY,
            angleRange = ellipseAngleRange
        )
    }
    private val bottomInEllipseList = getBottomInEllipseList().reverseElements()
    private fun getBottomInEllipseList(): List<Offset> {
        val ellipseCenter = Offset(
            x = canvasSizePx * 0.38F,
            y = (canvasSizePx / 2F) + spaceBetweenElements,
        )
        val ellipseAngleRange = (180F sweepAngle 90F).degreeToRadianRange()
        val ellipseRadius = canvasSizePx * ellipseInRadiusAlpha
        val ellipseAlphaX = ellipseInXAlpha
        val ellipseBetaY = ellipseInYAlpha

        return getListEllipseOffset(
            center = ellipseCenter,
            radius = ellipseRadius,
            alphaX = ellipseAlphaX,
            betaY = ellipseBetaY,
            angleRange = ellipseAngleRange
        )
    }

//    private val ellipticLengthX = canvasSizePx * 0.7F
    private val ellipticLengthX = canvasSizePx - strokeWidth
    fun getTopEllipsePath() = Path().apply {
        moveTo(topEllipseExtList.first().x, topEllipseExtList.first().y)
        for (index in topEllipseExtList.indices) {
            val point = Offset(topEllipseExtList[index].x, topEllipseExtList[index].y)
            lineTo(point.x, point.y)
        }
        lineTo(ellipticLengthX, topEllipseExtList.last().y)
        lineTo(ellipticLengthX, topEllipseInList.first().y)
        for (index in topEllipseInList.indices) {
            val point = Offset(topEllipseInList[index].x, topEllipseInList[index].y)
            lineTo(point.x, point.y)
        }
        lineTo(topEllipseExtList.first().x, topEllipseExtList.first().y)
    }

    fun getBottomEllipsePath() = Path().apply {
        moveTo(bottomExtEllipseList.first().x, bottomExtEllipseList.first().y)
        for (index in bottomExtEllipseList.indices) {
            val point = Offset(bottomExtEllipseList[index].x, bottomExtEllipseList[index].y)
            lineTo(point.x, point.y)
        }
        for (index in bottomInEllipseList.indices) {
            val point = Offset(bottomInEllipseList[index].x, bottomInEllipseList[index].y)
            lineTo(point.x, point.y)
        }
        lineTo(ellipticLengthX, bottomInEllipseList.last().y)
        lineTo(ellipticLengthX, bottomExtEllipseList.first().y)
        lineTo(bottomExtEllipseList.first().x, bottomExtEllipseList.first().y)
    }
}