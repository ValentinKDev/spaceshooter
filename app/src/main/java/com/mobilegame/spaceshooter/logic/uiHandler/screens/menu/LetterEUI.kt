package com.mobilegame.spaceshooter.logic.uiHandler.screens.menu

import androidx.compose.ui.graphics.Path
import androidx.compose.ui.unit.DpSize
import com.mobilegame.spaceshooter.utils.extensions.DpToPixel

class LetterEUI(val canvasSizeDp: DpSize) {
    private val canvasSizePx: Float = canvasSizeDp.width.DpToPixel()
    val strokeWidth = canvasSizePx / 80F
    private val xMin = strokeWidth
    private val xMax = canvasSizePx - strokeWidth
    private val yMin = xMin
    private val yMax = xMax

    private val horizontalBarWidth = canvasSizePx * 0.2F
    private val verticalBarWidth = horizontalBarWidth * 1.1F
    private val centerBarCenterY = canvasSizePx * 0.55F
//    private val topBarX = canvasSizePx * 0.48F
    private val topBarX = canvasSizePx * 0.55F
//    private val centerBarX = canvasSizePx * 0.32F
    private val centerBarX = canvasSizePx * 0.35F
    private val bottomBarX = canvasSizePx * 0.71F
    private val squareSide = horizontalBarWidth
    private val squarePadding = canvasSizePx * 0.05F

    val pathE = Path().apply {
        //bottom left
        moveTo(xMin, yMax)
        lineTo(xMin, yMin)
        lineTo(verticalBarWidth, yMin)
        //top bar
        lineTo(topBarX, yMin)
        lineTo(topBarX, yMin + horizontalBarWidth)
        lineTo(verticalBarWidth, yMin + horizontalBarWidth)
        //center bar
        lineTo(verticalBarWidth, (canvasSizePx / 2F) - (horizontalBarWidth / 2F))
        lineTo(centerBarX, (canvasSizePx / 2F) - (horizontalBarWidth / 2F))
        lineTo(centerBarX, (canvasSizePx / 2F) + (horizontalBarWidth / 2F))
        lineTo(verticalBarWidth, (canvasSizePx / 2F) + (horizontalBarWidth / 2F))
        //bottom
//        lineTo(verticalBarWidth, yMax)
        lineTo(verticalBarWidth, canvasSizePx - horizontalBarWidth)
        lineTo(bottomBarX, canvasSizePx - horizontalBarWidth)
        lineTo(bottomBarX, xMax)
        lineTo(xMin, yMax)
    }

    val centerSquare = Path().apply {
        //bottom left
        moveTo(centerBarX + strokeWidth + squarePadding, (canvasSizePx / 2F) + (horizontalBarWidth / 2F))
        //bottom right
        lineTo(centerBarX + strokeWidth + squarePadding + squareSide, (canvasSizePx / 2F) + (horizontalBarWidth / 2F))
        //top right
        lineTo(centerBarX + strokeWidth + squarePadding + squareSide, (canvasSizePx / 2F) - (horizontalBarWidth / 2F))
        //top left
        lineTo(centerBarX + strokeWidth + squarePadding, (canvasSizePx / 2F) - (horizontalBarWidth / 2F))
        //bottom left
        lineTo(centerBarX + strokeWidth + squarePadding, (canvasSizePx / 2F) + (horizontalBarWidth / 2F))
    }
    val bottomSquare1 = Path().apply {
        //bottom left
        moveTo(bottomBarX + strokeWidth + squarePadding, yMax)
        //bottom right
        lineTo(bottomBarX + strokeWidth + squarePadding + squareSide, yMax)
        //top right
        lineTo(bottomBarX + strokeWidth + squarePadding + squareSide, yMax - squareSide)
        //top left
        lineTo(bottomBarX + strokeWidth + squarePadding, yMax - squareSide)
        //bottom left
        lineTo(bottomBarX + strokeWidth + squarePadding, yMax)
    }
}