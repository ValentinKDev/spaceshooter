package com.mobilegame.spaceshooter.presentation.ui.screens.stats

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.mobilegame.spaceshooter.logic.model.screen.statsScreen.StatsScreenViewModel
import com.mobilegame.spaceshooter.utils.analyze.eLog

@Composable
fun StatsScreen(vm: StatsScreenViewModel = viewModel()) {
    val presentableStats by remember { vm.presentableStats }.collectAsState()

    LaunchedEffect(true) {
        eLog("StatsScreen", "StatsScreen launch")
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(vm.ui.colors.backgroundColor)
    ) {
        Column {
            Text(text = "wins : ${presentableStats.wins}")
            Text(text = "losses : ${presentableStats.losses}")
            Text(text = "highest Streak : ${presentableStats.highestStreakInfo.second} against : ${presentableStats.highestStreakInfo.first}")
            Text(text = "totalWinRate : ${presentableStats.totalWinRate}%")
            Text(text = "lastTenGamesWinRate : ${presentableStats.lastTenGamesWinRate}%")
            Text(text = "preferred Ship : ${presentableStats.mostUsedShipNameAndPercent.first} at : ${presentableStats.mostUsedShipNameAndPercent.second}%")
        }
    }
}