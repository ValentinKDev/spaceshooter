package com.mobilegame.spaceshooter.utils.extensions

import androidx.compose.ui.graphics.Color
import com.mobilegame.spaceshooter.utils.analyze.eLog


fun Color.alpha(ratio: Float): Color {
    return if (ratio in 0F..1F) {
        this.copy(alpha = ratio)
    } else {
        eLog("ERROR Color.alpha", "ratio is not in 0F..1F for Color ${this.toHexCode()}")
        this
    }
}

private fun Color.toHexCode(): String {
    val alpha = this.alpha * 255
    val red = this.red * 255
    val green = this.green * 255
    val blue = this.blue * 255
    return String.format("#%02x#%02x%02x%02x", alpha.toInt() ,red.toInt(), green.toInt(), blue.toInt())
}
