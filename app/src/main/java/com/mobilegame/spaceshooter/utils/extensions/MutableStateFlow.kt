package com.mobilegame.spaceshooter.utils.extensions

import kotlinx.coroutines.flow.MutableStateFlow

fun <T> MutableStateFlow<List<T>>.addToValue(element: T) {
    this.value = this.value.add(element)
}

fun <T> MutableStateFlow<List<T>>.removeToValue(element: T) {
    this.value = this.value.remove(element)
}

fun <T> MutableStateFlow<List<T>>.removeToValue(index: Int) {
    this.value = this.value.remove(index)
}

fun <T> MutableStateFlow<List<T>>.findInValue(element: T): T? = this.value.find { it == element }

