package com.mobilegame.spaceshooter.presentation.ui.screens.inGameScreen.elements.spaceShips.cercle

import androidx.compose.runtime.*
import com.mobilegame.spaceshooter.logic.model.screen.inGameScreens.ship.MunitionsViewModel
import com.mobilegame.spaceshooter.logic.uiHandler.SpaceShip.SpaceShipIconUI

@Composable
fun DefaultShipAmmunition(vm: MunitionsViewModel, ui: SpaceShipIconUI) {
    val munitions by remember { vm.ammunition }.collectAsState()

    for (n in 1..munitions) {
        DrawShipAmmunition(vm, ui, n, MunitionsType.Ammunition)
    }
}

@Composable
private fun DrawShipAmmunition(vm: MunitionsViewModel, ui: SpaceShipIconUI, n: Int, type: MunitionsType) {
    DrawMunition(center = vm.getAmmunitionOffset(ui, n), ui = ui, type = type)
}
