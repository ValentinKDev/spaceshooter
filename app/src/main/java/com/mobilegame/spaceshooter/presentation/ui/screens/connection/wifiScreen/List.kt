package com.mobilegame.spaceshooter.presentation.ui.screens.wifiScreen

import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.constraintlayout.compose.ConstraintLayout
import com.mobilegame.spaceshooter.logic.model.screen.connection.wifiScreen.WifiScreenViewModel
import com.mobilegame.spaceshooter.presentation.ui.screens.utils.PaddingComposable
import com.mobilegame.spaceshooter.presentation.ui.screens.connection.wifiScreen.elements.WifiBanner

@Composable
internal fun List(vm: WifiScreenViewModel) {
    PaddingComposable(
        topPaddingRatio = 0.4F,
    ) {
        WifiBanner(ui = vm.ui.banner.wifiIcon)
    }
}