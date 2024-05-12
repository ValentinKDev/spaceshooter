package com.mobilegame.spaceshooter.utils.extensions

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import com.mobilegame.spaceshooter.data.device.Device

fun Offset.toPair() = Pair(this, this)
fun Offset.toDpOffset(): DpOffset = DpOffset(
    x = this.x.toDp(),
    y = this.y.toDp()
)

infix fun Offset.xMinus(f: Float): Offset = Offset(x = this.x - f, y = this.y)
infix fun Offset.xPlus(f: Float): Offset = Offset(x = this.x + f, y = this.y)
infix fun Offset.yMinus(f: Float): Offset = Offset(x = this.x, y = this.y - f)
infix fun Offset.yPlus(f: Float): Offset = Offset(x = this.x, y = this.y + f)

infix fun Offset.isInBoundsOf(size: Size): Boolean {
    var ret = true
    if (this.x !in 0F..size.width) ret = false
    if (this.y !in 0F..size.height) ret = false
    return ret
}

fun Offset.invertX(xRatio: Float): Offset = Offset((Device.metrics.size.width * (1F - xRatio)), this.y )
fun Offset.invert(): Offset = Offset(this.x * -1F, this.y * -1F)
fun Pair<Offset, Offset>.isNotZeroZero(): Boolean = !(this.first == Offset.Zero && this.first == Offset.Zero)
