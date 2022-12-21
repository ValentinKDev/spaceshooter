package com.mobilegame.spaceshooter.presentation.ui.screens.tutoScreen

import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import com.mobilegame.spaceshooter.domain.model.screen.tutoScreen.TutoScreenViewModel
import com.mobilegame.spaceshooter.presentation.ui.screens.tutoScreen.emulator.SmartphoneSkin
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
