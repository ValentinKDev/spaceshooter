package com.mobilegame.spaceshooter.presentation.ui.screens.inGameScreen.elements

import androidx.compose.runtime.Composable
import com.mobilegame.spaceshooter.logic.model.screen.inGameScreens.duelGameScreen.SpaceWarGameViewModel


@Composable
fun Elements(vm: SpaceWarGameViewModel) {
    SpaceShipView(vm)
    AmmunitionView(vm.shipVM.ammoVM)
    ShootsView(vm)
}