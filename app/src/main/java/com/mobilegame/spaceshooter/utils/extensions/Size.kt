package com.mobilegame.spaceshooter.utils.extensions

import androidx.compose.ui.geometry.Size
import androidx.compose.ui.unit.DpSize

fun Size.toDpSize(): DpSize = DpSize(
    width = this.width.toDp(),
    height = this.height.toDp()
)

infix fun Size.timeS(f: Float) = Size(width = this.width * f, height = this.height * f)