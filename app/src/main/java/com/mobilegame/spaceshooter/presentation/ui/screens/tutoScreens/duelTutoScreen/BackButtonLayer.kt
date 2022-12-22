package com.mobilegame.spaceshooter.presentation.ui.screens.tutoScreens.duelTutoScreen

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.mobilegame.spaceshooter.domain.model.screen.tutoScreen.TutoScreenViewModel
import com.mobilegame.spaceshooter.presentation.ui.navigation.Navigator
import com.mobilegame.spaceshooter.presentation.ui.screens.utils.backButton.BackButton


@Composable
fun BackButtonLayer(vm: TutoScreenViewModel, navigator: Navigator) {
    Column(
        Modifier
            .fillMaxSize()
    ) {
        Box( Modifier.weight(vm.ui.template.headerHeightWeight) ) {
            BackButton(
                vm = vm.backButtonPressureNavigationVM,
                navigator = navigator,
                ui = vm.ui.template.backButton
            )
        }
        Box( Modifier.weight(vm.ui.template.emptySpaceHeightWeight))
    }
}