package com.mobilegame.spaceshooter.presentation.ui.screens.utils.backButton

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.mobilegame.spaceshooter.logic.model.screen.mainScreen.PressureNavigationViewModel
import com.mobilegame.spaceshooter.logic.uiHandler.template.BackButtonUI
import com.mobilegame.spaceshooter.logic.uiHandler.template.MainTemplateUI
import com.mobilegame.spaceshooter.presentation.ui.navigation.Navigator
import com.mobilegame.spaceshooter.presentation.ui.screens.utils.CenterComposableVertically
import com.mobilegame.spaceshooter.presentation.ui.screens.utils.ChargingButton
import com.mobilegame.spaceshooter.presentation.ui.screens.utils.PaddingComposable

@Composable
fun BackButton(vm: PressureNavigationViewModel, navigator: Navigator, ui: BackButtonUI = MainTemplateUI.backButton) {
    PaddingComposable(
        startPaddingRatio = ui.padding.start
    ) {
        CenterComposableVertically(ui.id) {
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
}