package com.mobilegame.spaceshooter.presentation.ui.screens.mainScreen

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.lifecycle.viewmodel.compose.viewModel
import com.mobilegame.spaceshooter.logic.model.screen.Screens
import com.mobilegame.spaceshooter.logic.model.screen.mainScreen.MainScreenViewModel
import com.mobilegame.spaceshooter.presentation.ui.navigation.Navigator
import com.mobilegame.spaceshooter.presentation.ui.template.TemplateWithoutBand
import com.mobilegame.spaceshooter.utils.analyze.eLog

@Composable
fun MainScreen(navigator: Navigator, vm: MainScreenViewModel = viewModel()) {
    LaunchedEffect(true) {
        eLog("MainScreen", "start")
    }

    TemplateWithoutBand(
        navigator = navigator,
        backNav = Screens.MainScreen.backNav,
        header = { MainScreenHeader(navigator, vm) },
        emptySpace = { MainScreenBody(navigator, vm) },
    )
}
