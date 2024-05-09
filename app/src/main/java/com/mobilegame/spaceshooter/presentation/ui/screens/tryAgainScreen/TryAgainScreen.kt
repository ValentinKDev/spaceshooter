package com.mobilegame.spaceshooter.presentation.ui.screens.tryAgainScreen

import ShipMenuViewModel
import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.lifecycle.viewmodel.compose.viewModel
import com.mobilegame.spaceshooter.logic.model.navigation.Navigator
import com.mobilegame.spaceshooter.logic.model.navigation.Screens
import com.mobilegame.spaceshooter.logic.model.screen.tryAgainScreen.TryAgainStats
import com.mobilegame.spaceshooter.presentation.ui.screens.inGameScreen.backGrounds.AnimatedBackGroundStatic
import com.mobilegame.spaceshooter.presentation.ui.screens.shipMenuScreen.ShipMenuBand
import com.mobilegame.spaceshooter.presentation.ui.screens.shipMenuScreen.ShipMenuBody
import com.mobilegame.spaceshooter.presentation.ui.template.TemplateWithBand

@Composable
fun TryAgainScreen(nav: Navigator, vm: ShipMenuViewModel = viewModel() ) {
    val navigate by remember { vm.navigate }.collectAsState()
    LaunchedEffect(key1 = navigate) {
        if (navigate) vm.navigateToGame(nav)
    }

    val shipListIndex = remember { vm.shipPicking.shipListIndex }.collectAsState()
    AnimatedBackGroundStatic(ui = vm.shipMenuUI.backgroundsList[shipListIndex.value])

    TemplateWithBand(
        backPressureHandler = vm.backPressureHandler,
        ui = vm.templateUI,
        header = {},
        band = { ShipMenuBand(vm, vm.gameStats) },
        body = { ShipMenuBody(vm) }
    )
}