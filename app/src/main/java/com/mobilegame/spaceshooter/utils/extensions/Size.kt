package com.mobilegame.spaceshooter.utils.extensions

import androidx.compose.ui.geometry.Size
import androidx.compose.ui.unit.DpSize

infix fun Size.toDpSize(densityF: Float): DpSize = DpSize(
    width = this.width.toDp(densityF),
    height = this.height.toDp(densityF)
)