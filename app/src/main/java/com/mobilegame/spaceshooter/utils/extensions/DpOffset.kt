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

//infix fun DpOffset.inBoundsOf(sizeDp: DpSize): DpOffset {
//    return when {
//        this.x < 0.dp -> this xStartVerticalBounds sizeDp
//        this.x > sizeDp.width -> this xEndVerticalBounds sizeDp
//        this.y < 0.dp -> this yStartHorizontalBounds sizeDp
//        this.y > sizeDp.height ->  this yEndHorizontalBounds sizeDp
//        else -> this
//    }
//}
//
//infix fun DpOffset.xStartVerticalBounds(sizeDp: DpSize): DpOffset =
//    this.verticalBounds( x = 0.dp, verticalBounds = 0.dp..sizeDp.height)
//infix fun DpOffset.xEndVerticalBounds(sizeDp: DpSize): DpOffset =
//    this.verticalBounds( x = sizeDp.width, verticalBounds = 0.dp..sizeDp.height)
//infix fun DpOffset.yStartHorizontalBounds(sizeDp: DpSize): DpOffset =
//    this.horizontalBounds( y = 0.dp, horizontalBounds = 0.dp..sizeDp.width)
//infix fun DpOffset.yEndHorizontalBounds(sizeDp: DpSize): DpOffset =
//    this.horizontalBounds( y = sizeDp.height, horizontalBounds = 0.dp..sizeDp.width)
//
//fun DpOffset.verticalBounds(x: Dp, verticalBounds: ClosedRange<Dp>): DpOffset = when {
//    this.y < verticalBounds.start -> DpOffset(x, verticalBounds.start)
//    this.y > verticalBounds.endInclusive -> DpOffset(x, verticalBounds.endInclusive)
//    else -> DpOffset(x, this.y)
//}
//
//fun DpOffset.horizontalBounds(y: Dp, horizontalBounds: ClosedRange<Dp>): DpOffset = when {
//    this.x < horizontalBounds.start -> DpOffset(horizontalBounds.start, y)
//    this.x > horizontalBounds.endInclusive -> DpOffset(horizontalBounds.endInclusive, y)
//    else -> DpOffset(this.x, y)
//}

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

