package com.mobilegame.spaceshooter.presentation.ui.screens.shipMenuScreen

import ShipMenuViewModel
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.lifecycle.viewmodel.compose.viewModel
import com.mobilegame.spaceshooter.logic.model.navigation.Navigator
import com.mobilegame.spaceshooter.logic.model.navigation.Screens
import com.mobilegame.spaceshooter.presentation.ui.template.TemplateWithBand

@Composable
//fun ShipMenuScreen(vm: LaunchDuelGameViewModel, nav: Navigator) {
fun ShipMenuScreen(nav: Navigator, vm: ShipMenuViewModel = viewModel() ) {
    val navigate by remember { vm.navigate }.collectAsState()
    LaunchedEffect(navigate) {
//        vm.initNav(nav)
        vm.navigateToGame(nav)
    }
    TemplateWithBand(
        navigator = nav,
        backNav = Screens.WifiScreen.backNav,
        ui = vm.templateUI,
        header = {},
        band = { ShipMenuBand(vm) },
        body = { ShipMenuBody(vm) }
    )
}