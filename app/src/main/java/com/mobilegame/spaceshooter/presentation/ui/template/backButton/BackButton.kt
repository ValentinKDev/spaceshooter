package com.mobilegame.spaceshooter.presentation.ui.template.backButton

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import com.mobilegame.spaceshooter.logic.model.screen.Screens
import com.mobilegame.spaceshooter.logic.model.navigation.PressureViewModel
import com.mobilegame.spaceshooter.logic.uiHandler.template.BackButtonUI
import com.mobilegame.spaceshooter.logic.model.navigation.Navigator
import com.mobilegame.spaceshooter.presentation.ui.screens.utils.CenterComposableVertically
import com.mobilegame.spaceshooter.presentation.ui.screens.utils.ChargingButton
import com.mobilegame.spaceshooter.presentation.ui.screens.utils.PaddingComposable

@Composable
fun BackButton(
    vm: PressureViewModel,
    navigator: Navigator,
    ui: BackButtonUI,
    backNav: Screens
) {
    val startBackJob by remember { vm.full }.collectAsState()
    LaunchedEffect(startBackJob) { if (startBackJob) navigator.navig(backNav) }

    PaddingComposable(
        startPaddingRatio = ui.padding.start
    ) {
        CenterComposableVertically(ui.id) {
            ChargingButton(
                handler = vm,
                sizeDp = ui.sizes.canvasSizeDp,
                roundShape = true,
                content = {
                    Box(
                        Modifier
                            .fillMaxHeight()
                    ) {
                        BackIcon(ui)
                    }
                },
            )
        }
    }
}