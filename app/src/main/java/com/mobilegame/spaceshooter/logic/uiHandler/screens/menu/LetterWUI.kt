package com.mobilegame.spaceshooter.logic.uiHandler.screens.menu

import androidx.compose.ui.graphics.Path
import androidx.compose.ui.unit.DpSize
import com.mobilegame.spaceshooter.utils.extensions.DpToPixel

class LetterWUI(val canvasSizeDp: DpSize) {
    private val canvasSizePx: Float = canvasSizeDp.width.DpToPixel()
    val strokeWidth = canvasSizePx / 80F
    private val xMin = strokeWidth
    private val xMax = canvasSizePx - strokeWidth
    private val yMin = xMin
    private val yMax = xMax
    private val center = canvasSizePx / 2F
    val barDeltaRatio = 1F/9F

    val barWidth = 0.2F * canvasSizePx

    val pathLeft = Path().apply {
        moveTo(xMin, yMin)
        lineTo(xMin + barWidth, yMin)
        lineTo(xMin + barWidth + ((canvasSizePx * barDeltaRatio)), yMax)
        lineTo(canvasSizePx * barDeltaRatio, yMax)
        lineTo(xMin, yMin)
    }
    val pathRight = Path().apply {
        moveTo(xMax, yMin)
        lineTo(xMax - barWidth, yMin)
        lineTo(xMax - barWidth - (canvasSizePx * barDeltaRatio), yMax)
        lineTo(canvasSizePx * (1F - barDeltaRatio), yMax)
        lineTo(xMax, yMin)
    }

    val yUp = canvasSizePx * 0.30F
    val yDown = canvasSizePx * 0.75F
    val xDelta = canvasSizePx * 0.13F
    val pathCenter = Path().apply {
        moveTo(xMax - barWidth - (canvasSizePx * barDeltaRatio), yUp)
        lineTo(canvasSizePx * 0.63F, yDown)
        lineTo(center, yDown * 0.9F)
        lineTo(canvasSizePx * 0.37F, yDown)
        lineTo(xMin + barWidth + (canvasSizePx * barDeltaRatio), yUp)
        lineTo(center, yUp * 0.65F)
        lineTo(xMax - barWidth - (canvasSizePx * barDeltaRatio), yUp)
    }
}