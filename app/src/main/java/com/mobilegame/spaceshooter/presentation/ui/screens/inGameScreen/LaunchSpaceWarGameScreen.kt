package com.mobilegame.spaceshooter.presentation.ui.screens.inGameScreen

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.lifecycle.viewmodel.compose.viewModel
import com.mobilegame.spaceshooter.data.device.Device
import com.mobilegame.spaceshooter.logic.model.screen.inGameScreens.GameResult
import com.mobilegame.spaceshooter.logic.model.screen.inGameScreens.duelGameScreen.LaunchDuelGameViewModel
import com.mobilegame.spaceshooter.logic.uiHandler.screens.games.background.BackgroundUI
import com.mobilegame.spaceshooter.presentation.ui.screens.inGameScreen.backGrounds.AnimatedBackGroundInMotion

@Composable
//fun LaunchSpaceWarGameScreen(vm: LaunchDuelGameViewModel, navigator: Navigator) {
fun LaunchSpaceWarGameScreen(vm: LaunchDuelGameViewModel = viewModel()) {
    val gameResult by remember { Device.event.gameResult }.collectAsState()

    LaunchedEffect(key1 = gameResult) {
        if (gameResult == GameResult.DEFEAT || gameResult == GameResult.VICTORY) {
            vm.gameVM.navigateToTryAgain(gameResult)
        }
    }
    AnimatedBackGroundInMotion(ui = BackgroundUI(vm.gameVM.userShipType), motionVM = vm.gameVM.shipVM.motionVM)
    SpaceWarGameScreen(vm = vm.gameVM)
}