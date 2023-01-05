package com.mobilegame.spaceshooter.presentation.ui.screens.inGameScreen

import androidx.compose.runtime.*
import com.mobilegame.spaceshooter.logic.model.screen.inGameScreens.duelGameScreen.DuelGameViewModel
import com.mobilegame.spaceshooter.presentation.ui.navigation.Navigator
import com.mobilegame.spaceshooter.presentation.ui.screens.inGameScreen.backGrounds.DefaultSpaceBackGround
import com.mobilegame.spaceshooter.presentation.ui.screens.inGameScreen.elements.AmmunitionView
import com.mobilegame.spaceshooter.presentation.ui.screens.inGameScreen.elements.SpaceShipView
import com.mobilegame.spaceshooter.utils.analyze.eLog


@Composable
fun DuelGameScreen(navigator: Navigator = Navigator(), vm: DuelGameViewModel) {
    LaunchedEffect(true) {
        eLog("InGameScreen", "Launch Screen")
    }

    DefaultSpaceBackGround {
        SpaceShipView(vm)
        AmmunitionView(vm.shipVM.ammoVM)
    }
}