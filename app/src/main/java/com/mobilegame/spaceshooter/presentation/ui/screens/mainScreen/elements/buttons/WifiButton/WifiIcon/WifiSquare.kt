package com.mobilegame.spaceshooter.presentation.ui.screens.mainScreen.elements.buttons.WifiButton.WifiIcon

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import com.mobilegame.spaceshooter.logic.model.screen.mainScreen.MainScreenViewModel
import com.mobilegame.spaceshooter.presentation.theme.MyColor
import com.mobilegame.spaceshooter.presentation.ui.screens.utils.PaddingComposable

@Composable
fun WifiSquare(vm: MainScreenViewModel, content: @Composable () -> Unit) {
    Box(
        androidx.compose.ui.Modifier
            .size(vm.ui.buttonWifi.sizes.squareHeightDp)
            .border(
                width = vm.ui.buttonBluetooth.sizes.squareStrokeDp,
                shape = RoundedCornerShape(5.dp),
                color = MyColor.Platinium
            )
    ) {
        PaddingComposable(topPaddingRatio = 0.2F) {
            content.invoke()
        }
    }
}