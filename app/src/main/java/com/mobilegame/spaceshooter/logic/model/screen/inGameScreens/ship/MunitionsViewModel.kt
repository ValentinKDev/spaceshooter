package com.mobilegame.spaceshooter.logic.model.screen.inGameScreens.ship

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mobilegame.spaceshooter.logic.model.screen.inGameScreens.duelGameScreen.Shoot
import com.mobilegame.spaceshooter.logic.model.screen.inGameScreens.motions.MotionsViewModel
import com.mobilegame.spaceshooter.logic.model.screen.inGameScreens.ship.types.ChargedProjectileType
import com.mobilegame.spaceshooter.logic.model.screen.inGameScreens.ship.types.ShipType
import com.mobilegame.spaceshooter.utils.analyze.eLog
import com.mobilegame.spaceshooter.utils.analyze.iLog
import com.mobilegame.spaceshooter.utils.analyze.vLog
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class MunitionsViewModel(private val motionVM: MotionsViewModel, private val shipType: ShipType) : ViewModel() {
    val cTAG = "MunitionsViewModel"
    private val shootingTimeInterval = shipType.info.shootingTimeInterval
    private val ammoRecoveryTime = shipType.info.ammoRecoveryTime
    private val _magazineSize = MutableStateFlow<Int>(shipType.info.magazineSize)
    val magazineSize: StateFlow<Int> = _magazineSize.asStateFlow()
    private fun enoughAmmo(): Boolean = _magazineSize.value > 0
    private fun incrementAmmo() { if (_magazineSize.value < shipType.info.magazineSize) _magazineSize.value = _magazineSize.value + 1
         vLog("MunitionsVM::incementAmmo", "_ammo.value = ${_magazineSize.value}")
    }
    private fun decrementAmmo() { if (_magazineSize.value >= 0) { _magazineSize.value = _magazineSize.value - 1 } }
    private var ammoBeforeCharging = _magazineSize.value
    private fun updateAmmoBeforeCharging() {ammoBeforeCharging = _magazineSize.value}
    private var ammoCharged = 1
    private fun updateAmmoCharged() {
        val fTAG = "updateAmmoChargedTo()"
        ammoCharged = ammoBeforeCharging - magazineSize.value
        iLog(cTAG, "$fTAG ammoCharged $ammoCharged")
    }
    private fun resetAmmoCharged() { ammoCharged = 1 }
    private var recoveringJob: Job = viewModelScope.launch(Dispatchers.IO) {}
    private fun recoverAmmo() {
        if (recoveringJob.isCompleted && _magazineSize.value < shipType.info.magazineSize) {
            recoveringJob = viewModelScope.launch(Dispatchers.IO) { startAmmoRecovery() }
        }
    }
    suspend fun startAmmoRecovery() {
        while (_magazineSize.value < shipType.info.magazineSize) {
            delay(ammoRecoveryTime)
            incrementAmmo()
        }
    }
    suspend fun ammoConsumption() {
        while (screenIsPressed) {
            decrementAmmo()
            delay(ammoRecoveryTime)
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
        val fTAG = "shoot"
        if (firstShoot) {
                viewModelScope.launch() {
                    updateAmmoCharged()
                    screenIsPressed = false
                    if (_magazineSize.value != 0)
                        startShooting()
//                    else eLog("MunitionsVM::shoot", "${enoughAmmo()}")
                    eLog("MunitionsVM::shoot", "ammo ${_magazineSize.value}")
                    recoverAmmo()
                }
        }
    }

    private suspend fun startShooting() {
        val fTAG = "startShooting"
        var ammo = ammoCharged
        iLog(cTAG, "$fTAG charged projectil ${shipType.info.chargedProjectileType}")
        when (shipType.info.chargedProjectileType) {
            ChargedProjectileType.Instant -> {
                iLog(cTAG, "$fTAG behavior int ${ammoCharged}")
                val newShoot = Shoot.newFromUser(
                    ship = shipType,
                    vm = motionVM,
                    behavior = ammoCharged,
                    damage = shipType.info.damage * ammoCharged.toFloat(),
                )
                motionVM.addShoot(newShoot)
                delay(shootingTimeInterval)
            }
            ChargedProjectileType.Rafal -> {
                //todo : add additionnal projectiles following a rule copied on the original app
                //todo : issue about fast single press to boost the dps
                //solution could be to unable the shot after a period of time after the add shoot
                //corresponding to the ammoRecoveryTime
                while (ammo > 0) {
                    ammo -= 1
                    val newShoot = Shoot.newFromUser(shipType, motionVM)
                    motionVM.addShoot(newShoot)
                    delay(shootingTimeInterval)
                }
            }
        }
        //todo : ammocharged reset necessary ?
        resetAmmoCharged()
    }
}