package com.mobilegame.spaceshooter.utils.extensions

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.unit.DpSize
import com.mobilegame.spaceshooter.logic.model.screen.inGameScreens.duelGameScreen.Shoot
import com.mobilegame.spaceshooter.utils.analyze.vLog

fun <T> List<T>.copy(): List<T> {
    val mutableList = mutableListOf<T>()
    for (i in 0 until this.size) {
        mutableList.add(this[i])
    }
    return mutableList.toList()
}

fun List<Shoot>.cloneIfInBounds(boundaries: DpSize): List<Shoot> {
    val mutableList: MutableList<Shoot> = mutableListOf()
    for (shoot in this) {
        if (shoot.offsetDp isInBoundsOf boundaries)
            mutableList.add(
                Shoot(
                    type = shoot.type,
                    motion = shoot.motion,
                    from = shoot.from,
                    vector = shoot.vector,
                    offsetDp = shoot.offsetDp
                )
            )
    }
    return mutableList.toList()
}
fun List<Shoot>.clone(): List<Shoot> {
    val mutableList: MutableList<Shoot> = mutableListOf()
    for (shoot in this) {
        mutableList.add(
            Shoot(
                type = shoot.type,
                motion = shoot.motion,
                from = shoot.from,
                vector = shoot.vector,
                offsetDp = shoot.offsetDp
            )
        )
    }
    return mutableList.toList()
}
//fun List<Shoot>.add(element: Shoot): List<Shoot> {
//    val mutableList: MutableList<Shoot> = this.clone().toMutableList()
fun <T> List<T>.add(element: T): List<T> {
    val mutableList: MutableList<T> = this.toMutableList()
    mutableList.add(element)
    return mutableList
}
fun <T> List<T>.remove(element: T): List<T> {
//    val mutableList: MutableList<T> = this.clone().toMutableList()
    val mutableList: MutableList<T> = this.toMutableList()
    mutableList.remove(element)
    return mutableList
}
fun <T> List<T>.remove(index: Int): List<T> {
//    val mutableList: MutableList<T> = this.clone().toMutableList()
    val mutableList: MutableList<T> = this.toMutableList()
    mutableList.remove(index)
    return mutableList
}

fun <T> List<T>.reverseElements(): List<T> {
    val mutableList = mutableListOf<T>()
    for (i in this.lastIndex downTo  0) {
        mutableList.add(this[i])
    }
    return mutableList.toList()
}

fun <T> List<T>.get(element: T): T? = this.find { it == element }
//fun MutableList<Offset>.reverseElements(): List<Offset> {
//    val mutableList = mutableListOf<Offset>()
//    for (i in this.lastIndex downTo 0) {
//        mutableList.add(this.get(index = i))
//    }
//    vLog("reverse Elements", "this.size ${this.size}, ret size ${mutableList.size}")
//
//    return mutableList.toList()
//}
