package com.mobilegame.spaceshooter.utils.extensions

fun <K, V> Map<K, V>.cloneAdd(key: K, value: V): Map<K, V> {
    val newMap = this.toMutableMap()
    newMap[key] = value
    return newMap
}

fun <K, V> Map<K, V>.cloneRemove(key: K): Map<K, V> {
    val newMap = this.toMutableMap()
    newMap.remove(key)
    return newMap
}
