package com.mobilegame.spaceshooter.utils.extensions

fun <T> MutableList<T>.containNot(element: T): Boolean = !this.contains(element)