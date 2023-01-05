package com.mobilegame.spaceshooter.utils.extensions.MutableList

import kotlinx.coroutines.Job

fun MutableList<Job>.cancelAll() {
    this.forEach {
        it.cancel()
    }
}