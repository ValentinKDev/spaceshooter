package com.mobilegame.spaceshooter.logic.uiHandler.screens.menu.startMenu.letters

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.unit.DpSize
import com.mobilegame.spaceshooter.presentation.ui.screens.menuScreen.letters.getListEllipseOffset
import com.mobilegame.spaceshooter.utils.extensions.degreeToRadianRange
import com.mobilegame.spaceshooter.utils.extensions.reverseElements
import com.mobilegame.spaceshooter.utils.extensions.sweepAngle

class LetterOUI (val canvasSizeDp: DpSize) {
    val letter = Letter(canvasSizeDp)

    private val ellipseOuterRadiusAlpha = 0.48F
    private val ellipseOuterXAlpha = 1F
    private val ellipseOuterYBeta = 1F
    private val listTopLeftOuterEllipseOffset = getListEllipseOffset(
        center = Offset(
            x = letter.offsetCenter.x,
            y = letter.offsetCenter.y,
        ),
        radius = letter.canvasSizePx * ellipseOuterRadiusAlpha,
        alphaX = ellipseOuterXAlpha,
        betaY = ellipseOuterYBeta,
        angleRange = (96F sweepAngle 104F).degreeToRadianRange(),
    )
    private val listTopRightOuterEllipseOffset = getListEllipseOffset(
        center = Offset(
            x = letter.offsetCenter.x,
            y = letter.offsetCenter.y,
        ),
        radius = letter.canvasSizePx * ellipseOuterRadiusAlpha,
        alphaX = ellipseOuterXAlpha,
        betaY = ellipseOuterYBeta,
        angleRange = (336F sweepAngle 106F).degreeToRadianRange(),
    )
    private val listBottomOuterEllipseOffset = getListEllipseOffset(
        center = Offset(
            x = letter.offsetCenter.x,
            y = letter.offsetCenter.y,
        ),
        radius = letter.canvasSizePx * ellipseOuterRadiusAlpha,
        alphaX = ellipseOuterXAlpha,
        betaY = ellipseOuterYBeta,
        angleRange = (216F sweepAngle 106F).degreeToRadianRange(),
    )

    private val ellipseInnerRadiusAlpha = 0.18F
    private val ellipseInnerXAlpha = 1F
    private val ellipseInnerYBeta = 1F
    private val listTopLeftInnerEllipseOffset = getListEllipseOffset(
        center = Offset(
            x = letter.offsetCenter.x,
            y = letter.offsetCenter.y,
        ),
        radius = letter.canvasSizePx * ellipseInnerRadiusAlpha,
        alphaX = ellipseInnerXAlpha,
        betaY = ellipseInnerYBeta,
//        angleRange = (96F sweepAngle 104F).degreeToRadianRange(),
        angleRange = (120F sweepAngle 60F).degreeToRadianRange(),
    ).reverseElements()
    private val listTopRightInnerEllipseOffset = getListEllipseOffset(
        center = Offset(
            x = letter.offsetCenter.x,
            y = letter.offsetCenter.y,
        ),
        radius = letter.canvasSizePx * ellipseInnerRadiusAlpha,
        alphaX = ellipseInnerXAlpha,
        betaY = ellipseInnerYBeta,
//        angleRange = (336F sweepAngle 106F).degreeToRadianRange(),
        angleRange = (0F sweepAngle 60F).degreeToRadianRange(),
    ).reverseElements()
    private val listBottomInnerEllipseOffset = getListEllipseOffset(
        center = Offset(
            x = letter.offsetCenter.x,
            y = letter.offsetCenter.y,
        ),
        radius = letter.canvasSizePx * ellipseInnerRadiusAlpha,
        alphaX = ellipseInnerXAlpha,
        betaY = ellipseInnerYBeta,
//        angleRange = (216F sweepAngle 106F).degreeToRadianRange(),
        angleRange = (240F sweepAngle 60F).degreeToRadianRange(),
    ).reverseElements()

    val rightEllipse = Path().apply {
//        arcTo(
//            rect = Rect(topLeft = letter.offsetTopLeft, bottomRight = letter.offsetBottomRight),
//            startAngleDegrees = 277F,
//            sweepAngleDegrees = 114F,
//            forceMoveTo = false
//        )
        moveTo( listTopRightOuterEllipseOffset.first().x, listTopRightOuterEllipseOffset.first().y, )
        for (offset in listTopRightOuterEllipseOffset) { lineTo(offset.x, offset.y) }
        for (offset in listTopRightInnerEllipseOffset) { lineTo(offset.x, offset.y) }
        close()
    }
    val leftEllipse = Path().apply {
//        arcTo(
//            rect = Rect(topLeft = letter.offsetTopLeft, bottomRight = letter.offsetBottomRight),
//            startAngleDegrees = 157F,
//            sweepAngleDegrees = 114F,
//            forceMoveTo = false
//        )
        moveTo( listTopLeftOuterEllipseOffset.first().x, listTopLeftOuterEllipseOffset.first().y, )
        for (offset in listTopLeftOuterEllipseOffset) { lineTo(offset.x, offset.y) }
        for (offset in listTopLeftInnerEllipseOffset) { lineTo(offset.x, offset.y) }
        close()
    }
    val bottomEllipse = Path().apply {
//        arcTo(
//            rect = Rect(topLeft = letter.offsetTopLeft, bottomRight = letter.offsetBottomRight),
//            startAngleDegrees = 37F,
//            sweepAngleDegrees = 114F,
//            forceMoveTo = false
//        )
        moveTo( listBottomOuterEllipseOffset.first().x, listBottomOuterEllipseOffset.first().y, )
        for (offset in listBottomOuterEllipseOffset) { lineTo(offset.x, offset.y) }
        for (offset in listBottomInnerEllipseOffset) { lineTo(offset.x, offset.y) }
        close()
    }
}