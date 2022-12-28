package com.mobilegame.spaceshooter.utils.extensions

import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mobilegame.spaceshooter.logic.model.screen.uiHandler.Device

const val densityRef = 3.0F
fun Float.toDp(): Dp = (this / Device.density).dp
fun Float.toSp(): TextUnit = (this / Device.density).sp
//todo : might better to use the ratio PixelNumberWidht/PixelNumberHeight for the accuracy to the reference
fun Float.toSpRef(): TextUnit = ((this / densityRef) * (densityRef / Device.density)).sp

fun Float.printTo(n: Int): String {
    if (this < 10F) {
        var str = this.toString()
        var startCounting = false
        var count = -3
        var ret = ""

        for (i in 0 until str.length) {
            ret += str[i]
            count += 1
            if (count >= n) break
        }
        return ret
    } else return this.toString()
}