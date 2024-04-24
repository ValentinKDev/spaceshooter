package com.mobilegame.spaceshooter.presentation.ui.screens.utils

import android.content.Context
import android.graphics.Bitmap
import android.os.Handler
import android.os.Looper
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.DpOffset
import androidx.core.content.getSystemService
import androidx.core.graphics.applyCanvas
import com.mobilegame.spaceshooter.data.device.Device
import com.mobilegame.spaceshooter.logic.uiHandler.template.BackgroundUI
import com.mobilegame.spaceshooter.presentation.theme.MyColor
import java.io.File
import java.util.Calendar

@Composable
fun BackgroundGrid(ui: BackgroundUI) {
    val dotDpSize: Dp = remember { ui.dotSizeDp }
    val dpOffsetList: List<DpOffset> = remember { ui.dpOffsetList }
    val drawGrid = remember { ui.drawGrid }.collectAsState()
    val bitmap = remember { ui.bitmap }
    val timerStart = Calendar.getInstance().timeInMillis
    val view = LocalView.current
    val context = LocalContext.current

    LaunchedEffect(key1 = "") {

        drawGrid.value?.let { if (it) { ui.drawGrid.value = false } }
        if (ui.dpOffsetList.isNotEmpty()) {
            ui.drawGrid.value = true
//            ui.createBitmap(view, context)
        }
    }

//    bitmap?.let {
//        Image(
//            modifier = Modifier
//                .onGloballyPositioned { layout ->
//                    Log.i("backgroundGrid", "BackgroundGrid: ${layout.size}")
//                }
//            ,
//            bitmap = it.asImageBitmap(),
//            contentDescription = "test"
//        )
//    }

    drawGrid.value?.let { grid ->
        if (grid) {
        Log.e("BackgroundGrid", "Grid CREATION: launch / relaunch")
        Box(
            Modifier
                .fillMaxSize()
                .background(Color.Black)
        ) {
            dpOffsetList.forEach {
                Box(
                    Modifier
                        .offset(x = it.x, y = it.y)
                        .background(MyColor.grayDark8)
                        .size(dotDpSize)
                )
            }
        }
        val timerStop = Calendar.getInstance().timeInMillis
        Log.e("BackgroundGrid", "Grid CREATION: time creation ${timerStop - timerStart }")
    } }
}