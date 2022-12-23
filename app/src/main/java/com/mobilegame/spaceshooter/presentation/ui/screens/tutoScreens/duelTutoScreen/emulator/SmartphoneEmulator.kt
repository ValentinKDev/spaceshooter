package com.mobilegame.spaceshooter.presentation.ui.screens.duelTutoScreen

import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import com.mobilegame.spaceshooter.logic.model.screen.tutoScreen.TutoScreenViewModel
import com.mobilegame.spaceshooter.presentation.ui.screens.tutoScreens.duelTutoScreen.emulator.SmartphoneSkin
import com.mobilegame.spaceshooter.presentation.ui.screens.utils.PaddingComposable

@Composable
fun SmartphoneEmulator(vm: TutoScreenViewModel) {
    PaddingComposable(
        horizontalPadding = vm.ui.generalLayout.horizontalPadd
    ) {
        Box {
            EmulatorScreen(vm)
            SmartphoneSkin(vm)
        }
    }
}
