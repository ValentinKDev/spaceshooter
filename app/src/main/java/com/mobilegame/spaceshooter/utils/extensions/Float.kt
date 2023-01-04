package com.mobilegame.spaceshooter.utils.extensions

import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mobilegame.spaceshooter.logic.uiHandler.Device

const val densityRef = 3.0F
fun Float.toDp(): Dp = (this / Device.density).dp
fun Float.toSp(): TextUnit = (this / Device.density).sp
//todo : might better to use the ratio PixelNumberWidht/PixelNumberHeight for the accuracy to the reference
fun Float.toSpRef(): TextUnit = ((this / densityRef) * (densityRef / Device.density)).sp

fun Float.printTo(n: Int): String {
    val str = this.toString()
    var ret = ""
    var count = 0
    var pointIndex = 0

    for (i in 0 until str.length) {
        ret += str[i]
        if (str[i] == '.') break
        pointIndex += 1
    }
    for (i in pointIndex + 1 until str.length) {
        ret += str[i]
        count += 1
        if (count >= n) break
    }
    for (i in count until n) {
        ret += '0'
    }
    return ret
}