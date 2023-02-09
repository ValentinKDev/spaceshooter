package com.mobilegame.spaceshooter.presentation.ui.screens.inGameScreen

import androidx.compose.runtime.*
import com.mobilegame.spaceshooter.logic.model.screen.inGameScreens.duelGameScreen.DuelGameViewModel
import com.mobilegame.spaceshooter.logic.model.navigation.Navigator
import com.mobilegame.spaceshooter.presentation.ui.screens.inGameScreen.backGrounds.DefaultSpaceBackGround
import com.mobilegame.spaceshooter.presentation.ui.screens.inGameScreen.elements.Elements
import com.mobilegame.spaceshooter.utils.analyze.eLog


@Composable
fun DuelGameScreen(navigator: Navigator = Navigator(), vm: DuelGameViewModel) {
    LaunchedEffect(true) {
        eLog("InGameScreen", "Launch Screen")
    }

    //todo: Use ConstraintSet with Width Ratio to reduce screen size depending on enemy device
    DefaultSpaceBackGround {
        Elements(vm)
    }
}