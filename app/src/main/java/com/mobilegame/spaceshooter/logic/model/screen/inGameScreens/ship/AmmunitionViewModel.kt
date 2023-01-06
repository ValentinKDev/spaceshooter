package com.mobilegame.spaceshooter.logic.model.screen.inGameScreens.ship

import androidx.compose.ui.geometry.Offset
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mobilegame.spaceshooter.logic.model.screen.inGameScreens.duelGameScreen.Shoot
import com.mobilegame.spaceshooter.logic.model.screen.inGameScreens.motions.MotionsViewModel
import com.mobilegame.spaceshooter.logic.uiHandler.SpaceShip.SpaceShipIconUI
import com.mobilegame.spaceshooter.utils.analyze.eLog
import com.mobilegame.spaceshooter.utils.analyze.iLog
import com.mobilegame.spaceshooter.utils.extensions.notZero
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class AmmunitionViewModel(private val motionVM: MotionsViewModel, private val shipType: ShipType) : ViewModel() {

    private val shootingTimeInterval = 120L
    private val ammunitionTimeInterval = 450L
    private val _ammunition = MutableStateFlow<Int>(10)
    val ammunition: StateFlow<Int> = _ammunition.asStateFlow()
    fun incrementAmmo() { if (_ammunition.value < 10) _ammunition.value = _ammunition.value + 1 }
    fun decrementAmmo() { if (_ammunition.value > 0) { _ammunition.value = _ammunition.value - 1 } }
    private var ammoBeforeCharging = _ammunition.value
    private fun updateAmmoBeforeCharging() {ammoBeforeCharging = _ammunition.value}
    private var recoveringJob: Job = viewModelScope.launch(Dispatchers.IO){}
    suspend fun startAmmoRecovery() {
        while (_ammunition.value < 10) {
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
    private var startChargingTime = 0L
    private var endChargingTime = 0L
    private var chargingTime = 0L
    private fun startChargingShoot() { startChargingTime = System.currentTimeMillis() }
    private fun stopChargingTime() { endChargingTime = System.currentTimeMillis() }
    fun chargingShoot() {
        updateAmmoBeforeCharging()
        startChargingShoot()
        recoveringJob.cancel()
        screenIsPressed = true
        viewModelScope.launch(Dispatchers.IO) { ammoConsumption() }
    }
    fun shoot() {
        if (startChargingTime.notZero()) {
            eLog("SpaceShipVM::shoot", "before ammo chargeing $ammoBeforeCharging")
            screenIsPressed = false
            stopChargingTime()
            recoveringJob = viewModelScope.launch(Dispatchers.IO) { startAmmoRecovery() }
//            chargingTime = endChargingTime - startChargingTime
//            var ammoCharged = (chargingTime.toFloat() / ammunitionTimeInterval).toInt() + 1
//            if (ammoCharged >= ammoBeforeCharging) ammoCharged = ammoBeforeCharging
            val ammoCharged = ammoBeforeCharging - _ammunition.value
            startShooting(ammoCharged)
            eLog("SpaceShipVM::shoot", "ammo charged $ammoCharged")
        }
    }


    private fun startShooting(ammoCharged: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            var ammo = ammoCharged
            while (ammo >= 0) {
                ammo -= 1
                val newShoot = Shoot(
                    type = shipType,
                    from = ShipOrigin.User,
                    vector = motionVM.getShootVector(),
                    offsetDp = motionVM.shipPosition.value.copy()
                )
                motionVM.addShoot(newShoot)
                delay(shootingTimeInterval)
            }
        }
    }

    fun getAmmunitionOffset(ui: SpaceShipIconUI, n: Int): Offset = when (n) {
        1 -> ui.ammunition.m5
        2 -> ui.ammunition.m6
        3 -> ui.ammunition.m4
        4 -> ui.ammunition.m7
        5 -> ui.ammunition.m3
        6 -> ui.ammunition.m8
        7 -> ui.ammunition.m2
        8 -> ui.ammunition.m9
        9 -> ui.ammunition.m1
        10 -> ui.ammunition.m10
        else -> {
            eLog("SpaceShipVM::getAmmunitionOffset", "ERROR getMunition(ui, --> ${ammunition.value})")
            Offset.Zero
        }
    }
}