package com.mobilegame.spaceshooter.utils.extensions

import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import com.mobilegame.spaceshooter.data.device.Device

fun Dp.dpToPixel(): Float = this.value * Device.metrics.density


infix fun Dp.div(f: Float): Dp = (this.value / f).dp
infix fun Dp.time(f: Float): Dp = (this.value * f).dp
infix fun Dp.add(f: Float): Dp = (this.value + f).dp
infix fun Dp.subtract(f: Float): Dp = (this.value - f).dp

infix fun Dp.div(dp: Dp): Dp = (this.value / dp.value).dp
infix fun Dp.time(dp: Dp): Dp = (this.value * dp.value).dp
infix fun Dp.add(dp: Dp): Dp = (this.value + dp.value).dp
infix fun Dp.subtract(dp: Dp): Dp = (this.value - dp.value).dp

//infix fun Dp.div(a: Int): Int = (this.value / a).a
//infix fun Dp.time(a: Int): Int = (this.value * a).a
//infix fun Dp.add(a: Int): Int = (this.value + a).a
//infix fun Dp.subtract(a: Int): Int = (this.value - a.value).a

fun Dp.toSquare(): DpSize = DpSize(this, this)
