package com.mobilegame.spaceshooter.presentation.ui.screens.shipMenuScreen

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
import com.mobilegame.spaceshooter.presentation.ui.screens.inGameScreen.backGrounds.AnimatedBackGroundStatic
import com.mobilegame.spaceshooter.presentation.ui.template.TemplateWithBand
import com.mobilegame.spaceshooter.utils.analyze.eLog

@Composable
//fun ShipMenuScreen(vm: LaunchDuelGameViewModel, nav: Navigator) {
fun ShipMenuScreen(nav: Navigator, vm: ShipMenuViewModel = viewModel() ) {
    val navigate by remember { vm.navigate }.collectAsState()
    LaunchedEffect(true) { eLog("ShipMenuScreen", "ShipMenuScreen start") }
    LaunchedEffect(navigate) { if (navigate) vm.navigateToGame(nav) }

    val shipListIndex = remember { vm.shipPicking.shipListIndex }.collectAsState()
    AnimatedBackGroundStatic(ui = vm.shipMenuUI.backgroundsList[shipListIndex.value])

    TemplateWithBand(
        backNav = Screens.WifiScreen.backNav ?: Screens.None,
        ui = vm.templateUI,
        header = {  },
        band = { ShipMenuBand(vm) },
        body = { ShipMenuBody(vm) }
    )
}