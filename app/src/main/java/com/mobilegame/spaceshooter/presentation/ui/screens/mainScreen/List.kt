package com.mobilegame.spaceshooter.presentation.ui.screens.mainScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.mobilegame.spaceshooter.domain.model.screen.mainScreen.MainScreenViewModel
import com.mobilegame.spaceshooter.presentation.ui.navigation.Navigator
import com.mobilegame.spaceshooter.presentation.ui.screens.mainScreen.elements.buttons.BluetoothButton.BlueToothButton
import com.mobilegame.spaceshooter.presentation.ui.screens.mainScreen.elements.buttons.WifiButton.WifiButton
import com.mobilegame.spaceshooter.presentation.ui.screens.mainScreen.elements.buttons.WifiButton.WifiIcon.WifiIcon
import com.mobilegame.spaceshooter.presentation.ui.screens.utils.*
import com.mobilegame.spaceshooter.utils.extensions.alpha

@Composable
internal fun List(navigator: Navigator, vm: MainScreenViewModel) {
    PaddingComposable(
        horizontalPadding = 0.17F,
    ) {
        Box {
            AlignComposableToCenterStart {
                BlueToothButton(vm = vm, navigator = navigator)
            }
            AlignComposableToCenterEnd {
                WifiButton(vm = vm, navigator = navigator)
            }
        }
    }
}
