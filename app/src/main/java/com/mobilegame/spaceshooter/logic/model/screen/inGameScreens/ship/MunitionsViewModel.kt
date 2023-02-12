package com.mobilegame.spaceshooter.logic.model.screen.inGameScreens.ship

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mobilegame.spaceshooter.logic.model.screen.inGameScreens.duelGameScreen.Shoot
import com.mobilegame.spaceshooter.logic.model.screen.inGameScreens.motions.MotionsViewModel
import com.mobilegame.spaceshooter.logic.model.screen.inGameScreens.ship.types.ShipType
import com.mobilegame.spaceshooter.utils.analyze.eLog
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class MunitionsViewModel(private val motionVM: MotionsViewModel, private val shipType: ShipType) : ViewModel() {
    private val shootingTimeInterval = 120L
    private val ammunitionTimeInterval = 450L
    private val _magazineSize = MutableStateFlow<Int>(shipType.info.magazineSize)
    val magazineSize: StateFlow<Int> = _magazineSize.asStateFlow()
    private fun enoughAmmo(): Boolean = _magazineSize.value > 0
    private fun incrementAmmo() { if (_magazineSize.value < shipType.info.magazineSize) _magazineSize.value = _magazineSize.value + 1 ; eLog("MunitionsVM::incementAmmo", "_ammo.value = ${_magazineSize.value}")

    }
    private fun decrementAmmo() { if (_magazineSize.value >= 0) { _magazineSize.value = _magazineSize.value - 1 } }
    private var ammoBeforeCharging = _magazineSize.value
    private fun updateAmmoBeforeCharging() {ammoBeforeCharging = _magazineSize.value}
    private var recoveringJob: Job = viewModelScope.launch(Dispatchers.IO) {}
    private fun recoverAmmo() {
        if (recoveringJob.isCompleted && _magazineSize.value < shipType.info.magazineSize) {
            recoveringJob = viewModelScope.launch(Dispatchers.IO) { startAmmoRecovery() }
        }
    }
    suspend fun startAmmoRecovery() {
        while (_magazineSize.value < shipType.info.magazineSize) {
            delay(ammunitionTimeInterval)
            incrementAmmo()
        }
    }
    suspend fun ammoConsumption() {
        while (screenIsPressed) {
            decrementAmmo()
            delay(ammunitionTimeInterval)
        }
    }
    private var screenIsPressed = false
    private var firstShoot = false
    fun chargingShoot() {
        if (_magazineSize.value > 0) {
            firstShoot = true
            updateAmmoBeforeCharging()
            recoveringJob.cancel()
            screenIsPressed = true
            viewModelScope.launch(Dispatchers.IO) { ammoConsumption() }
        }
    }

    fun shoot() {
        if (firstShoot) {
                viewModelScope.launch() {
                    screenIsPressed = false
                    if (_magazineSize.value != 0)
                        startShooting(1)
                    else eLog("MunitionsVM::shoot", "${enoughAmmo()}")
                    eLog("MunitionsVM::shoot", "ammo ${_magazineSize.value}")
                    recoverAmmo()
                }
        }
    }

    private suspend fun startShooting(ammoCharged: Int) {
        var ammo = ammoCharged
        while (ammo > 0) {
            ammo -= 1
            val newShoot = Shoot(
                type = shipType,
                from = ShipOrigin.User,
                motion = motionVM.motion.value,
                vector = motionVM.getShootVector(),
                offsetDp = motionVM.getShipTopCenter()
            )
            motionVM.addShoot(newShoot)
            delay(shootingTimeInterval)
        }
    }

}