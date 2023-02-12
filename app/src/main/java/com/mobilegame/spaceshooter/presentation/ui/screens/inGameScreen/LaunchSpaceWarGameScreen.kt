package com.mobilegame.spaceshooter.presentation.ui.screens.inGameScreen

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import com.mobilegame.spaceshooter.logic.model.screen.inGameScreens.duelGameScreen.LaunchDuelGameViewModel

@Composable
fun LaunchSpaceWarGameScreen(vm: LaunchDuelGameViewModel = viewModel()) {
    SpaceWarGameScreen(vm = vm.gameVM)
}