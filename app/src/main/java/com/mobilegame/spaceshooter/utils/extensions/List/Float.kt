package com.mobilegame.spaceshooter.utils.extensions.List

import com.mobilegame.spaceshooter.logic.model.sensor.XYZ
import com.mobilegame.spaceshooter.logic.model.sensor.div
import com.mobilegame.spaceshooter.logic.model.sensor.minus

//inline fun List<Float>.getOrNull(index: Int): Float? = if (this.size notIn (0 until index)) null else 0F
infix fun Int.numberOf(f: Float): MutableList<Float> {
    val mutableList = mutableListOf<Float>()
    for (i in 0 until this) {
        mutableList += f
    }
    return mutableList
}

fun MutableList<Float>.smooth(smoothRatio: Int) {
    var value = this[0]
    for ( i in 1 until lastIndex) {
        val current = this[i]
        value = (current - value) / smoothRatio
        this[i] = value
    }
}
