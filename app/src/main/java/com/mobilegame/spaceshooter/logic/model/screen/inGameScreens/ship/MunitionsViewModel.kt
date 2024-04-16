package com.mobilegame.spaceshooter.logic.model.screen.inGameScreens.ship

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mobilegame.spaceshooter.data.device.Device
import com.mobilegame.spaceshooter.logic.model.screen.inGameScreens.duelGameScreen.Shoot
import com.mobilegame.spaceshooter.logic.model.screen.inGameScreens.motions.MotionsViewModel
import com.mobilegame.spaceshooter.logic.model.screen.inGameScreens.ship.types.ChargedProjectileType
import com.mobilegame.spaceshooter.logic.model.screen.inGameScreens.ship.types.ShipType
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
    private val magazine = magazineMaxSize
    private var canShoot = true
    private val _magazineSize = MutableStateFlow<Int>(shipType.info.magazineSize)
    val magazineSize: StateFlow<Int> = _magazineSize.asStateFlow()
    private var recoveringJob: Job = viewModelScope.launch(Dispatchers.IO) {}
    private var actionJob: Job = viewModelScope.launch(Dispatchers.IO) {}
//    private val shipState = MutableStateFlow(State.FirstShootNotDone)
    private val shipState = MutableStateFlow(State.AbleToShoot)
    private fun updateShipStateTo(state: State) {shipState.value = state}

    private enum class State {
        FirstShootNotDone,
        ChargingProjectile,
        ShootingProjectile,
        //        Shoot,
        UnableToShoot,
        AbleToShoot,
        RecoveringAmmunition,
//        OutOfAmmunition,
        MagazineFull,
        MagazineEmpty,
    }

    private var screenIsPressed = false
    private var firstShootDone = false
    private var ammoBeforeCharging = _magazineSize.value
    private var ammoCharged = 0
    private var isShootingTimeIntervalOver = false
    private fun incrementAmmo() { if (_magazineSize.value < shipType.info.magazineSize) _magazineSize.value += 1 }
    private fun decrementAmmo() { if (_magazineSize.value >= 0) { _magazineSize.value -= 1 } }
    private fun magazineIsNotEmpty(): Boolean = magazineSize.value >= 0
    private fun shipCanShoot(): Boolean {
//        return if (shipState.value == State.AbleToShoot
//            || shipState.value == State.ChargingProjectile
//            || shipState.value == State.MagazineFull
//            || shipState.value == State.RecoveringAmmunition) true
//        else { Log.e(TAG, "shipCanShoot: false", ) ; false }
        return if (shipState.value != State.MagazineEmpty && shipState.value != State.UnableToShoot) true
//        return if (magazineIsNotEmpty() && shipState.value != State.UnableToShoot) true
        else { Log.e(TAG, "shipCanShoot: false", ) ; false }
    }
    private fun isStateFirstShootNotDone(): Boolean = shipState.value == State.FirstShootNotDone
    private fun updateAmmoBeforeCharging() {ammoBeforeCharging = _magazineSize.value}
    private fun updateAmmoCharged() {
        val diff = ammoBeforeCharging - magazineSize.value
        when (diff) {
            0 -> {
                ammoCharged = 1
                decrementAmmo()
            }
            in 1..magazineMaxSize -> { ammoCharged = diff }
            magazineMaxSize + 1 -> { ammoCharged = magazineMaxSize}
            else -> { ammoCharged = 1; Log.e( TAG, "updateAmmoCharged: ERROR ammoBeforeCharging $ammoBeforeCharging - magazineSize ${magazineSize.value} = $diff", ) }
        }
    }
    init {
        Log.i(TAG, "init: ")
        shipLogic()
    }

    private fun shipLogic() = viewModelScope.launch(Dispatchers.Main) {
        var action: Deferred<Unit>? = null
        shipState.collect {
            Log.v(TAG, "collect state: ${shipState.value}")
            action?.cancel()
            when (it) {
                State.FirstShootNotDone -> {}
                State.ChargingProjectile -> {
                    updateAmmoBeforeCharging()
                    action = async {
                        ammoConsumption()
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
                    while (_magazineSize.value < shipType.info.magazineSize) {
                        delay(ammoRecoveryTime)
                        incrementAmmo()
                    }
                    if (magazineSize.value == magazineMaxSize) updateShipStateTo(State.MagazineFull)
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
        if ( _magazineSize.value <= shipType.info.magazineSize) {
            recoveringJob = viewModelScope.launch(Dispatchers.IO) { recoverAmmo() }
        }
    }
    suspend fun recoverAmmo() {
        delay(shootingTimeInterval)
        canShoot = true
        delay(ammoRecoveryTimeMinusShootingTimeInterval)
        incrementAmmo()
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

//    suspend fun chargingShoot() {
//        if (canShoot && magazineIsNotEmpty()) {
//            actionJob.cancel()
//            recoveringJob.cancel()
//            actionJob = viewModelScope.launch(Dispatchers.IO) {
//                firstShootDone = true
//                updateAmmoBeforeCharging()
//                screenIsPressed = true
//                ammoConsumption()
//            }
//        }
//    }
//    suspend fun shoot() {
//        if (canShoot && magazineIsNotEmpty() && firstShootDone) {
//            actionJob.cancel()
//            recoveringJob.cancel()
//            actionJob = viewModelScope.launch(Dispatchers.IO) {
//                updateAmmoCharged()
//                screenIsPressed = false
//                createProjectile()
//                startAmmoRecovering()
//            }
//        }
//    }

    private suspend fun createProjectile() {
        var ammo = ammoCharged
        when (shipType.info.chargedProjectileType) {
            ChargedProjectileType.Instant -> {
                val newShoot = Shoot.newFromUser(
                    type = shipType,
                    vm = motionVM,
                    behavior = ammoCharged,
                    damage = shipType.info.damage * ammoCharged.toFloat(),
                )
                Log.i(TAG, "createProjectile: ammocharged $ammoCharged")
//                updateMagazineFlowAndBlockShoot(newShoot)
                Device.event.projectileFlow.emit(newShoot)
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
                    updateMagazineFlowAndBlockShoot(newShoot)
                    delay(shootingTimeInterval)
                }
            }
        }
    }

//    private val magazineFlow: MutableSharedFlow<Int> = MutableSharedFlow()
//    private val shootingTimeInterval = shipType.info.shootingTimeInterval
//    private val ammoRecoveryTime = shipType.info.ammoRecoveryTime
//    private val ammoRecoveryTimeMinusShootingTimeInterval = ammoRecoveryTime - shootingTimeInterval
//    private val magazineMaxSize = shipType.info.magazineSize
//    private val magazine = magazineMaxSize
//    private var canShoot = true
//    private val _magazineSize = MutableStateFlow<Int>(shipType.info.magazineSize)
//    val magazineSize: StateFlow<Int> = _magazineSize.asStateFlow()
//    private var recoveringJob: Job = viewModelScope.launch(Dispatchers.IO) {}
//    private var actionJob: Job = viewModelScope.launch(Dispatchers.IO) {}
//    private val shipState = MutableStateFlow(State.FirstShootNotDone)

//    init {
//        Log.i(TAG, "init: ")
//        viewModelScope.launch(Dispatchers.IO) {
//            magazineSize.collect {
//                if (it <= 0) {
//                    Log.e(TAG, "magazine is empty: ")
//                }
//            }
//        }
//    }
//
//    private fun incrementAmmo() { if (_magazineSize.value < shipType.info.magazineSize) _magazineSize.value += 1 }
//    private fun decrementAmmo() { if (_magazineSize.value >= 0) { _magazineSize.value -= 1 } }
//    private fun magazineIsNotEmpty(): Boolean = magazineSize.value >= 0
//    private var ammoBeforeCharging = _magazineSize.value
//    private suspend fun updateMagazineFlowAndBlockShoot(newShoot: Shoot) {
//        Device.event.projectileFlow.emit(newShoot)
//        canShoot = false
//    }
//    private fun updateAmmoBeforeCharging() {ammoBeforeCharging = _magazineSize.value}
//    private var ammoCharged = 0
//    private fun updateAmmoCharged() {
//        Log.i(TAG, "updateAmmoCharged: ammoBeforeCharging $ammoBeforeCharging - magazineSize ${magazineSize.value}")
//        val diff = ammoBeforeCharging - magazineSize.value
//        when (diff) {
//            0 -> {
//                ammoCharged = 1
//                decrementAmmo()
//            }
//            in 1..magazineMaxSize -> { ammoCharged = diff }
//            else -> { ammoCharged = 1; Log.e( TAG, "updateAmmoCharged: ERROR ammoBeforeCharging $ammoBeforeCharging - magazineSize ${magazineSize.value}", ) }
//        }
//    }
//    private fun startAmmoRecovering() {
//        if ( _magazineSize.value <= shipType.info.magazineSize) {
//            recoveringJob = viewModelScope.launch(Dispatchers.IO) { recoverAmmo() }
//        }
//    }
//    suspend fun recoverAmmo() {
//        delay(shootingTimeInterval)
//        canShoot = true
//        delay(ammoRecoveryTimeMinusShootingTimeInterval)
//        incrementAmmo()
//        while (_magazineSize.value < shipType.info.magazineSize) {
//            delay(ammoRecoveryTime)
//            incrementAmmo()
//        }
//    }
//    suspend fun ammoConsumption() {
//        while (screenIsPressed) {
//            decrementAmmo()
//            delay(ammoRecoveryTime)
//        }
//    }
//    private var screenIsPressed = false
//    private var firstShootDone = false
//
//    suspend fun chargingShoot() {
//        if (canShoot && magazineIsNotEmpty()) {
//            actionJob.cancel()
//            recoveringJob.cancel()
//            actionJob = viewModelScope.launch(Dispatchers.IO) {
//                firstShootDone = true
//                updateAmmoBeforeCharging()
//                screenIsPressed = true
//                ammoConsumption()
//            }
//        }
//    }
//
//    suspend fun shoot() {
//        Log.e(TAG, "shoot: canshoot $canShoot")
//        if (canShoot && magazineIsNotEmpty() && firstShootDone) {
//            actionJob.cancel()
//            recoveringJob.cancel()
//            actionJob = viewModelScope.launch(Dispatchers.IO) {
//                recoveringJob.cancel()
//                updateAmmoCharged()
//                screenIsPressed = false
//                createProjectile()
//                startAmmoRecovering()
//            }
//        }
//    }
//
//    private suspend fun createProjectile() {
//        var ammo = ammoCharged
//        when (shipType.info.chargedProjectileType) {
//            ChargedProjectileType.Instant -> {
//                val newShoot = Shoot.newFromUser(
//                    type = shipType,
//                    vm = motionVM,
//                    behavior = ammoCharged,
//                    damage = shipType.info.damage * ammoCharged.toFloat(),
//                )
//                Log.i(TAG, "createProjectile: ammocharged $ammoCharged")
//                updateMagazineFlowAndBlockShoot(newShoot)
//                delay(shootingTimeInterval)
//            }
//            ChargedProjectileType.Rafal -> {
//                //todo : add additionnal projectiles following a rule copied on the original app
//                //todo : issue about fast single press to boost the dps
//                //solution could be to unable the shot after a period of time after the add shoot
//                //corresponding to the ammoRecoveryTime
//                while (ammo > 0) {
//                    ammo -= 1
//                    val newShoot = Shoot.newFromUser(
//                        type = shipType,
//                        vm = motionVM,
//                    )
//                    updateMagazineFlowAndBlockShoot(newShoot)
//                    delay(shootingTimeInterval)
//                }
//            }
//        }
//    }
}