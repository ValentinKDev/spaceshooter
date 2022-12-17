package com.mobilegame.spaceshooter.presentation.ui.screens.mainScreen

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import com.mobilegame.spaceshooter.domain.model.screen.mainScreen.MainScreenViewModel
import com.mobilegame.spaceshooter.presentation.ui.navigation.Navigator
import com.mobilegame.spaceshooter.presentation.ui.screens.mainScreen.buttons.TutoButton
import com.mobilegame.spaceshooter.presentation.ui.screens.utils.AlignComposableToEnd
import com.mobilegame.spaceshooter.presentation.ui.screens.utils.AlignComposableToStart
import com.mobilegame.spaceshooter.presentation.ui.screens.utils.CenterComposableVertically
import com.mobilegame.spaceshooter.presentation.ui.screens.utils.PaddingComposable

@Composable
internal fun Header(navigator: Navigator, vm: MainScreenViewModel) {
    PaddingComposable(
        startPaddingRatio = vm.ui.header.ratios.textPaddingStart,
        endPaddingRatio = vm.ui.header.ratios.buttonPaddingEnd,
        topPaddingRatio = 0F,
        bottomPaddingRatio = 0F,
    ) {
        Box {
            AlignComposableToStart {
                Text(
                    text = vm.ui.header.text.message,
                    color = vm.ui.header.colors.text,
                )
            }

            AlignComposableToEnd {
                CenterComposableVertically {
                    TutoButton(vm)
                }
            }
        }
    }
}