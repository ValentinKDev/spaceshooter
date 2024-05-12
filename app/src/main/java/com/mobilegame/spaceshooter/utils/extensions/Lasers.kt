package com.mobilegame.spaceshooter.utils.extensions

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp

fun getXForY(start: DpOffset, end: DpOffset, y: Dp): Dp {
    val v = (end.y.value - start.y.value) / (end.x.value - start.x.value)
    val x = (y.value - start.y.value + v * start.x.value) / v
    return x.dp
}
fun getYForX(start: DpOffset, end: DpOffset, x: Dp): Dp {
    val v = (end.y.value - start.y.value) / (end.x.value - start.x.value)
    val b = start.y.value - v * start.x.value
    val y = v * x.value + b
    return y.dp
}
fun getXForY(start: Offset, end: Offset, y: Float): Float {
    val m = (end.y - start.y) / (end.x - start.x)
    val x = (y - start.y + m * start.x) / m
    return x
}
fun getYForX(start: Offset, end: Offset, x: Float): Float {
    val m = (end.y - start.y) / (end.x - start.x)
    val b = start.y - m * start.x
    val y = m * x + b
    return y
}