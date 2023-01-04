package com.mobilegame.spaceshooter.presentation.ui.screens.duelTutoScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.mobilegame.spaceshooter.logic.model.screen.tutoScreen.TutoScreenViewModel
import com.mobilegame.spaceshooter.presentation.ui.screens.inGameScreen.DuelGameScreen
import com.mobilegame.spaceshooter.presentation.ui.screens.utils.CenterComposable

@Composable
fun EmulatorScreen(vm: TutoScreenViewModel) {
    CenterComposable {
        Box(
            Modifier
                .size(vm.ui.smartphoneEmulator.sizes.screenInnerDp)
                .background(Color.Black)
        ) {
            DuelGameScreen(vm = vm.gameVM)
        }
    }
}
