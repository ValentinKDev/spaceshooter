package com.mobilegame.spaceshooter.presentation.ui.screens.utils

import android.util.Log
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
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.DpOffset
import com.mobilegame.spaceshooter.logic.uiHandler.template.BackgroundUI
import com.mobilegame.spaceshooter.presentation.theme.MyColor
import java.util.Calendar

@Composable
fun BackgroundGrid(ui: BackgroundUI) {
    val dotDpSize: Dp = remember { ui.dotSizeDp }
    val dpOffsetList: List<DpOffset> = remember { ui.dpOffsetList }
    val drawGrid = remember { ui.drawGrid }.collectAsState()

    LaunchedEffect(key1 = "") {
        drawGrid.value?.let { if (it) { ui.drawGrid.value = false } }
        if (ui.dpOffsetList.isNotEmpty()) { ui.drawGrid.value = true }
    }

    drawGrid.value?.let { grid ->
        if (grid) {
        Log.e("BackgroundGrid", "Grid CREATION: launch / relaunch")
        val timerStart = Calendar.getInstance().timeInMillis
        Box(
            Modifier
                .fillMaxSize()
                .background(Color.Black)
        ) {
            dpOffsetList.forEach {
//            ui.dpOffsetList.forEach {
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