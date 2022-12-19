package com.mobilegame.spaceshooter.presentation.ui.screens.utils.backButton

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.mobilegame.spaceshooter.domain.model.screen.uiAdapter.BackButtonAdapter
import com.mobilegame.spaceshooter.domain.model.screen.mainScreen.PressureNavigationViewModel
import com.mobilegame.spaceshooter.presentation.ui.navigation.Navigator
import com.mobilegame.spaceshooter.presentation.ui.screens.utils.CenterComposableVertically
import com.mobilegame.spaceshooter.presentation.ui.screens.utils.ChargingButton

@Composable
fun BackButton(vm: PressureNavigationViewModel, navigator: Navigator, ui: BackButtonAdapter) {

    CenterComposableVertically {
        ChargingButton(
            handler = vm,
            sizeDp = ui.sizes.canvasDp,
            roundShape = true,
            navigator = navigator
        ) {
            Box(
                Modifier
                    .fillMaxHeight()
            ) {
                BackIcon(ui)
            }
        }
    }
}