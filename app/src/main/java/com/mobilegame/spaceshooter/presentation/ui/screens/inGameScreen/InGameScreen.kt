package com.mobilegame.spaceshooter.presentation.ui.screens.inGameScreen

import android.app.Activity
import android.hardware.Sensor
import android.hardware.SensorManager
import android.hardware.TriggerEvent
import android.hardware.TriggerEventListener
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import com.mobilegame.spaceshooter.logic.model.screen.inGameScreen.InGameViewModel
import com.mobilegame.spaceshooter.presentation.ui.navigation.Navigator
import com.mobilegame.spaceshooter.presentation.ui.screens.utils.spaceShips.DefaultShip
import com.mobilegame.spaceshooter.utils.analyze.eLog


@Composable
fun InGameScreen(navigator: Navigator = Navigator(), vm: InGameViewModel) {
    val position by remember { vm.shipVM.pCenterDp }.collectAsState()

    LaunchedEffect(true) {
        eLog("InGameScreen", "Launch Screen")
    }


    Box( Modifier.fillMaxSize() ) {
        Box(
            Modifier
                .wrapContentSize()
                .offset(position.x, position.y)
        ) {
            DefaultShip( vm.shipVM, vm.ui.spaceShip )
        }
    }
}