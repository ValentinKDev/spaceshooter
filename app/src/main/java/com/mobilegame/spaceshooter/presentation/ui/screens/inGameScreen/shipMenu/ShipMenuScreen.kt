package com.mobilegame.spaceshooter.presentation.ui.screens.inGameScreen.shipMenu

import ShipMenuViewModel
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.viewmodel.compose.viewModel
import com.mobilegame.spaceshooter.logic.model.navigation.Navigator
import com.mobilegame.spaceshooter.logic.model.navigation.Screens
import com.mobilegame.spaceshooter.logic.model.screen.connection.wifiScreen.WifiScreenViewModel
import com.mobilegame.spaceshooter.logic.model.screen.inGameScreens.duelGameScreen.LaunchDuelGameViewModel
import com.mobilegame.spaceshooter.presentation.ui.screens.connection.elements.background.BackgroundBanner
import com.mobilegame.spaceshooter.presentation.ui.template.TemplateWithBand

@Composable
//fun ShipMenuScreen(vm: LaunchDuelGameViewModel, nav: Navigator) {
fun ShipMenuScreen(nav: Navigator, vm: ShipMenuViewModel = viewModel() ) {
//    val isPickedShip by remember { vm.pickedShip }.collectAsState()
    LaunchedEffect(key1 = "") {
        vm.initNav(nav)
//        if (isPickedShip)
    }
    TemplateWithBand(
        navigator = nav,
        backNav = Screens.WifiScreen.backNav,
//        ui = vm.shipMenuVM.templateUI,
        ui = vm.templateUI,
        header = {},
        band = { ShipMenuBand(vm) },
        body = { ShipMenuBody(vm) }
    )
}