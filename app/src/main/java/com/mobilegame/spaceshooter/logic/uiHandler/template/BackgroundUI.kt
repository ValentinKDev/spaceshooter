package com.mobilegame.spaceshooter.logic.uiHandler.template

import android.util.Log
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import com.mobilegame.spaceshooter.data.device.Device
import kotlinx.coroutines.flow.MutableStateFlow
import java.util.Calendar

class BackgroundUI {
    val TAG = "BackgroundUI"

    val dotSizeDp = 2.dp
    val padSizeDp = 5.dp
    var dotNumberInLine = 0
    var lineNumberOfDots = 0
    var dpOffsetList: List<DpOffset> = listOf()
    var drawGrid = MutableStateFlow<Boolean?>(null)
    fun init() {
        val timerStart = Calendar.getInstance().timeInMillis
        dotNumberInLine = (Device.metrics.sizeDp.width.value / (dotSizeDp.value + padSizeDp.value)).toInt()
        lineNumberOfDots = (Device.metrics.sizeDp.height.value / (dotSizeDp.value + padSizeDp.value)).toInt()
        val mutableLine: MutableList<DpOffset> = mutableListOf()
        var line = 0
        while (line <= lineNumberOfDots) {
            var col = 0
            while (col <= dotNumberInLine) {
                val dpOffset = DpOffset(
                    x = (col * (dotSizeDp.value + padSizeDp.value)).dp,
                    y = (line * (padSizeDp.value + dotSizeDp.value)).dp,
                )
//                Log.i(TAG, "init: col $col $dpOffset")
//                if ( dpOffset.x > widthDp)
//                    Log.e(TAG, "init: ERROR OFFset X", )
//                else if  (dpOffset.y > heightDp)
//                    Log.e(TAG, "init: ERROR OFFset Y line $line", )
//                else
                mutableLine.add(dpOffset)
                col++
            }
            line++
        }
        dpOffsetList = mutableLine.toList()
        val timerStop = Calendar.getInstance().timeInMillis
        Log.i(TAG, "init: timer ${timerStop - timerStart}")

        Log.i(TAG, "init: dotNumberInLine $dotNumberInLine")
        Log.i(TAG, "init: lineNumberOfDots $lineNumberOfDots")
    }
}

