package com.mobilegame.spaceshooter.presentation.ui.screens.mainScreen

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.unit.dp
import com.mobilegame.spaceshooter.domain.model.screen.mainScreen.MainScreenViewModel
import com.mobilegame.spaceshooter.presentation.theme.MyColor
import com.mobilegame.spaceshooter.presentation.ui.navigation.Navigator
import com.mobilegame.spaceshooter.presentation.ui.screens.mainScreen.buttons.BluetoothIcon
import com.mobilegame.spaceshooter.presentation.ui.screens.mainScreen.buttons.WifiIcon.WifiIcon
import com.mobilegame.spaceshooter.presentation.ui.screens.mainScreen.buttons.WifiIcon.WifiSquare
import com.mobilegame.spaceshooter.presentation.ui.screens.utils.*

@Composable
internal fun List(navigator: Navigator, vm: MainScreenViewModel) {
    PaddingComposable(
        horizontalPadding = 0.2F,
    ) {
        Box {
            AlignComposableToStart {
                CenterComposableVertically {
                    BluetoothIcon(vm)
                }
            }
            AlignComposableToEnd {
                CenterComposableVertically {
                    WifiSquare(vm)
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
