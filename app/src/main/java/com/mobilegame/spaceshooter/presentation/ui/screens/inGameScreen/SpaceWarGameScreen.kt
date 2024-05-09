package com.mobilegame.spaceshooter.presentation.ui.screens.inGameScreen

import androidx.compose.runtime.*
import com.mobilegame.spaceshooter.logic.model.screen.inGameScreens.duelGameScreen.SpaceWarGameViewModel
import com.mobilegame.spaceshooter.logic.model.navigation.Navigator
import com.mobilegame.spaceshooter.logic.uiHandler.screens.games.background.BackgroundUI
import com.mobilegame.spaceshooter.presentation.ui.screens.inGameScreen.backGrounds.AnimateScreenBorders
import com.mobilegame.spaceshooter.presentation.ui.screens.inGameScreen.backGrounds.AnimatedBackGroundInMotion
import com.mobilegame.spaceshooter.presentation.ui.screens.inGameScreen.elements.Elements
import com.mobilegame.spaceshooter.utils.analyze.eLog


@Composable
fun SpaceWarGameScreen(navigator: Navigator = Navigator(), vm: SpaceWarGameViewModel) {
    LaunchedEffect(true) {
        eLog("SpaceWarGameScreen", "Launch Screen")
    }
    //todo: Use ConstraintSet with Width Ratio to reduce screen size depending on enemy device
    AnimatedBackGroundInMotion(
        ui = BackgroundUI(vm.userShipType),
        motionVM = vm.shipVM.motionVM,
        hitVM = vm.hitAnimVM
//        color = pairOfIsHitAndColor.second,
//        alphaBonus = if (pairOfIsHitAndColor.first) 0.13F else 0F,
    )
    Elements(vm)
    AnimateScreenBorders(vm.hitAnimVM, BackgroundUI(vm.userShipType))
}