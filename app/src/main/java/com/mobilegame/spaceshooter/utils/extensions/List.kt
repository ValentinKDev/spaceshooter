package com.mobilegame.spaceshooter.utils.extensions

import androidx.compose.ui.node.modifierElementOf
import androidx.compose.ui.unit.DpSize
import com.mobilegame.spaceshooter.logic.model.screen.inGameScreens.duelGameScreen.Shoot
import com.mobilegame.spaceshooter.utils.analyze.eLog

//fun <T> List<T>.plus(element: T): List<T> {
//    val mutableList: MutableList<T> = this.copy().toMutableList()
//    mutableList.add(element)
//    return mutableList.toList()
//}

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
fun List<Shoot>.plus(element: Shoot): List<Shoot> {
    val mutableList: MutableList<Shoot> = this.clone().toMutableList()
    mutableList.add(element)
    return mutableList.toList()
}
fun List<Shoot>.remove(index: Int): List<Shoot> {
    val mutableList: MutableList<Shoot> = this.clone().toMutableList()
    mutableList.remove(index)
    return mutableList.toList()
}
