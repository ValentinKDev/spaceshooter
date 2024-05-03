package com.mobilegame.spaceshooter.logic.repository.gameStats

import android.util.Log
import java.text.SimpleDateFormat
import java.util.Date

class MyDate(val dateStr: String) {
    private val TAG = "MyDate"
    val date: Date = dateStr.toDateOrDefault()
    private fun String.toDateOrDefault(): Date = try {
        SimpleDateFormat(PATTERN).parse(this)!!
    } catch (e: Exception) {
        Log.e(TAG, "toDateOrDefault: $e")
        SimpleDateFormat(PATTERN).parse( DEFAULT )!!
    }

    fun isOlderThan(dateToCompare: Date): Boolean {
        val ret: Boolean = when ( date.compareTo(dateToCompare) ) {
            -1 -> true
            else -> false
        }
        return ret
    }

    fun isOlderThan(dateToCompareStr: String): Boolean {
        val dateToCompare: Date = dateToCompareStr.toDateOrDefault()
        return isOlderThan(dateToCompare)
    }
    fun parse(dateStr: String): Date? = MY_FORMAT.parse(dateStr)
    companion object {
        private const val PATTERN = "yyyy.MM.dd/HH:mm:ss"
        const val DEFAULT = "0000.00.00|00:00:00"
        val MY_FORMAT = SimpleDateFormat(PATTERN)
        fun currentStr(): String = MY_FORMAT.format(Date())
    }
}