package com.mobilegame.spaceshooter.utils.extensions

infix fun Int.notIn(range: IntRange) = !(range.first <= this && this <= range.last)

//infix fun Int.to(i: Int): IntRange = (this until i)