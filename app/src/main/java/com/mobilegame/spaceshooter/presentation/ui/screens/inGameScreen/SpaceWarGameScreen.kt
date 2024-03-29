package com.mobilegame.spaceshooter.presentation.ui.screens.inGameScreen

import androidx.compose.runtime.*
import com.mobilegame.spaceshooter.logic.model.screen.inGameScreens.duelGameScreen.SpaceWarGameViewModel
import com.mobilegame.spaceshooter.logic.model.navigation.Navigator
import com.mobilegame.spaceshooter.presentation.ui.screens.inGameScreen.backGrounds.DefaultSpaceBackGround
import com.mobilegame.spaceshooter.presentation.ui.screens.inGameScreen.elements.Elements
import com.mobilegame.spaceshooter.utils.analyze.eLog


@Composable
fun SpaceWarGameScreen(navigator: Navigator = Navigator(), vm: SpaceWarGameViewModel) {
//    val onPause by remember { vm.shipVM.motionVM.gameOnPause }.collectAsState()

    LaunchedEffect(true) {
        eLog("SpaceWarGameScreen", "Launch Screen")
        vm.initNav(navigator)
    }

    //todo: Use ConstraintSet with Width Ratio to reduce screen size depending on enemy device
//    DefaultSpaceBackGround {
//        if (onPause) PausePopUp(vm)
//        else
            Elements(vm)
//    }
}