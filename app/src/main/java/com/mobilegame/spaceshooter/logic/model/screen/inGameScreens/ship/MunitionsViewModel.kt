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
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class MunitionsViewModel(private val motionVM: MotionsViewModel, private val shipType: ShipType) : ViewModel() {
    val TAG = "MunitionsViewModel"
//    private val shootingTimeInterval = shipType.info.shootingTimeInterval
//    private val ammoRecoveryTime = shipType.info.ammoRecoveryTime
//    private val chargingTimeInterval = 200L
//    private val magazineMaxSize = shipType.info.magazineSize
////    private val currentMagazineSize = magazineMaxSize
//    private val _currentMagazineSize = MutableStateFlow(magazineMaxSize)
//    val currentMagazineSize: StateFlow<Int> = _currentMagazineSize.asStateFlow()
//    private var chargeStartFrom = 0
//    private var chargeStopAt = 0
//    private var isCharged = false
//    private val _magazineFlow: MutableSharedFlow<Int> = MutableSharedFlow()
//    val magazineFlow: SharedFlow<Int> = _magazineFlow
//    private var recoveringJob: Job = viewModelScope.launch(Dispatchers.IO) {}
////    private lateinit var recoveringJob: Job
//    private var isPressedFlow = MutableSharedFlow<Boolean>()
//    var isPressed: Boolean = false
//    var hasBeenPressedOnce = false
////    private var isPressed = false
////    private var screenPressed =
//
//    init {
//        Log.i(TAG, "init: ")
//        viewModelScope.launch(Dispatchers.Main) {
////            withContext(Dispatchers.IO) {
//                isPressedFlow.collect {
//                    isPressed = it
//                    when (it) {
//                        true -> { chargingProjectile() }
//                        false -> { shootProjectile() }
////                        true -> {
////                            Log.i(TAG, "isPressed: $isPressed")
////                        }
////                        false -> {
////                            Log.i(TAG, "isPressed: $isPressed")
////                        }
//                    }
//                }
////            }
//        }
//    }
//    private suspend fun incrementMagazineSize() { if (currentMagazineSize.value < magazineMaxSize) _magazineFlow.emit(1) }
//    private suspend fun decrementMagazineSize() { if (currentMagazineSize.value > 0) _magazineFlow.emit(-1) }
//    private suspend fun magazineAmmoRecovery() {
//        while (currentMagazineSize.value < magazineMaxSize) {
//            incrementMagazineSize()
//            delay(ammoRecoveryTime)
//            Log.i(TAG, "magazineAmmoRecovery: currentMagazineSize ${currentMagazineSize.value}")
//        }
//    }
//    fun updatePressureStateTo(bool: Boolean) = viewModelScope.launch {
//        Log.e(TAG, "updatePressureStateTo: $bool", )
//        isPressedFlow.emit(bool)
//    }
//
//
//    private suspend fun consumeAmmo() {
////    private var consumeAmmo(): Job = viewModelScope.launch(Dispatchers.IO) {
////        while (currentMagazineSize.value > 0 ) {
//        while (currentMagazineSize.value > 0 && isPressed) {
//            decrementMagazineSize()
//            delay(chargingTimeInterval)
//            Log.i(TAG, "consumeAmmo: currentMagazineSize ${currentMagazineSize.value}")
//            Log.i(TAG, "consumeAmmo: is pressed ${isPressed}")
//            if (isPressedFlow.equals(false)) break
////            if (isPressed.map { it }.equals() == false) break
//        }
//    }
//    private fun shootProjectile() {
//        Log.i(TAG, "shootProjectile: ")
//        // get the chargeStopAt if charged shoot
//        chargeStopAt = currentMagazineSize.value
//        val chargeSize = chargeStartFrom - chargeStopAt
//        Log.i(TAG, "shoot: chargeSize $chargeSize")
//        // fire projectile
//        // set the charge size at 0
//        chargeStopAt = 0
//        chargeStartFrom = 0
//        // set ischarge to false
//    }
//    private suspend fun chargingProjectile() {
//        Log.i(TAG, "chargingProjectile: ")
////        if (hasBeenPressedOnce) {
////            isCharged = true
//            //stop recovering ammo
//            recoveringJob.cancel()
//            // save the current magazine size to calculate after the size of the charge
//            chargeStartFrom = currentMagazineSize.value
//            // start to decrement ammos
//            consumeAmmo()
//            // start to decrement the magazine charge with charging time interval
////            recoveringJob = viewModelScope.launch { magazineAmmoRecovery() }
//            magazineAmmoRecovery()
////        }
//
//    }
//    }

    private val magazineFlow: MutableSharedFlow<Int> = MutableSharedFlow()
    private val shootingTimeInterval = shipType.info.shootingTimeInterval
    private val ammoRecoveryTime = shipType.info.ammoRecoveryTime
    private val magazineMaxSize = shipType.info.magazineSize
    private val magazine = magazineMaxSize
    private var canShoot = true

    private val _magazineSize = MutableStateFlow<Int>(shipType.info.magazineSize)
    val magazineSize: StateFlow<Int> = _magazineSize.asStateFlow()

    init {
        Log.i(TAG, "init: ")
        viewModelScope.launch { val af = async { ammoListener() } }
    }
    private suspend fun ammoListener(): Nothing = magazineFlow.collect{
        if (it > 0) incrementAmmo()
        else if (it < 0) decrementAmmo()
        else Log.e(TAG, "ammoListener: ERROR collect 0", )
    }
    private fun incrementAmmo() { if (_magazineSize.value < shipType.info.magazineSize) _magazineSize.value += 1 }
    private fun decrementAmmo() { if (_magazineSize.value >= 0) { _magazineSize.value -= 1 } }
    private var ammoBeforeCharging = _magazineSize.value
    private suspend fun updateMagazineFlowAndBlockShoot(newShoot: Shoot) {
//        if (canShoot)
            Device.event.projectileFlow.emit(newShoot)
        canShoot = false
    }
    private fun updateAmmoBeforeCharging() {ammoBeforeCharging = _magazineSize.value}
    private var ammoCharged = 1
    private fun updateAmmoCharged() {
        ammoCharged = ammoBeforeCharging - magazineSize.value
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
//            incrementAmmo()
            magazineFlow.emit(1)
            canShoot = true
        }
    }
    suspend fun ammoConsumption() {
        while (screenIsPressed) {
            magazineFlow.emit(-1)
//            decrementAmmo()
            delay(ammoRecoveryTime)
        }
    }
    private var screenIsPressed = false
    private var firstShoot = false
    fun chargingShoot() {
        if (canShoot) {
            if (_magazineSize.value > 0) {
                firstShoot = true
                updateAmmoBeforeCharging()
                recoveringJob.cancel()
                screenIsPressed = true
                viewModelScope.launch(Dispatchers.IO) { ammoConsumption() }
            }
        }
    }

    fun shoot() {
        if (canShoot) {
            if (firstShoot) {
                viewModelScope.launch() {
                    updateAmmoCharged()
                    screenIsPressed = false
                    if (_magazineSize.value != 0)
                        startShooting()
//                    else eLog("MunitionsVM::shoot", "${enoughAmmo()}")
//                    eLog("MunitionsVM::shoot", "ammo ${_magazineSize.value}")
                    recoverAmmo()
                }
            }
        }
    }

    private suspend fun startShooting() {
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
        //todo : ammocharged reset necessary ?
        resetAmmoCharged()
    }
}