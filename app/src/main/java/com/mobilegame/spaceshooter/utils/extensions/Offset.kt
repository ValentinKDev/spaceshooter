package com.mobilegame.spaceshooter.utils.extensions

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.unit.DpOffset

fun Offset.toDpOffset(): DpOffset = DpOffset(
    x = this.x.toDp(),
    y = this.y.toDp()
)
