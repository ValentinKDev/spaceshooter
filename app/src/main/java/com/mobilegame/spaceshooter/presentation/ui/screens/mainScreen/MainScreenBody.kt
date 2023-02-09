package com.mobilegame.spaceshooter.presentation.ui.screens.mainScreen

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import com.mobilegame.spaceshooter.logic.model.screen.mainScreen.MainScreenViewModel
import com.mobilegame.spaceshooter.logic.model.navigation.Navigator
import com.mobilegame.spaceshooter.presentation.ui.screens.mainScreen.elements.buttons.BluetoothButton.BlueToothButton
import com.mobilegame.spaceshooter.presentation.ui.screens.mainScreen.elements.buttons.WifiButton.WifiButton
import com.mobilegame.spaceshooter.presentation.ui.screens.utils.*

@Composable
internal fun MainScreenBody(navigator: Navigator, vm: MainScreenViewModel) {
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
    Instructions(vm)
}
