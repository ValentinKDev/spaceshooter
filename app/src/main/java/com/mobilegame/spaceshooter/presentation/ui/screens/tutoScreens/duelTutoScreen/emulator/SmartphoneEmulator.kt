package com.mobilegame.spaceshooter.presentation.ui.screens.duelTutoScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Constraints
import androidx.constraintlayout.compose.ConstraintLayout
import com.mobilegame.spaceshooter.logic.model.screen.tutoScreen.TutoScreenViewModel
import com.mobilegame.spaceshooter.presentation.ui.screens.tutoScreens.duelTutoScreen.emulator.SmartphoneSkin
import com.mobilegame.spaceshooter.presentation.ui.screens.utils.PaddingComposable

@Composable
fun SmartphoneEmulator(vm: TutoScreenViewModel, id: String) {
    PaddingComposable(
        horizontalPadding = vm.ui.generalLayout.horizontalPadd
    ) {
        Box() {
            EmulatorScreen(vm, id)
            SmartphoneSkin(vm)
        }
    }
}
