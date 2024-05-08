package com.mobilegame.spaceshooter.logic.uiHandler.screens.menu.startMenu.letters

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.unit.DpSize
import com.mobilegame.spaceshooter.utils.extensions.dpToPixel

class Letter(val canvasSizeDp: DpSize) {
    val canvasSizePx: Float = canvasSizeDp.width.dpToPixel()
    val strokeWidth: Float = canvasSizePx / 80F
    val spaceBetweenElements: Float = canvasSizePx * 0.03F
    val offsetTopLeft: Offset = Offset(x = strokeWidth, y = strokeWidth)
    val offsetTopRight: Offset = Offset(x = canvasSizePx - strokeWidth, y = strokeWidth)
    val offsetBottomLeft: Offset = Offset(x = strokeWidth, y = canvasSizePx - strokeWidth)
    val offsetBottomRight: Offset = Offset(x = canvasSizePx - strokeWidth, y = canvasSizePx - strokeWidth)
    val offsetCenter: Offset = Offset(x = canvasSizePx / 2F, y = canvasSizePx / 2F)
    val standardBarWidth: Float = canvasSizePx * 0.25F
}