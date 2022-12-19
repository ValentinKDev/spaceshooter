package com.mobilegame.spaceshooter.presentation.ui.screens.wifiScreen

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import com.mobilegame.spaceshooter.presentation.ui.navigation.Navigator
import com.mobilegame.spaceshooter.utils.analyze.wLog


@Composable
fun WifiScreen(navigator: Navigator) {
    LaunchedEffect(true) {
        wLog("WifiScreen", "start")
    }
}