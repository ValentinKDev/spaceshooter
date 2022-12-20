package com.mobilegame.spaceshooter.presentation.ui.screens.mainScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.lifecycle.viewmodel.compose.viewModel
import com.mobilegame.spaceshooter.domain.model.screen.mainScreen.MainScreenViewModel
import com.mobilegame.spaceshooter.presentation.ui.navigation.Navigator
import com.mobilegame.spaceshooter.utils.analyze.verbalLog
import com.mobilegame.spaceshooter.utils.analyze.wLog

@Composable
fun MainScreen(navigator: Navigator, vm: MainScreenViewModel = viewModel()) {
    val density = LocalDensity.current
    val densityF = LocalContext.current.resources.displayMetrics.density
    val densityDpi = LocalContext.current.resources.displayMetrics.densityDpi
    LaunchedEffect(true) {
        verbalLog("MainScreen", "start")
        wLog("MainScreen", "densityF $densityF")
        wLog("MainScreen", "density $density")
        wLog("MainScreen", "densityDpi $densityDpi")
    }

    Box {
        Column(Modifier.fillMaxSize()) {
            Box(
                Modifier
                    .fillMaxWidth()
                    .weight(vm.ui.header.ratios.heightWeight)
            ) {
                Header(navigator, vm)
            }
            Box(
                Modifier
                    .fillMaxWidth()
                    .weight(vm.ui.delimiter.ratios.heightWeight)
                    .background(vm.ui.delimiter.colors.background)
            ) {
                Delimiter()
            }
            Box(
                Modifier
                    .fillMaxWidth()
                    .weight(vm.ui.list.ratios.heightWeight)
            ) {
                List(navigator, vm)
            }
        }
        Instructions(vm)
    }
}
