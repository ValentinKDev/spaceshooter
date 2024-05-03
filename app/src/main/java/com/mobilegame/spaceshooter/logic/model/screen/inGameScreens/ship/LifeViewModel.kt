package com.mobilegame.spaceshooter.logic.model.screen.inGameScreens.ship

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mobilegame.spaceshooter.data.device.Device
import com.mobilegame.spaceshooter.logic.model.navigation.Navigator
import com.mobilegame.spaceshooter.logic.model.screen.inGameScreens.GameResult
import com.mobilegame.spaceshooter.logic.model.screen.inGameScreens.duelGameScreen.LooseInfo
import com.mobilegame.spaceshooter.logic.model.screen.inGameScreens.duelGameScreen.Shoot
import com.mobilegame.spaceshooter.logic.model.screen.inGameScreens.motions.MotionsViewModel
import com.mobilegame.spaceshooter.logic.model.screen.inGameScreens.ship.types.ShipType
import com.mobilegame.spaceshooter.logic.model.screen.tryAgainScreen.TryAgainStats
import com.mobilegame.spaceshooter.logic.repository.device.DeviceEventRepo
import com.mobilegame.spaceshooter.logic.repository.gameStats.MyDate
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Date

class LifeViewModel(
    private val motionVM: MotionsViewModel,
    type: ShipType,
//    private val tryAgainStats: TryAgainStats
): ViewModel() {
    val TAG = "LifeViewModel"
    var dead: Boolean = false
    val lifeStarter: Float = type.info.life
    private val _lifeRatio = MutableStateFlow(1F)
    val lifeRatio: StateFlow<Float> = _lifeRatio.asStateFlow()
    private var currentLife: Float = lifeStarter
    private var nav: Navigator? = null


    private suspend fun lifeUpdate(projectile: Shoot) {
        if (dead == false) {
//            currentLife -= projectile.damage
            currentLife -= 150F
            if (currentLife.toInt() > 0) {
                _lifeRatio.emit( currentLife / lifeStarter )
            } else {
                dead = true
                _lifeRatio.emit(0F)
                endGame(projectile)
            }
        }
        Log.i(TAG, "lifeUpdate: current life $currentLife")
        Log.i(TAG, "lifeUpdate: ratio life ${_lifeRatio.value}")
    }
    private suspend fun endGame(projectile: Shoot) {
        Log.i(TAG, "endGame: ")
        Log.i(TAG, "endGame projectile shooter ip: ${projectile.shooterIp}")
        Log.i(TAG, "endGame visible device ip :${Device.wifi.listConnectedDevice.map { it.ip }.first()}")
        Log.i(TAG, "endGame: ${Device.wifi.listConnectedDevice.find { it.ip == projectile.shooterIp }?.ip}")
        Log.i(TAG, "endGame: ${Device.wifi.listConnectedDevice.find { it.ip == projectile.shooterIp }?.name}")
        Device.wifi.visibleDevices.value.find { it.ip == projectile.shooterIp }?.let {_shooterDevice ->
            val currentDate: String = MyDate.currentStr()
            Log.e(TAG, "endGame: \n\n\n\ndate $currentDate")

            DeviceEventRepo().sendDeadUser(
                LooseInfo(
                    shooterIp = projectile.shooterIp,
                    shooterName = _shooterDevice.name,
                    deadPlayerName = Device.data.name ?: "ERROR NO NAME",
                    deadPlayerIp = Device.wifi.inetAddress,
                    exactMoment = currentDate
                )
            )
        } ?: { Log.e(TAG, "endGame: Shooter name not found in visibleDevicesList", )}
        Device.event.gameResult.emit(GameResult.DEFEAT)
    }
    init {
        viewModelScope.launch(Dispatchers.IO) {
            val hitsJob = async { listenToTheHits() }
//            val gameResult = async { listenToGameResult() }
        }
    }

    private suspend fun listenToTheHits() {
        motionVM._hitStateFlow.collect {
            Log.i(TAG, "listenToTheHits: collect hit")
            lifeUpdate(it)
        }
    }

    fun initNav(navigator: Navigator) {
        nav = navigator
    }
}