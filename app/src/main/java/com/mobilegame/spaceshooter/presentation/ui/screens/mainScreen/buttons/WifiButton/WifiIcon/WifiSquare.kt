package com.mobilegame.spaceshooter.presentation.ui.screens.mainScreen.buttons.WifiButton.WifiIcon

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import com.mobilegame.spaceshooter.domain.model.screen.mainScreen.MainScreenViewModel
import com.mobilegame.spaceshooter.presentation.theme.MyColor

@Composable
fun WifiSquare(vm: MainScreenViewModel) {
    Box(
        androidx.compose.ui.Modifier
            .size(vm.ui.buttonWifi.sizes.squareHeightDp)
            .border(
                width = vm.ui.buttonBluetooth.sizes.squareStrokeDp,
                shape = RoundedCornerShape(5.dp),
                color = MyColor.Platinium
            )
    )
}