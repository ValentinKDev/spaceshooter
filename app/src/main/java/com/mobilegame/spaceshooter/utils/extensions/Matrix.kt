package com.mobilegame.spaceshooter.utils.extensions

fun <T> Array<Array<T>>.oneLineUpper(line: Int, column: Int) = this[line - 1][column]
fun <T> Array<Array<T>>.oneLineDowner(line: Int, column: Int) = this[line + 1][column]
fun <T> Array<Array<T>>.oneColumnAfter(line: Int, column: Int) = this[line][column + 1]
fun <T> Array<Array<T>>.oneColumnBefore(line: Int, column: Int) = this[line][column - 1]
