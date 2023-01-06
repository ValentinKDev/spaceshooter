package com.mobilegame.spaceshooter.utils.extensions

import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import com.mobilegame.spaceshooter.logic.uiHandler.Device

fun Dp.fromDp(): Float = this.value * Device.density


infix fun DpOffset.inBoundsOf(sizeDp: DpSize): DpOffset {
    return when {
        this.x < 0.dp -> this xStartVerticalBounds sizeDp
        this.x > sizeDp.width -> this xEndVerticalBounds sizeDp
        this.y < 0.dp -> this yStartHorizontalBounds sizeDp
        this.y > sizeDp.height ->  this yEndHorizontalBounds sizeDp
        else -> this
    }
}

infix fun DpOffset.xStartVerticalBounds(sizeDp: DpSize): DpOffset =
    this.verticalBounds( x = 0.dp, verticalBounds = 0.dp..sizeDp.height)
infix fun DpOffset.xEndVerticalBounds(sizeDp: DpSize): DpOffset =
    this.verticalBounds( x = sizeDp.width, verticalBounds = 0.dp..sizeDp.height)
infix fun DpOffset.yStartHorizontalBounds(sizeDp: DpSize): DpOffset =
    this.horizontalBounds( y = 0.dp, horizontalBounds = 0.dp..sizeDp.width)
infix fun DpOffset.yEndHorizontalBounds(sizeDp: DpSize): DpOffset =
    this.horizontalBounds( y = sizeDp.height, horizontalBounds = 0.dp..sizeDp.width)

fun DpOffset.verticalBounds(x: Dp, verticalBounds: ClosedRange<Dp>): DpOffset = when {
    this.y < verticalBounds.start -> DpOffset(x, verticalBounds.start)
    this.y > verticalBounds.endInclusive -> DpOffset(x, verticalBounds.endInclusive)
    else -> DpOffset(x, this.y)
}

fun DpOffset.horizontalBounds(y: Dp, horizontalBounds: ClosedRange<Dp>): DpOffset = when {
    this.x < horizontalBounds.start -> DpOffset(horizontalBounds.start, y)
    this.x > horizontalBounds.endInclusive -> DpOffset(horizontalBounds.endInclusive, y)
    else -> DpOffset(this.x, y)
}
infix fun DpOffset.isInBoundsOf(sizeDp: DpSize): Boolean {
    var ret = true
    if (this.x !in 0.dp..sizeDp.width) ret = false
    if (this.y !in 0.dp..sizeDp.height) ret = false
    return ret
}