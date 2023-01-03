package com.mobilegame.spaceshooter.utils.extensions

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.unit.DpOffset

fun DpOffset.toOffset(): Offset = Offset(
    x = this.x.fromDp(),
    y = this.y.fromDp()
)

infix fun DpOffset.or(secondOffset: DpOffset): DpOffset = if (this == DpOffset.Unspecified) secondOffset else this
