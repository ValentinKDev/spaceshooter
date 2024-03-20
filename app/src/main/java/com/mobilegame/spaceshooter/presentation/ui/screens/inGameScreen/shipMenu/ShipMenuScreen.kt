package com.mobilegame.spaceshooter.presentation.ui.screens.inGameScreen.shipMenu

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.mobilegame.spaceshooter.logic.model.navigation.Navigator
import com.mobilegame.spaceshooter.logic.model.navigation.Screens
import com.mobilegame.spaceshooter.logic.model.screen.connection.spaceShipMenu.ShipMenuViewModel
import com.mobilegame.spaceshooter.logic.model.screen.connection.wifiScreen.WifiScreenViewModel
import com.mobilegame.spaceshooter.logic.model.screen.inGameScreens.duelGameScreen.LaunchDuelGameViewModel
import com.mobilegame.spaceshooter.presentation.ui.screens.connection.elements.background.BackgroundBanner
import com.mobilegame.spaceshooter.presentation.ui.template.TemplateWithBand

@Composable
//fun ShipMenuScreen(vm: WifiScreenViewModel, nav: Navigator) {
fun ShipMenuScreen(vm: LaunchDuelGameViewModel, nav: Navigator) {
    TemplateWithBand(
        navigator = nav,
        backNav = Screens.WifiScreen.backNav,
//        ui = vm.ui.shipMenu.template,
        ui = vm.shipMenuVM.templateUI,
        header = {},
        band = { ShipMenuBand(vm.shipMenuVM) },
        body = { ShipMenuBody(vm) }
    )
}