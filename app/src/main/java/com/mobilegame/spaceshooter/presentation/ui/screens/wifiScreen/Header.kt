package com.mobilegame.spaceshooter.presentation.ui.screens.wifiScreen

import android.preference.PreferenceActivity.Header
import androidx.compose.runtime.Composable
import com.mobilegame.spaceshooter.domain.model.screen.wifiScreen.WifiScreenViewModel
import com.mobilegame.spaceshooter.presentation.ui.navigation.Navigator
import com.mobilegame.spaceshooter.presentation.ui.screens.utils.PaddingComposable
import com.mobilegame.spaceshooter.presentation.ui.screens.utils.backButton.BackButton

@Composable
internal fun Header(vm: WifiScreenViewModel, navigator: Navigator) {
    BackButton(
        vm = vm.backButtonPressureNavigationVM,
        navigator = navigator,
        ui = vm.ui.backButton
    )
}