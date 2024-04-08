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

//    private val shootingTimeInterval = 500L
//    private val ammoRecoveryTime = shipType.info.ammoRecoveryTime
//    private val chargingTimeInterval = 400L
//    private val magazineMaxSize = shipType.info.magazineSize
//    private val _currentMagazineSize = MutableStateFlow(magazineMaxSize)
//    val currentMagazineSize: StateFlow<Int> = _currentMagazineSize.asStateFlow()
//    private var chargeStartFrom = currentMagazineSize.value
//    private var chargeStopAt = 0
//    private var isCharged = false
//    private var canShoot: Boolean? = null
//    private val _charging = MutableStateFlow(false)
//    private fun updateChargingTo(bool: Boolean) {_charging.value = bool}
//    private val _recovering = MutableStateFlow(false)
//    private fun updateRecoveringTo(bool: Boolean) {_recovering.value = bool}
//    private val _magazineFlow: MutableSharedFlow<Int> = MutableSharedFlow()
//    private var ammoJob: Job = viewModelScope.launch(Dispatchers.IO) {}
//    private val _isPressedFlow = MutableStateFlow(false)
//    val isPressedFlow: StateFlow<Boolean> = _isPressedFlow.asStateFlow()
//    var isPressed: Boolean = false
//    var hasBeenPressedOnce = false
//
//    init {
//        Log.i(TAG, "init: ")
//        viewModelScope.launch(Dispatchers.Main) {
////            val pr = async { collectPressureFlow() }
//            val ma = async { collectMagazineFlow() }
//            val re = async { collectRecoveringFlow() }
////            val ch = async { collectChargingFlow() }
//        }
//    }
//    private suspend fun collectPressureFlow(): Nothing = isPressedFlow.collect {
//        Log.i(TAG, "collectPressureFlow: $it")
//        isPressed = it
//        when (it) {
//            true -> { chargingProjectile() }
//            false -> { shootProjectile() }
//        }
//    }
//    private suspend fun collectMagazineFlow(): Nothing = _magazineFlow.collect {
//        updateCurrentMagazineSize(it)
//    }
//    private suspend fun collectRecoveringFlow(): Nothing = _recovering.collect {
//        if (it) {
////            ammoJob.cancel()
//            ammoJob = magazineAmmoRecovery()
//        }
//        else ammoJob.cancel()
//    }
//    private suspend fun collectChargingFlow(): Nothing = _charging.collect {
//        if (it) {
//            ammoJob.cancel()
//            ammoJob = consumeAmmo()
//        }
////        else ammoJob.cancel()
//    }
//
//    private fun updateCurrentMagazineSize(withValue: Int) {
//        _currentMagazineSize.value = currentMagazineSize.value + withValue
//    }
//    private suspend fun incrementMagazineSize() {
//        if (currentMagazineSize.value < magazineMaxSize) _magazineFlow.emit(1)
////        if (currentMagazineSize.value < magazineMaxSize) currentMagazineSize.value
//    }
//    private suspend fun decrementMagazineSize() {
//        if (currentMagazineSize.value > 0) _magazineFlow.emit(-1)
//    }
//    private suspend fun magazineAmmoRecovery() = viewModelScope.launch {
//        while (currentMagazineSize.value < magazineMaxSize) {
//            incrementMagazineSize()
//            delay(ammoRecoveryTime)
////            Log.i(TAG, "magazineAmmoRecovery: currentMagazineSize ${currentMagazineSize.value}")
//        }
//    }
//    fun updatePressureStateTo(bool: Boolean) = viewModelScope.launch { _isPressedFlow.emit(bool) }
//
////    private suspend fun consumeAmmo() {
//    private suspend fun consumeAmmo() = viewModelScope.launch {
////    = withContext(Dispatchers.Main) {
//        while (currentMagazineSize.value in 0 .. magazineMaxSize) {
////            Log.i(TAG, "consumeAmmo: magsize ${currentMagazineSize.value}")
//            decrementMagazineSize()
//            delay(chargingTimeInterval)
//            if (!isPressedFlow.value) break
////            if (!isPressed) break
//        }
//    }
//
//    fun chargingProjectile() {
//        updateRecoveringTo(false)
//        Log.i(TAG, "chargingProjectile: ")
////        updateChargingTo(true)
//        isCharged = true
//            //stop recovering ammo
//            // save the current magazine size to calculate after the size of the charge
//            // start to decrement ammos
//        chargeStartFrom = currentMagazineSize.value
////        consumeAmmo()
////        chargeStopAt = currentMagazineSize.value
//            // start to decrement the magazine charge with charging time interval
////            recoveringJob = viewModelScope.launch { magazineAmmoRecovery() }
////        viewModelScope.launch { val re = async { magazineAmmoRecovery() } }
////            magazineAmmoRecovery()
//        canShoot = true
//        Log.i(TAG, "chargingProjectile: end charge")
//    }
//
////    suspend fun shootProjectile() {
//    fun shootProjectile() = viewModelScope.launch {
//        // get the chargeStopAt if charged shoot
//
//        chargeStopAt = currentMagazineSize.value
////    val chargeSize = if (isCharged) chargeStartFrom - chargeStopAt else 1
//        val chargeSize = chargeStartFrom - chargeStopAt
//        Log.i(TAG, "shootProjectile: chargeSize $chargeSize")
//        // fire projectile
//        releaseProjectile(if (chargeSize > 0) chargeSize else 1)
////        canShoot = false
//        delay(shootingTimeInterval)
//        canShoot = true
//        isCharged = false
////        canShoot = true
//        // set the charge size at 0 / reset ammo charged
////        chargeStopAt = currentMagazineSize.value
////        chargeStartFrom = currentMagazineSize.value
////        chargeStopAt = 0
////        chargeStartFrom = 0
//        // set ischarge to false
//        updateRecoveringTo(true)
//    }
////    suspend fun releaseProjectile(chargeSize: Int) {
//    suspend fun releaseProjectile(chargeSize: Int) = viewModelScope.launch {
//        Log.i(TAG, "shootProjectile: chargeSize $chargeSize")
//        when (shipType.info.chargedProjectileType) {
//            ChargedProjectileType.Instant -> {
//                updateMagazineFlowAndBlockShoot(
//                    Shoot.newFromUser(
//                        type = shipType,
//                        vm = motionVM,
//                        behavior = chargeSize,
//                        damage = shipType.info.damage * chargeSize.toFloat(),
//                    )
//                )
//            }
//            ChargedProjectileType.Rafal -> {
//                //todo : add additionnal projectiles following a rule copied on the original app
//                //todo : issue about fast single press to boost the dps
//                //solution could be to unable the shot after a period of time after the add shoot
//                //corresponding to the ammoRecoveryTime
//                var ammo = chargeSize
//                while (ammo > 0) {
//                    updateMagazineFlowAndBlockShoot(
//                        Shoot.newFromUser(
//                            type = shipType,
//                            vm = motionVM,
//                        )
//                    )
//                    ammo -= 1
//                }
//            }
//        }
//    }
//
//    private suspend fun updateMagazineFlowAndBlockShoot(newShoot: Shoot) {
//        Log.i(TAG, "updateMagazineFlowAndBlockShoot: canshoot $canShoot")
//        canShoot?.let {
//            if (it) {
//                Device.event.projectileFlow.emit(newShoot)
//                canShoot = false
//            }
//        } ?: let { canShoot = true }
//    }



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

    init {
        Log.i(TAG, "init: ")
//        viewModelScope.launch { val af = async { ammoListener() } }
    }
