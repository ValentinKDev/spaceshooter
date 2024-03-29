package com.mobilegame.spaceshooter.logic.uiHandler.screens.menu

import android.util.Log
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import com.mobilegame.spaceshooter.data.device.Device
import com.mobilegame.spaceshooter.utils.extensions.div
import com.mobilegame.spaceshooter.utils.extensions.subtract
import com.mobilegame.spaceshooter.utils.extensions.time
import com.mobilegame.spaceshooter.utils.extensions.toSquare

class MenuScreenUI() {
    private val TAG = "MenuScreenUI"
//    val letterSizeDp = 75.dp.toSquare()
    val letterSizeDp = (Device.metrics.sizeDp.width * 0.075F).toSquare()
    val letterPaddingDp = letterSizeDp.width * 0.15F
    val letterSpacerSizeDp = DpSize(letterPaddingDp, letterSizeDp.height)
    val verticalPadding = (Device.metrics.sizeDp.height subtract letterSizeDp.height) div 2F

    fun getContentSize(word: String) = DpSize((letterSizeDp.width time word.length.toFloat()) + (letterPaddingDp time (word.length - 1).toFloat()), letterSizeDp.height)
    fun getStartPadding(word: String) = ((Device.metrics.sizeDp.width subtract (letterSizeDp.width time word.length.toFloat())) subtract (letterPaddingDp time (word.length - 1).toFloat())) div 2F
    fun getEndPadding(word: String) = ((Device.metrics.sizeDp.width subtract (letterSizeDp.width time word.length.toFloat())) subtract ((letterPaddingDp time (word.length - 1).toFloat())))div 2F
    val screenSize = Device.metrics.sizeDp
    val topPadding = verticalPadding
    val bottomPadding = verticalPadding
    init {
        Log.e(TAG, "init: $letterSizeDp")
//        Log.e(TAG, "init: $newLetterSizeDp")
    }
}