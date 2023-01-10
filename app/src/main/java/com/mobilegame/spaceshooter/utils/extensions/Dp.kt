package com.mobilegame.spaceshooter.utils.extensions

import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import com.mobilegame.spaceshooter.logic.uiHandler.Device

fun Dp.DpToPixel(): Float = this.value * Device.density


infix fun Dp.div(f: Float): Dp = (this.value / f).dp
infix fun Dp.time(f: Float): Dp = (this.value * f).dp
infix fun Dp.add(f: Float): Dp = (this.value + f).dp
infix fun Dp.subtract(f: Float): Dp = (this.value - f).dp

infix fun Dp.div(dp: Dp): Dp = (this.value / dp.value).dp
infix fun Dp.time(dp: Dp): Dp = (this.value * dp.value).dp
infix fun Dp.add(dp: Dp): Dp = (this.value + dp.value).dp
infix fun Dp.subtract(dp: Dp): Dp = (this.value - dp.value).dp

fun Dp.toSquare(): DpSize = DpSize(this, this)
