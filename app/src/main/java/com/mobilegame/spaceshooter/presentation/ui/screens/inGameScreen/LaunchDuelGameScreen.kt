package com.mobilegame.spaceshooter.presentation.ui.screens.inGameScreen

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import com.mobilegame.spaceshooter.logic.model.screen.inGameScreens.duelGameScreen.LaunchDuelGameViewModel
import com.mobilegame.spaceshooter.presentation.ui.navigation.Navigator

@Composable
fun LaunchDuelGameScreen(navigator: Navigator, vm: LaunchDuelGameViewModel = viewModel()) {
    DuelGameScreen(vm = vm.gameVM)
}