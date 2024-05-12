package com.mobilegame.spaceshooter.utils.extensions

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import com.mobilegame.spaceshooter.data.device.Device
import com.mobilegame.spaceshooter.logic.uiHandler.SpaceShip.types.BoxCoordinates

fun DpOffset.toOffset(): Offset = Offset(
    x = this.x.dpToPixel(),
    y = this.y.dpToPixel()
)

infix fun DpOffset.or(secondOffset: DpOffset): DpOffset = if (this == DpOffset.Unspecified) secondOffset else this

infix fun DpOffset.inBoundsOf(sizeDp: DpSize): DpOffset {
    val offset = DpOffset(
        x = this.x boundTo 0.dp..sizeDp.width,
        y = this.y boundTo 0.dp..sizeDp.height
    )
    return offset
}

infix fun Dp.boundTo(bounds: ClosedRange<Dp>):Dp {
    return when {
        this in bounds -> this
        this < bounds.start -> bounds.start
        this > bounds.endInclusive -> bounds.endInclusive
        else -> Dp.Unspecified
    }
}

infix fun DpOffset.isInBoundsOf(sizeDp: DpSize): Boolean {
    var ret = true
    if (this.x !in 0.dp..sizeDp.width) ret = false
    if (this.y !in 0.dp..sizeDp.height) ret = false
    return ret
}
infix fun Dp.isInWidthOf(sizeDp: DpSize): Boolean {
    var ret = false
    if (this in 0.dp..sizeDp.width) ret = true
    return ret
}

infix fun Dp.isInHeightOf(sizeDp: DpSize): Boolean {
    var ret = false
    if (this in 0.dp..sizeDp.height) ret = true
    return ret
}


infix fun DpOffset.isNotInBoundsOf(sizeDp: DpSize): Boolean = !(this isInBoundsOf sizeDp)

infix fun DpOffset.touchTopScreen(sizeDp: DpSize): Boolean = (this.y <= 0.dp)

infix fun DpOffset.isInsideOf(box: BoxCoordinates): Boolean {
    var ret = true
    if (this.x !in (box.startWidthDp..box.endWidthDp)) ret = false
    if (this.y !in (box.topHeightDp..box.bottomHeightDp)) ret = false
    return ret
}

fun DpOffset.toPair() = Pair(this, this)
fun DpOffset.invertX(xRatio: Float): DpOffset = DpOffset((Device.metrics.sizeDp.width.value * (1F - xRatio)).dp, this.y )
fun DpOffset.invert(): DpOffset = DpOffset(this.x * -1F, this.y * -1F)
fun DpSize.invert(): DpSize = DpSize((this.width.value * -1F).dp, (this.height.value * -1F).dp)

infix fun DpOffset.xMinus(f: Dp): DpOffset = DpOffset(x = this.x - f, y = this.y)
infix fun DpOffset.xPlus(f: Dp): DpOffset = DpOffset(x = this.x + f, y = this.y)
infix fun DpOffset.yMinus(f: Dp): DpOffset = DpOffset(x = this.x, y = this.y - f)
infix fun DpOffset.yPlus(f: Dp): DpOffset = DpOffset(x = this.x, y = this.y + f)
