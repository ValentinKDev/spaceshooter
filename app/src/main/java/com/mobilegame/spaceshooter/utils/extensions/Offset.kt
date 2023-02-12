package com.mobilegame.spaceshooter.utils.extensions

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.unit.DpOffset

fun Offset.toDpOffset(): DpOffset = DpOffset(
    x = this.x.toDp(),
    y = this.y.toDp()
)

infix fun Offset.xMinus(f: Float): Offset = Offset(x = this.x - f, y = this.y)
infix fun Offset.xPlus(f: Float): Offset = Offset(x = this.x + f, y = this.y)
infix fun Offset.yMinus(f: Float): Offset = Offset(x = this.x, y = this.y - f)
infix fun Offset.yPlus(f: Float): Offset = Offset(x = this.x, y = this.y + f)
