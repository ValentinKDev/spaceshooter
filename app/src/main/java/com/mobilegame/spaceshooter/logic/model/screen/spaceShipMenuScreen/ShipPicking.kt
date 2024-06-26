package com.mobilegame.spaceshooter.logic.model.screen.spaceShipMenuScreen

import android.util.Log
import androidx.compose.ui.geometry.Size
import androidx.lifecycle.viewModelScope
import com.mobilegame.spaceshooter.logic.model.screen.inGameScreens.ship.types.ShipType
import com.mobilegame.spaceshooter.logic.uiHandler.SpaceShip.SpaceShipIconUIInterface
import com.mobilegame.spaceshooter.logic.uiHandler.template.LateralDirection
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class ShipPicking(private val shipViewBox: Size, shipSelected: ShipType = ShipType.DEFAULT) {
    private val TAG = "ShipPicking"
    private val shipListSize = ShipType.getList().size
    private val _shipListIndex = MutableStateFlow(ShipType.getList().indexOf(shipSelected))
    val shipListIndex: StateFlow<Int> = _shipListIndex.asStateFlow()
    var oldIndex: Int = ShipType.getList().indexOf(shipSelected)

    private val _shipType = MutableStateFlow(ShipType.getFromList(shipListIndex.value))
    val shipType: StateFlow<ShipType> = _shipType.asStateFlow()
    var shipUI: SpaceShipIconUIInterface = getCurrentShipUI()

    private fun updateShipUI() { shipUI = getCurrentShipUI() }
    private fun getCurrentShipUI(): SpaceShipIconUIInterface = ShipType.getTypeShipUI(shipListIndex.value, shipViewBox)
    private suspend fun updateShipType() { _shipType.emit(ShipType.getFromList(shipListIndex.value)) }

    private fun decrementListIndex() {
        if (shipListIndex.value != oldIndex) { if (oldIndex == 0) oldIndex = shipListSize - 1 else oldIndex-- }
        if (_shipListIndex.value == 0) _shipListIndex.value = shipListSize - 1
        else _shipListIndex.value = shipListIndex.value - 1
    }
    private fun incrementListIndex() {
        if (shipListIndex.value != oldIndex) { if (oldIndex == shipListSize - 1) oldIndex = 0  else oldIndex++ }
        if (_shipListIndex.value == shipListSize - 1) _shipListIndex.value = 0
        else _shipListIndex.value = shipListIndex.value + 1
    }

    suspend fun handleArrowClick(direction: LateralDirection) {
        when (direction) {
            LateralDirection.Left -> { decrementListIndex() }
            LateralDirection.Right -> { incrementListIndex() }
        }
        updateShipType()
        updateShipUI()
    }
}