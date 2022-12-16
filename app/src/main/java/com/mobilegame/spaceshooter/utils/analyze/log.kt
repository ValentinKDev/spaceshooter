package com.mobilegame.spaceshooter.utils.analyze

import android.util.Log
import com.google.gson.GsonBuilder

fun errorLog(firstpart: String = "", secondpart: String) = Log.e(firstpart, secondpart)
fun verbalLog(firstpart: String = "", secondpart: String) = Log.v(firstpart, secondpart)
fun infoLog(firstpart: String = "", secondpart: String) = Log.i(firstpart, secondpart)
fun warningLog(firstpart: String = "", secondpart: String) = Log.w(firstpart, secondpart)
fun debugLog(firstpart: String = "", secondpart: String) = Log.d(firstpart, secondpart)

fun <T>prettyPrint(tag: String, name: String, element: T, logType: Int = Log.INFO) {
    val prettyPrinter = GsonBuilder().setPrettyPrinting().create()
    val prettyJsonString = prettyPrinter.toJson(element)
    if (name.isNotEmpty())
        priorityLog(logType, tag,"$name ")
    priorityLog(logType, tag, prettyJsonString)
}

private fun priorityLog(priority: Int, tag: String, message: String) {
    when (priority) {
        Log.INFO -> Log.i(tag, message)
        Log.VERBOSE -> Log.v(tag, message)
        Log.ERROR -> Log.e(tag, message)
        Log.DEBUG -> Log.d(tag, message)
        Log.WARN -> Log.w(tag, message)
        else -> Log.i(tag, message)
    }
}
