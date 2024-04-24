package com.mobilegame.spaceshooter.logic.uiHandler.screens.menu.startMenu.letters

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.unit.DpSize
import com.mobilegame.spaceshooter.presentation.ui.screens.menuScreen.letters.getListEllipseOffset
import com.mobilegame.spaceshooter.utils.extensions.degreeToRadianRange
import com.mobilegame.spaceshooter.utils.extensions.reverseElements
import com.mobilegame.spaceshooter.utils.extensions.sweepAngle

class LetterBUI(val canvasSizeDp: DpSize) {
    val letter = Letter(canvasSizeDp)

    private val margeLeftTriangle = letter.canvasSizePx * 0.1F
    private val leftTrianglePoint = Offset(
        x = letter.offsetCenter.x * 0.9F + margeLeftTriangle,
        y = letter.offsetCenter.y * 0.8F,
    )
    private val topTrianglePoint = Offset(
        x = leftTrianglePoint.x + letter.spaceBetweenElements + (4 * letter.strokeWidth),
        y = leftTrianglePoint.y,
    )
    private val topEllipseRadiusAlpha = 0.19F
    private val topEllipseXAlpha = 1F
    private val topEllipseYBeta = 1F
    private val listTopEllipseOffset = getListEllipseOffset(
        center = Offset(
            x = topTrianglePoint.x,
            y = (topTrianglePoint.y / 2F),
        ),
        radius = letter.canvasSizePx * topEllipseRadiusAlpha,
        alphaX = topEllipseXAlpha,
        betaY = topEllipseYBeta,
        angleRange = (270F sweepAngle 180F).degreeToRadianRange(),
    ).reverseElements()
    private val bottomEllipseRadiusAlpha = 0.35F
    private val bottomEllipseXAlpha = 1F
    private val bottomEllipseYBeta = 1F
    private val listBottomEllipseOffset = getListEllipseOffset(
        center = Offset(
            x = topTrianglePoint.x,
            y = ((letter.canvasSizePx - (topTrianglePoint.y / 2F)) / 2F) + topTrianglePoint.y,
        ),
        radius = letter.canvasSizePx * bottomEllipseRadiusAlpha,
        alphaX = bottomEllipseXAlpha,
        betaY = bottomEllipseYBeta,
        angleRange = (325F sweepAngle 130F).degreeToRadianRange(),
    )
    val leftTrianglePart = Path().apply {
        moveTo(letter.offsetTopLeft.x, letter.offsetTopLeft.y)
        lineTo(letter.offsetBottomLeft.x, letter.offsetBottomLeft.y)
        lineTo(letter.offsetBottomLeft.x + margeLeftTriangle, letter.offsetBottomLeft.y)
        lineTo(leftTrianglePoint.x, leftTrianglePoint.y)
        lineTo(letter.offsetTopLeft.x + margeLeftTriangle, letter.offsetTopLeft.y)
        close()

        moveTo(margeLeftTriangle + letter.offsetTopLeft.x + letter.spaceBetweenElements + (4 * letter.strokeWidth), letter.offsetTopLeft.y)
        for (offset in listTopEllipseOffset) { lineTo(offset.x, offset.y) }
        close()

        moveTo( listBottomEllipseOffset.first().x, listBottomEllipseOffset.first().y)
        for (offset in listBottomEllipseOffset) { lineTo(offset.x, offset.y) }
        lineTo(  margeLeftTriangle + letter.offsetBottomLeft.x + letter.spaceBetweenElements + ( 4F * letter.strokeWidth), letter.offsetBottomLeft.y)
        close()
    }
}