package com.mobilegame.spaceshooter.presentation.ui.screens.tutoScreen

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.lifecycle.viewmodel.compose.viewModel
import com.mobilegame.spaceshooter.domain.model.screen.tutoScreen.TutoScreenViewModel
import com.mobilegame.spaceshooter.presentation.ui.navigation.Navigator
import com.mobilegame.spaceshooter.utils.analyze.eLog


@Composable
fun TutoScreen(navigator: Navigator, vm: TutoScreenViewModel = viewModel()) {
    LaunchedEffect(true) {
        eLog("TutoScreen", "Launch")
    }

    BackButtonLayer(vm, navigator)
    SmartphonesFaceToFace(vm)
}