//    private suspend fun ammoListener(): Nothing = magazineFlow.collect{
//        if (it > 0) incrementAmmo()
//        else if (it < 0) decrementAmmo()
//        else Log.e(TAG, "ammoListener: ERROR collect 0", )
//    }
    private fun incrementAmmo() { if (_magazineSize.value < shipType.info.magazineSize) _magazineSize.value += 1 }
    private fun decrementAmmo() { if (_magazineSize.value >= 0) { _magazineSize.value -= 1 } }
    private fun magazineIsNotEmpty(): Boolean = magazineSize.value > 0
    private var ammoBeforeCharging = _magazineSize.value
    private suspend fun updateMagazineFlowAndBlockShoot(newShoot: Shoot) {
//        if (canShoot)
        Device.event.projectileFlow.emit(newShoot)
        canShoot = false
    }
    private fun updateAmmoBeforeCharging() {ammoBeforeCharging = _magazineSize.value}
    private var ammoCharged = 0
//    private fun noAmmoIsCharged(): Boolean = ammoCharged > 0
    private fun updateAmmoCharged() {
        Log.i(TAG, "updateAmmoCharged: ammoBeforeCharging $ammoBeforeCharging - magazineSize ${magazineSize.value}")
        val diff = ammoBeforeCharging - magazineSize.value
        when (diff) {
            0 -> {
                ammoCharged = 1
                decrementAmmo()
            }
            in 1..magazineMaxSize -> { ammoCharged = diff }
            else -> { ammoCharged = 1; Log.e( TAG, "updateAmmoCharged: ERROR ammoBeforeCharging $ammoBeforeCharging - magazineSize ${magazineSize.value}", ) }
        }
//        ammoCharged = diff
    }
