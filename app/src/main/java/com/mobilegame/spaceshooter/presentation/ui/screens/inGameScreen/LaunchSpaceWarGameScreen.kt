package com.mobilegame.spaceshooter.presentation.ui.screens.inGameScreen

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.lifecycle.viewmodel.compose.viewModel
import com.mobilegame.spaceshooter.logic.model.navigation.Navigator
import com.mobilegame.spaceshooter.logic.model.screen.inGameScreens.duelGameScreen.LaunchDuelGameViewModel
import com.mobilegame.spaceshooter.presentation.ui.screens.inGameScreen.shipMenu.ShipMenuScreen

@Composable
fun LaunchSpaceWarGameScreen(navigator: Navigator, vm: LaunchDuelGameViewModel = viewModel()) {
    val pickedShip by remember { vm.shipMenuVM.pickedShip }.collectAsState()
    LaunchedEffect(key1 = pickedShip) {
        Log.e("launchEffect", "LaunchSpaceWarGameScreen: pickedShip $pickedShip", )
        vm.gameVM.updateShipType(vm.shipMenuVM.shipType.value)
    }

    if (pickedShip) SpaceWarGameScreen(vm = vm.gameVM)
    else ShipMenuScreen(vm, navigator)
}