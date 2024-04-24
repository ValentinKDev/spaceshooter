
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mobilegame.spaceshooter.data.connection.wifi.PreparationState
import com.mobilegame.spaceshooter.data.device.Device
import com.mobilegame.spaceshooter.logic.model.navigation.Navigator
import com.mobilegame.spaceshooter.logic.model.navigation.PressureViewModel
import com.mobilegame.spaceshooter.logic.model.navigation.Screens
import com.mobilegame.spaceshooter.logic.model.screen.spaceShipMenuScreen.ShipPicking
import com.mobilegame.spaceshooter.logic.model.screen.tryAgainScreen.TryAgainStats
import com.mobilegame.spaceshooter.logic.repository.device.DeviceEventRepo
import com.mobilegame.spaceshooter.logic.uiHandler.screens.shipMenuScreen.ShipMenuUI
import com.mobilegame.spaceshooter.logic.uiHandler.template.TemplateUI
import com.mobilegame.spaceshooter.presentation.ui.navigation.StrArgumentNav
import com.mobilegame.spaceshooter.utils.analyze.eLog
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.job
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ShipMenuViewModel(): ViewModel() {
    private val TAG = "ShipMenuViewModel"
    val templateUI = TemplateUI(instantNavBack = true)
    val shipMenuUI = ShipMenuUI()
    val pressureVM = PressureViewModel()
    val shipPicking = ShipPicking(shipMenuUI.body.sizes.shipViewBox)

    //    var nav: Navigator? = null
    private val _navigate = MutableStateFlow(false)
    val navigate: StateFlow<Boolean> = _navigate.asStateFlow()


    init {
        viewModelScope.launch {
            val ltr = async {
                //todo: refactor this
                withContext(Dispatchers.IO) {
                    Device.wifi.listVisibleDevicesFlow.map { it.firstOrNull() }.collect {visibleDeviceState ->
                        visibleDeviceState?.let {
                            when (it.state) {
                                PreparationState.ReadyToPlay -> {
//                                PreparationState.ReadyToChooseShip -> {
                                    Log.e(TAG, "front device is ReadyToPlay")
                                    if (pressureVM.full.value) {
                                        Log.i(TAG, "ready to play and full is true: ")
//                                        val ev = async { DeviceEventRepo().sendReadyToChooseShip() }
//                                        val ev = async { DeviceEventRepo().sendReadyToPlay() }
                                        spaceShipPicked()
                                        this.coroutineContext.job.cancel()
                                        onCleared()
                                    }
                                }
                                else -> {
//                                eLog(TAG, "collecting  Device.wifi.listVisibleDevicesFlow ${it.state}")
                                }
                            }
                        }
                    }
                }
            }
            val emr = async {
                pressureVM.full.collect { _full ->
                    eLog(TAG, "collecting pressureVM.full $_full")
                    if (_full) { pressureReadyToPlay() }
                    else { pressureReleaseToPlay() }
                }
            }
        }
    }
    private suspend fun spaceShipPicked() {
//        Log.e(TAG, "spaceShipPicked: true \n\n\n\n\n true", )
//        _pickedShip.value = true
        Device.wifi.visibleDevices.value.first().shipType?.info?.name?.let {
            val argStr = StrArgumentNav.serializeArgToInGame(
                userShipTypeName = shipPicking.shipType.value.info.name,
                tryAgainStats = TryAgainStats.EMPTY_TRY_AGAIN_STATS,
            )
            _navigate.value = true
//            nav?.navig(destination = Screens.SpaceWarScreen, argumentStr = argStr) ?: Log.e(TAG, "spaceShipPicked: ERROR nav is null", )
        }
    }
    suspend fun navigateToGame(navigator: Navigator) {
        val argStr = StrArgumentNav.serializeArgToInGame(
            userShipTypeName = shipPicking.shipType.value.info.name,
            tryAgainStats = TryAgainStats.EMPTY_TRY_AGAIN_STATS,
        )
        navigator.navig(destination = Screens.SpaceWarScreen, argumentStr = argStr) ?: Log.e(TAG, "spaceShipPicked: ERROR nav is null", )
    }

    fun pressureReadyToPlay() = viewModelScope.launch {
        Log.i(TAG, "pressureReadyToPlay: ", )
        DeviceEventRepo().sendReadyToPlay(shipPicking.shipType.value.info.name)
    }
    fun pressureReleaseToPlay()  = viewModelScope.launch {
        Log.i(TAG, "pressureReleaseToPlay: ")
        DeviceEventRepo().sendNotReadyToPlay()
    }

//    fun initNav(navigator: Navigator) {
//        nav = navigator
//    }

    fun handleLeftArrowClick() = viewModelScope.launch { shipPicking.handleLeftArrowClick() }
    fun handleRightArrowClick() = viewModelScope.launch { shipPicking.handleRightArrowClick() }

    override fun onCleared() {
        Log.w(TAG, "onCleared: ")
        super.onCleared()
    }
}