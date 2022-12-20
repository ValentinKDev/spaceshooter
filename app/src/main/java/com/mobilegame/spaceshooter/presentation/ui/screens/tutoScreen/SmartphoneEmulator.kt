package com.mobilegame.spaceshooter.presentation.ui.screens.tutoScreen

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.mobilegame.spaceshooter.domain.model.screen.tutoScreen.TutoScreenViewModel
import com.mobilegame.spaceshooter.presentation.theme.MyColor
import com.mobilegame.spaceshooter.presentation.ui.screens.utils.PaddingComposable

@Composable
fun SmartphoneEmulator(vm: TutoScreenViewModel) {
    PaddingComposable(
        horizontalPadding = vm.ui.generalLayout.horizontalPadd
    ) {
        Box(
            Modifier
                .fillMaxSize()
                .border(
                    width = vm.ui.smartphoneEmulator.sizes.borderDp,
                    shape = RoundedCornerShape(4.dp),
                    color = MyColor.Platinium
                )
        ) {
            SmartphoneScreen(vm)
        }
    }
}