//    private fun resetAmmoCharged() {
//        Log.i(TAG, "resetAmmoCharged: ")
//        ammoCharged = 1
//    }
    private fun startAmmoRecovering() {
//        if (recoveringJob.isCompleted && _magazineSize.value < shipType.info.magazineSize) {
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
//            magazineFlow.emit(1)
//            canShoot = true
        }
    }
    suspend fun ammoConsumption() {
        while (screenIsPressed) {
//            magazineFlow.emit(-1)
            decrementAmmo()
            delay(ammoRecoveryTime)
        }
    }
    private var screenIsPressed = false
//    private var isCharged = false
    private var firstShootDone = false
//    suspend fun chargingShoot() {
    suspend fun chargingShoot() = viewModelScope.launch(Dispatchers.IO) {
//        if (canShoot) {
        if (canShoot && magazineIsNotEmpty()) {
//            if (_magazineSize.value > 0) {
//            if (_magazineSize.value > 0) {
                firstShootDone = true
                updateAmmoBeforeCharging()
                recoveringJob.cancel()
                screenIsPressed = true
//                isCharged = true
//            viewModelScope.launch(Dispatchers.IO) { ammoConsumption() }
                 ammoConsumption()
//            }
        }
    }

    suspend fun shoot() = viewModelScope.launch(Dispatchers.IO) {
//    suspend fun shoot() {
//        actionJob = viewModelScope.launch(Dispatchers.IO) {
//    fun shoot() {
//        if (canShoot && firstShootDone && noAmmoIsCharged()) {
        if (canShoot && firstShootDone) {
            recoveringJob.cancel()
//            if (firstShoot) {
//            if (firstShootDone) {
//                viewModelScope.launch() {
//            if (noAmmoIsCharged()) decrementAmmo()
            updateAmmoCharged()
            screenIsPressed = false
//                    if (_magazineSize.value != 0)
            createProjectile()
//                    else eLog("MunitionsVM::shoot", "${enoughAmmo()}")
//                    eLog("MunitionsVM::shoot", "ammo ${_magazineSize.value}")
            startAmmoRecovering()
        }
//        isCharged = false
    }
//}

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
//                motionVM.addShoot(newShoot)
//                Device.event.producingProjectile.tryEmit(newShoot)
                Log.i(TAG, "createProjectile: ammocharged $ammoCharged")
                updateMagazineFlowAndBlockShoot(newShoot)
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
//                    motionVM.addShoot(newShoot)
//                    Device.event.producingProjectile.tryEmit(newShoot)
                    updateMagazineFlowAndBlockShoot(newShoot)
                    delay(shootingTimeInterval)
                }
            }
        }
//        delay(ammoRecoveryTimeMinusShootingTimeInterval)
        //todo : ammocharged reset necessary ?
//        resetAmmoCharged()
    }
}