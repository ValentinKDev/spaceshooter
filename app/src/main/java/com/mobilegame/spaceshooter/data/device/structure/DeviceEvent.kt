package com.mobilegame.spaceshooter.data.device.structure

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import com.mobilegame.spaceshooter.data.connection.dto.EventMessageType
import com.mobilegame.spaceshooter.logic.model.navigation.Navigator
import com.mobilegame.spaceshooter.logic.model.screen.inGameScreens.GameResult
import com.mobilegame.spaceshooter.logic.model.screen.inGameScreens.duelGameScreen.Shoot
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class DeviceEvent {
//    var routeFlow: MutableSharedFlow<String> = MutableSharedFlow()

    var projectileFlow: MutableSharedFlow<Shoot> = MutableSharedFlow()
//    var projectileFlow: MutableStateFlow<Shoot> = MutableStateFlow(Shoot.UNDEFINED)
//    val eventTypeFlow: MutableSharedFlow<EventMessageType> = MutableSharedFlow()
//    var producingProjectile: MutableSharedFlow<Shoot> = MutableSharedFlow()
//var gameResult: MutableSharedFlow<GameResult> = MutableSharedFlow()
    var gameResult: MutableStateFlow<GameResult> = MutableStateFlow(GameResult.OnGoing)
    val hitStateFlow = MutableSharedFlow<Shoot>()
//    var _gameResult: StateFlow<GameResult> = gameResult.
//    var nav: MutableSharedFlow<Boolean> = MutableSharedFlow()
    var nav: Navigator = Navigator()
//    var gameOnPause: MutableSharedFlow<Boolean> = MutableSharedFlow()
//    var gameOnPause: MutableStateFlow<Boolean> = mutableSharedFlow(false)
}
