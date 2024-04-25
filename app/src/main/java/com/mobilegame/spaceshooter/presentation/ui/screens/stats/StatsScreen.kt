package com.mobilegame.spaceshooter.presentation.ui.screens.stats

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.mobilegame.spaceshooter.logic.model.screen.statsScreen.StatsScreenViewModel
import com.mobilegame.spaceshooter.utils.analyze.eLog

@Composable
fun StatsScreen(vm: StatsScreenViewModel = viewModel()) {
    LaunchedEffect(true) {
        eLog("StatsScreen", "StatsScreen launch")
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(vm.ui.colors.backgroundColor)
    ) {

    }
}