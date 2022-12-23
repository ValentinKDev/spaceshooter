package com.mobilegame.spaceshooter.presentation.ui.screens.tutoScreens.duelTutoScreen

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.lifecycle.viewmodel.compose.viewModel
import com.mobilegame.spaceshooter.logic.model.screen.tutoScreen.TutoScreenViewModel
import com.mobilegame.spaceshooter.presentation.ui.navigation.Navigator
import com.mobilegame.spaceshooter.utils.analyze.eLog


@Composable
fun DuelTutoScreen(navigator: Navigator, vm: TutoScreenViewModel = viewModel()) {
    LaunchedEffect(true) {
        eLog("DuelTutoScreen", "Launch duel Tuto Screen")
    }

    FakeJoystickLayer(vm)
    BackButtonLayer(vm, navigator)
    SmartphonesFaceToFace(vm)
}