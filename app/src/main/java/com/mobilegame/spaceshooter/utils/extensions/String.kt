package com.mobilegame.spaceshooter.utils.extensions

fun String.addNavArg(arg: String): String = this.plus("/$arg")

fun String.toID(): String = this + "_ID"

fun String.containsNot(str: String): Boolean = !this.contains(str)
