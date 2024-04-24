package com.mobilegame.spaceshooter.logic.uiHandler.template

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Log
import android.view.View
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import androidx.core.graphics.applyCanvas
import com.mobilegame.spaceshooter.data.device.Device
import kotlinx.coroutines.flow.MutableStateFlow
import java.io.File
import java.util.Calendar

class BackgroundUI {
    val TAG = "BackgroundUI"

    val dotSizeDp = 2.dp
    val padSizeDp = 5.dp
    var dotNumberInLine = 0
    var lineNumberOfDots = 0
    var dpOffsetList: List<DpOffset> = listOf()
    var drawGrid = MutableStateFlow<Boolean?>(null)
    var bitmap: Bitmap? = null
    fun init(context: Context) {
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

//        val test: File = File(context.filesDir, "background.png")
//        bitmap = BitmapFactory.decodeFile(test.absolutePath)
    }

//    fun createBitmap(view: View, context: Context) {
//        val bmp: Bitmap = Bitmap.createBitmap(Device.metrics.width.toInt(), Device.metrics.height.toInt(),
//            Bitmap.Config.ARGB_8888).applyCanvas {
//            view.draw(this)
//        }
//        bmp.let {
//            File(context.filesDir, "background.png")
//                .writeBitmap(bmp, Bitmap.CompressFormat.PNG, 100)
//        }
//    }

    private fun File.writeBitmap(bitmap: Bitmap, format: Bitmap.CompressFormat, quality: Int) {
        outputStream().use { out ->
            bitmap.compress(format, quality, out)
            out.flush()
        }
    }
}

