package com.mobilegame.spaceshooter.logic.model.screen.inGameScreens.ship

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mobilegame.spaceshooter.data.device.Device
import com.mobilegame.spaceshooter.logic.model.screen.inGameScreens.duelGameScreen.Shoot
import com.mobilegame.spaceshooter.logic.model.screen.inGameScreens.motions.Motions
import com.mobilegame.spaceshooter.logic.model.screen.inGameScreens.motions.MotionsViewModel
import com.mobilegame.spaceshooter.logic.model.screen.inGameScreens.ship.types.ChargedProjectileType
import com.mobilegame.spaceshooter.logic.model.screen.inGameScreens.ship.types.ShipType
import com.mobilegame.spaceshooter.utils.analyze.eLog
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class MunitionsViewModel(private val motionVM: MotionsViewModel, private val shipType: ShipType) : ViewModel() {
    val TAG = "MunitionsViewModel"

    private val magazineFlow: MutableSharedFlow<Int> = MutableSharedFlow()
    private val shootingTimeInterval = shipType.info.shootingTimeInterval
    private val ammoRecoveryTime = shipType.info.ammoRecoveryTime
    private val ammoRecoveryTimeMinusShootingTimeInterval = ammoRecoveryTime - shootingTimeInterval
    private val magazineMaxSize = shipType.info.magazineSize
    var magazineSize: Int = magazineMaxSize
    private var canShoot = true
    private val _hasShoot = MutableStateFlow("")
    val hasShoot: StateFlow<String> = _hasShoot.asStateFlow()
    private var recoveringJob: Job = viewModelScope.launch(Dispatchers.IO) {}
    private val shipState = MutableStateFlow(State.AbleToShoot)
    private val _chargingAnimation = MutableStateFlow(false)
    val chargingAnimation: StateFlow<Boolean> = _chargingAnimation.asStateFlow()

    private enum class State {
        FirstShootNotDone,
        ChargingProjectile,
        ShootingProjectile,
        UnableToShoot,
        AbleToShoot,
        RecoveringAmmunition,
        MagazineFull,
        MagazineEmpty,
    }

    private var screenIsPressed = false
    private var firstShootDone = false
    private var ammoBeforeCharging = magazineSize
    private var ammoCharged = 0
    private var isShootingTimeIntervalOver = false
    private fun incrementAmmo() { if (magazineSize < shipType.info.magazineSize) magazineSize += 1 }
    private fun decrementAmmo() { if (magazineSize >= 0) { magazineSize -= 1 } }
    private fun magazineIsNotEmpty(): Boolean = magazineSize >= 0
    private fun shipCanShoot(): Boolean {
        return if (firstShootDone
            && shipState.value != State.MagazineEmpty
            && shipState.value != State.UnableToShoot ) true
        else {
            if (!firstShootDone) firstShootDone = true
            false
        }
    }
    private fun updateShipStateTo(state: State) {shipState.value = state}
    private fun isStateFirstShootNotDone(): Boolean = shipState.value == State.FirstShootNotDone
    private fun updateAmmoBeforeCharging() {ammoBeforeCharging = magazineSize}
    private fun updateAmmoCharged() {
        val diff = ammoBeforeCharging - magazineSize
        when (diff) {
            0 -> {
                ammoCharged = 1
                decrementAmmo()
            }
            in 1..magazineMaxSize -> { ammoCharged = diff }
            magazineMaxSize + 1 -> { ammoCharged = magazineMaxSize}
            else -> {
                ammoCharged = 1
            }
        }
    }
    init {
        Log.i(TAG, "init: ")
        shipLogic()
    }

    private fun shipLogic() = viewModelScope.launch(Dispatchers.Main) {
        var action: Deferred<Unit>? = null
        var anim: Deferred<Unit>? = null
        shipState.collect {
            action?.cancel()
            anim?.cancel()
            _chargingAnimation.value = false
            when (it) {
                State.FirstShootNotDone -> {}
                State.ChargingProjectile -> {
                    updateAmmoBeforeCharging()
                    action = async {
                        ammoConsumption()
                    }
                    anim = async {
                        chargingAnimation()
                    }
                }
                State.ShootingProjectile -> {
                    action?.cancel()
                    updateAmmoCharged()
                    //todo : this protection to avoid shooting while empty mag does reset the time for ammo recovery, find an other way
                    if (ammoCharged > 1 || (ammoCharged == 1 && magazineIsNotEmpty())) createProjectile()
                    updateShipStateTo(State.UnableToShoot)
                }
                State.UnableToShoot -> {
                    isShootingTimeIntervalOver = false
                    delay(shootingTimeInterval)
                    isShootingTimeIntervalOver = true
                    updateShipStateTo(State.RecoveringAmmunition)
                }
                State.AbleToShoot -> { }
                State.RecoveringAmmunition -> {
                    action = async {
                    delay(ammoRecoveryTimeMinusShootingTimeInterval)
                    incrementAmmo()
                    while (magazineSize < shipType.info.magazineSize) {
                        delay(ammoRecoveryTime)
                        incrementAmmo()
                    }
                    if (magazineSize == magazineMaxSize) updateShipStateTo(State.MagazineFull)
                    }
                }
                State.MagazineFull -> { updateShipStateTo(State.AbleToShoot) }
                State.MagazineEmpty -> { startAmmoRecovering() }
            }
        }
    }

    fun screenIsNotPressed() {
        screenIsPressed = false
        if (shipCanShoot()) {
            updateShipStateTo(State.ShootingProjectile)
        } else if (isStateFirstShootNotDone()) {
            updateShipStateTo(State.AbleToShoot)
        }
    }

    fun screenIsPressed() {
        screenIsPressed = true
        if (shipCanShoot()) {
            updateShipStateTo(State.ChargingProjectile)
        }
    }

    private suspend fun updateMagazineFlowAndBlockShoot(newShoot: Shoot) {
        Device.event.projectileFlow.emit(newShoot)
        canShoot = false
    }

    private fun startAmmoRecovering() {
//        if ( _magazineSize.value <= shipType.info.magazineSize) {
        if ( magazineSize <= shipType.info.magazineSize) {
            recoveringJob = viewModelScope.launch(Dispatchers.IO) { recoverAmmo() }
        }
    }
    suspend fun recoverAmmo() {
        delay(shootingTimeInterval)
        canShoot = true
        delay(ammoRecoveryTimeMinusShootingTimeInterval)
        incrementAmmo()
        while (magazineSize < shipType.info.magazineSize) {
            delay(ammoRecoveryTime)
            incrementAmmo()
        }
    }
    suspend fun chargingAnimation() {
        var temp = magazineSize
        var i = 0
        while (screenIsPressed) {
            if (magazineSize < temp) {
                i++
                if (i > 1) {
                    _chargingAnimation.value = true
                    delay(100L)
                    _chargingAnimation.value = false
                    delay(ammoRecoveryTime - 100L)
                }
            } else { delay(10L) }
        }
    }
    suspend fun ammoConsumption() {
//        var i = 0
        while (screenIsPressed) {
            decrementAmmo()
            delay(ammoRecoveryTime)
//            i++
        }
    }
    private suspend fun createProjectile() {
//        Log.i(TAG, "createProjectile: start")
        var ammo = ammoCharged
        when (shipType.info.chargedProjectileType) {
            ChargedProjectileType.Instant -> {
                val newShoot = Shoot.newFromUser(
                    type = shipType,
                    vm = motionVM,
                    behavior = ammoCharged,
                    damage = shipType.info.damage * ammoCharged.toFloat(),
                )
//                updateMagazineFlowAndBlockShoot(newShoot)
//                Device.event.projectileFlow.emit(newShoot)
                Device.event.projectileFlow.emit(newShoot)
//                Device.event.projectileFlow.value = newShoot
//                motionVM.addShoot(newShoot)
                delay(shootingTimeInterval)
            }
            ChargedProjectileType.Rafal -> {
                //todo : add additionnal projectiles following a rule copied on the original app
                //todo : issue about fast single press to boost the dps
                //solution could be to unable the shot after a period of time after the add shoot
                //corresponding to the ammoRecoveryTime
                while (ammo > 0) {
                    ammo -= 1
                    val newShoot = Shoot.newFromUser(
                        type = shipType,
                        vm = motionVM,
                    )
                    Device.event.projectileFlow.emit(newShoot)
                    canShoot = false
                    delay(shootingTimeInterval)
                }
            }
        }
//        Log.i(TAG, "createProjectile: finish")
    }
}