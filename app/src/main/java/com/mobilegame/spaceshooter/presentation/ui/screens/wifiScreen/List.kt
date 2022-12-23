package com.mobilegame.spaceshooter.presentation.ui.screens.wifiScreen

import androidx.compose.runtime.Composable
import com.mobilegame.spaceshooter.logic.model.screen.wifiScreen.WifiScreenViewModel
import com.mobilegame.spaceshooter.presentation.ui.screens.utils.PaddingComposable
import com.mobilegame.spaceshooter.presentation.ui.screens.wifiScreen.elements.WifiBanner

@Composable
internal fun List(vm: WifiScreenViewModel) {
    PaddingComposable(
//        topPaddingRatio = 0.2F,
        topPaddingRatio = 0.4F,
    ) {
        WifiBanner(ui = vm.ui.banner.wifiIcon)
    }
}