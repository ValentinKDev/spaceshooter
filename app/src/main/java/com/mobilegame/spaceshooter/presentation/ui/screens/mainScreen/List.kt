package com.mobilegame.spaceshooter.presentation.ui.screens.mainScreen

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import com.mobilegame.spaceshooter.domain.model.screen.mainScreen.MainScreenViewModel
import com.mobilegame.spaceshooter.presentation.ui.navigation.Navigator
import com.mobilegame.spaceshooter.presentation.ui.screens.mainScreen.buttons.BluetoothButton.BlueToothButton
import com.mobilegame.spaceshooter.presentation.ui.screens.mainScreen.buttons.WifiButton.WifiButton
import com.mobilegame.spaceshooter.presentation.ui.screens.mainScreen.buttons.WifiButton.WifiIcon.WifiIcon
import com.mobilegame.spaceshooter.presentation.ui.screens.utils.*

@Composable
internal fun List(navigator: Navigator, vm: MainScreenViewModel) {
    PaddingComposable(
        horizontalPadding = 0.2F,
    ) {
        Box {
            AlignComposableToStart {
                CenterComposableVertically {
                    BlueToothButton(navigator, vm)
                }
            }
            AlignComposableToEnd {
                CenterComposableVertically {
                    WifiButton(vm, navigator)
                }
            }
        }
    }
    PaddingComposable(
        topPaddingRatio = 0.4F,
        endPaddingRatio = 0.2F,
        bottomPaddingRatio = 0.1F,
    ) {
        AlignComposableToEnd {
            WifiIcon(vm)
        }
    }
}
