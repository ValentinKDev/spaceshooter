package com.mobilegame.spaceshooter.logic.uiHandler.screens.menu

import androidx.compose.ui.graphics.Path
import androidx.compose.ui.unit.Dp
import com.mobilegame.spaceshooter.utils.extensions.DpToPixel

class LetterAUI(val canvasSizeDp: Dp, val padding: Dp) {
    private val canvasSizePx: Float = canvasSizeDp.DpToPixel()
    val strokeWidth = canvasSizePx / 80F

    private val bottomBarWidth = canvasSizePx * 0.25F
    private val rectHeightPx = canvasSizePx * 0.20F
    private val rectWidthPx = canvasSizePx * 0.35F
    private val rectTopY = canvasSizePx * 0.46F
    fun getRectCentralPath() = Path().apply {
        //top right
        moveTo((canvasSizePx / 2F) + (rectWidthPx / 2F), rectTopY)
        //bottom right
        lineTo((canvasSizePx / 2F) + (rectWidthPx / 2F), rectTopY+ rectHeightPx)
        //bottom left
        lineTo((canvasSizePx / 2F) - (rectWidthPx / 2F), rectTopY+ rectHeightPx)
        //top left
        lineTo((canvasSizePx / 2F) - (rectWidthPx / 2F), rectTopY)
        //top right
        lineTo((canvasSizePx / 2F) + (rectWidthPx / 2F), rectTopY)
    }

    private val bottomTopPathY = canvasSizePx * 0.53F
    fun getTopPath() = Path().apply {
        //top left
        moveTo(strokeWidth, strokeWidth)
        //top right
        lineTo(canvasSizePx - strokeWidth, strokeWidth)
        //bottom right
        lineTo(canvasSizePx - strokeWidth, bottomTopPathY)
        lineTo(canvasSizePx - strokeWidth - bottomBarWidth, bottomTopPathY)
        //between legs
        lineTo(canvasSizePx - strokeWidth - bottomBarWidth, rectHeightPx)
        lineTo(bottomBarWidth, rectHeightPx)
        //bottom left
        lineTo(bottomBarWidth, bottomTopPathY)
        lineTo(strokeWidth, bottomTopPathY)
        //top left
        lineTo(strokeWidth, strokeWidth)
    }

    private val paddingBetweenTopAndLeg = canvasSizePx * 0.06F
    fun getRectLeftPath() = Path().apply {
        //top left
        moveTo(strokeWidth, bottomTopPathY + paddingBetweenTopAndLeg)
        //top right
        lineTo(strokeWidth + bottomBarWidth - strokeWidth, bottomTopPathY + paddingBetweenTopAndLeg)
        //bottom right
        lineTo(strokeWidth + bottomBarWidth - strokeWidth, canvasSizePx - strokeWidth)
        //bottom left
        lineTo(strokeWidth, canvasSizePx - strokeWidth)
        //top left
        lineTo(strokeWidth, bottomTopPathY + paddingBetweenTopAndLeg)
    }
    fun getRectRightPath() = Path().apply {
        //top right
        moveTo(canvasSizePx - strokeWidth, bottomTopPathY + paddingBetweenTopAndLeg)
        //top left
        lineTo(canvasSizePx - strokeWidth - bottomBarWidth, bottomTopPathY + paddingBetweenTopAndLeg)
        //bottom left
        lineTo(canvasSizePx - strokeWidth - bottomBarWidth, canvasSizePx - strokeWidth)
        //bottom right
        lineTo(canvasSizePx - strokeWidth , canvasSizePx - strokeWidth)
        //top right
        lineTo(canvasSizePx - strokeWidth, bottomTopPathY + paddingBetweenTopAndLeg)
    }
}