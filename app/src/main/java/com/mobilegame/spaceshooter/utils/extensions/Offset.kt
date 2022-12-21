package com.mobilegame.spaceshooter.utils.extensions

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.unit.DpOffset

fun Offset.toDpOffset(densityF: Float): DpOffset = DpOffset(
    x = this.x.toDp(densityF),
    y = this.y.toDp(densityF)
)