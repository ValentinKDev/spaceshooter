package com.mobilegame.spaceshooter.presentation.ui.screens.tutoScreens.duelTutoScreen

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.mobilegame.spaceshooter.logic.model.navigation.Screens
import com.mobilegame.spaceshooter.logic.model.screen.tutoScreen.TutoScreenViewModel
import com.mobilegame.spaceshooter.logic.model.navigation.Navigator
import com.mobilegame.spaceshooter.presentation.ui.template.backButton.BackButton


@Composable
fun BackButtonLayer(vm: TutoScreenViewModel, navigator: Navigator) {
    Column(
        Modifier
            .fillMaxSize()
    ) {
        Box( Modifier.weight(vm.ui.template.percent.header) ) {
            BackButton(
                vm = vm.backButtonPressureNavigationVM,
                navigator = navigator,
                ui = vm.ui.template.backButton,
                backNav = Screens.DuelTutoScreen.backNav ?: Screens.None,
//                instant = null,
            )
        }
        Box( Modifier.weight(vm.ui.template.percent.bodyWithoutBand))
    }
}