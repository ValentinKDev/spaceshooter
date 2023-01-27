package com.mobilegame.spaceshooter.presentation.ui.screens.wifiScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.mobilegame.spaceshooter.logic.model.screen.connection.wifiScreen.WifiScreenViewModel
import com.mobilegame.spaceshooter.presentation.ui.screens.utils.PaddingComposable
import com.mobilegame.spaceshooter.presentation.ui.screens.connection.wifiScreen.elements.WifiBanner
import com.mobilegame.spaceshooter.presentation.ui.screens.utils.CenterComposable
import com.mobilegame.spaceshooter.utils.extensions.toSquare

@Composable
internal fun List(vm: WifiScreenViewModel) {
    PaddingComposable(
        topPaddingRatio = 0.4F,
    ) { WifiBanner(ui = vm.ui.banner.wifiIcon) }
    CenterComposable(id = "buttonBroadcast") {
        Row() {
            Box(
                Modifier
                    .size(40.dp)
                    .background(Color.Red)
                    .clickable {
                        vm.goDiscovery()
                    })
            Spacer(modifier = Modifier.size(10.dp.toSquare()))
            Box(
                Modifier
                    .size(40.dp)
                    .background(Color.Blue)
                    .clickable {
                        vm.fetch()
                    })
        }
    }
}