package com.mobilegame.spaceshooter.presentation.ui.screens.tutoScreen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
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
        Box(
            Modifier
                .fillMaxWidth()
                .weight(vm.ui.backButtonLayer.buttonHeightWeight)
        ) {
            BackButton(
                vm = vm.backButtonPressureNavigationVM,
                navigator = navigator,
                ui = vm.ui.backButton
            )
        }
        Box(
            Modifier
                .weight(vm.ui.backButtonLayer.emptyHeightWeight)
        ) {
        }
    }
}