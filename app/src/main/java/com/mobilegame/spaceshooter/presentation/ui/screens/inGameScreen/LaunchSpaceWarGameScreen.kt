package com.mobilegame.spaceshooter.presentation.ui.screens.inGameScreen

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import com.mobilegame.spaceshooter.data.device.Device
import com.mobilegame.spaceshooter.logic.model.navigation.Navigator
import com.mobilegame.spaceshooter.logic.model.screen.inGameScreens.GameResult
import com.mobilegame.spaceshooter.logic.model.screen.inGameScreens.duelGameScreen.LaunchDuelGameViewModel

@Composable
fun LaunchSpaceWarGameScreen(vm: LaunchDuelGameViewModel, navigator: Navigator) {
    val gameResult by remember { Device.event.gameResult }.collectAsState()

    LaunchedEffect(key1 = gameResult) {
        if (gameResult == GameResult.DEFEAT || gameResult == GameResult.VICTORY) {
            vm.gameVM.navigateToTryAgain(navigator, gameResult)
//            vm.navigateToTryAgain(navigator, gameResult)
        }
    }
        SpaceWarGameScreen(vm = vm.gameVM)
}