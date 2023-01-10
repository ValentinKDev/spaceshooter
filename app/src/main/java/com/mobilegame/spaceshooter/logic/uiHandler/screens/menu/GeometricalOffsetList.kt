package com.mobilegame.spaceshooter.presentation.ui.screens.menu.letters

import androidx.compose.ui.geometry.Offset
import kotlin.math.cos
import kotlin.math.sin

fun getListEllipseOffset(
    center: Offset,
    radius: Float,
    alphaX: Float,
    betaY: Float,
    angleRange: ClosedFloatingPointRange<Float>
): List<Offset> {
    var angle = angleRange.start
    val list = mutableListOf<Offset>()
    while (angle <= angleRange.endInclusive) {
        list += getEllipseOffsetAt(center, radius, angle, alphaX, betaY)
        angle += 0.01F
    }
    return list
}

fun getEllipseOffsetAt(
    center: Offset,
    radius: Float,
    angle: Float,
    alphaX: Float,
    betaY: Float,
): Offset {
    val x = radius * cos(angle) * alphaX
    val y = radius * sin(angle) * betaY
    return (Offset(center.x + x, center.y + y))
}

fun getListCircleOffset(center: Offset, radius: Float, angleRange: ClosedFloatingPointRange<Float>): List<Offset> {
    return getListEllipseOffset(
        center = center,
        radius = radius,
        alphaX = 1F,
        betaY = 1F,
        angleRange = angleRange,
    )
}
