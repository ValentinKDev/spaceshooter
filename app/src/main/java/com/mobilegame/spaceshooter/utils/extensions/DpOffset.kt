package com.mobilegame.spaceshooter.utils.extensions

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp

fun DpOffset.toOffset(): Offset = Offset(
    x = this.x.DpToPixel(),
    y = this.y.DpToPixel()
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

infix fun DpOffset.isNotInBoundsOf(sizeDp: DpSize): Boolean = !(this isInBoundsOf sizeDp)

infix fun DpOffset.touchTopScreen(sizeDp: DpSize): Boolean = (this.y <= 0.dp)

