package com.mobilegame.spaceshooter.presentation.ui.screens

import android.app.Application
import android.app.GameState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.mobilegame.spaceshooter.logic.model.navigation.Navigator
import com.mobilegame.spaceshooter.logic.model.navigation.PressureHandler
import com.mobilegame.spaceshooter.logic.model.screen.inGameScreens.GameResult
import com.mobilegame.spaceshooter.logic.model.screen.inGameScreens.ship.types.ShipType
import com.mobilegame.spaceshooter.logic.model.screen.tryAgainScreen.TryAgainStats
import com.mobilegame.spaceshooter.logic.repository.gameStats.GameStatsRepo
import com.mobilegame.spaceshooter.logic.repository.gameStats.MyDate
import com.mobilegame.spaceshooter.logic.uiHandler.template.TemplateUI
import com.mobilegame.spaceshooter.utils.analyze.eLog
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Creator(navigator: Navigator ,vm: CreatorViewModel = viewModel()) {
    LaunchedEffect(true) {
        eLog("Creator", "Creator launch")
    }

}

class CreatorViewModel(application: Application): AndroidViewModel(application) {
    val repo = GameStatsRepo(getApplication())

    init {
        viewModelScope.launch {
            repo.addGameResult(
                TryAgainStats(
                    wins = 1,
                    losses = 3,
                    streak = 1,
                    gameResult = GameResult.VICTORY,
                    shipName = ShipType.DEFAULT.info.name,
                    enemiesName = "TEST",
                    currentDate = MyDate.currentStr(),
                )
            )
        }
    }
}