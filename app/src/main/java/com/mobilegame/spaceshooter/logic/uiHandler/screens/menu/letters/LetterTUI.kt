package com.mobilegame.spaceshooter.logic.uiHandler.screens.menu.letters

import androidx.compose.ui.graphics.Path
import androidx.compose.ui.unit.DpSize

class LetterTUI(val canvasSizeDp: DpSize) {
    internal val letter = Letter(canvasSizeDp)

    private val firstHorizontalCut = 0.35F * letter.canvasSizePx
    private val verticalCut = 0.52F * letter.canvasSizePx
    val topPart = Path().apply {
        //left part
        moveTo(x = letter.offsetTopLeft.x, y = letter.offsetTopLeft.y)
        lineTo(x = verticalCut - letter.spaceBetweenElements - (2F * letter.strokeWidth), y = letter.offsetTopRight.y)
        lineTo(x = letter.offsetCenter.x - (letter.standardBarWidth / 2F) - letter.spaceBetweenElements - ( 2F * letter.strokeWidth), y = letter.standardBarWidth)
        lineTo(x = letter.offsetTopLeft.x, y = letter.standardBarWidth)
        close()

        //right part
        moveTo(x = verticalCut, y = letter.offsetTopRight.y)
        lineTo(x = letter.offsetTopRight.x, y = letter.offsetTopRight.y)
        lineTo(x = letter.offsetTopRight.x, y = letter.standardBarWidth)
        lineTo(x = letter.offsetCenter.x + (letter.standardBarWidth / 2F), y = letter.standardBarWidth)
        lineTo(x = letter.offsetCenter.x + (letter.standardBarWidth / 2F), y = firstHorizontalCut)
        lineTo(x = letter.offsetCenter.x - (letter.standardBarWidth / 2F), y = firstHorizontalCut)
        lineTo(x = letter.offsetCenter.x - (letter.standardBarWidth / 2F), y = letter.standardBarWidth)
        close()
    }
    val middlePart = Path().apply {
        moveTo(x = letter.offsetCenter.x - (letter.standardBarWidth / 2F), y =  firstHorizontalCut + letter.spaceBetweenElements + (2 * letter.strokeWidth))
        lineTo(x = letter.offsetCenter.x + (letter.standardBarWidth / 2F), y =  firstHorizontalCut + letter.spaceBetweenElements + (2 * letter.strokeWidth))
        lineTo(x = letter.offsetCenter.x + (letter.standardBarWidth / 2F), y =  letter.offsetBottomRight.y)
        lineTo(x = letter.offsetCenter.x - (letter.standardBarWidth / 2F), y =  letter.offsetBottomRight.y)
//        lineTo(x = letter.offsetCenter.x + (letter.standardBarWidth / 2F), y =  letter.offsetBottomRight.y - letter.standardBarWidth - letter.spaceBetweenElements - (2 * strokeWidth))
//        lineTo(x = letter.offsetCenter.x - (letter.standardBarWidth / 2F), y =  letter.offsetBottomRight.y - letter.standardBarWidth - letter.spaceBetweenElements - (2 * strokeWidth))
        close()
    }
    val bottomPart = Path().apply {
        moveTo(x = letter.offsetCenter.x - (letter.standardBarWidth / 2F), y = letter.offsetBottomLeft.y - letter.standardBarWidth)
        lineTo(x = letter.offsetCenter.x + (letter.standardBarWidth / 2F), y = letter.offsetBottomLeft.y - letter.standardBarWidth)
        lineTo(x = letter.offsetCenter.x + (letter.standardBarWidth / 2F), y = letter.offsetBottomLeft.y)
        lineTo(x = letter.offsetCenter.x - (letter.standardBarWidth / 2F), y = letter.offsetBottomLeft.y)
        close()
    }
}