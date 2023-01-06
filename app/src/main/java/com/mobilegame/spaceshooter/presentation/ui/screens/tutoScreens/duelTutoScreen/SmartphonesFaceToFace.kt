package com.mobilegame.spaceshooter.presentation.ui.screens.tutoScreens.duelTutoScreen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.mobilegame.spaceshooter.logic.model.screen.tutoScreen.TutoScreenViewModel
import com.mobilegame.spaceshooter.presentation.ui.screens.duelTutoScreen.SmartphoneEmulator

@Composable
fun SmartphonesFaceToFace(vm: TutoScreenViewModel) {
    Column(
        Modifier
            .fillMaxSize()
    ) {
        Box(
            Modifier
                .fillMaxWidth()
                .weight(vm.ui.generalLayout.paddVertical))
        Box(
            Modifier
                .fillMaxWidth()
                .weight(vm.ui.generalLayout.smartphoneWeight)
        ) {
//            SmartphoneEmulator(vm)
        }
        Box(
            Modifier
                .fillMaxWidth()
                .weight(vm.ui.generalLayout.paddMid))
        Box(
            Modifier
                .fillMaxWidth()
                .weight(vm.ui.generalLayout.smartphoneWeight)
        ) {
            SmartphoneEmulator(vm, vm.ui.smartphoneEmulator.idBottom)
        }
        Box(
            Modifier
                .fillMaxWidth()
                .weight(vm.ui.generalLayout.paddVertical))
    }
}
