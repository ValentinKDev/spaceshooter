package com.mobilegame.spaceshooter.presentation.ui.screens.utils

import android.view.Window
import android.view.WindowManager
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import com.google.accompanist.systemuicontroller.SystemUiController
import com.google.accompanist.systemuicontroller.rememberSystemUiController

@Composable
fun HideScreenBars(window: Window) {
    val systemUiController: SystemUiController = rememberSystemUiController()

    window.setFlags( WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)
    DisposableEffect(Unit) {
        systemUiController.isStatusBarVisible = false
        systemUiController.isNavigationBarVisible = false
        systemUiController.isSystemBarsVisible = false
        onDispose { }
    }
}
