package com.mobilegame.spaceshooter.presentation.ui.screens.inGameScreen.elements.spaceShips.circle

import androidx.compose.runtime.*
import com.mobilegame.spaceshooter.logic.model.screen.inGameScreens.ship.MunitionsViewModel
import com.mobilegame.spaceshooter.logic.uiHandler.SpaceShip.CircleSpaceShipIconUI

@Composable
fun CircleShipAmmunition(vm: MunitionsViewModel, ui: CircleSpaceShipIconUI) {
    val munitions by remember { vm.magazineSize }.collectAsState()

    for (n in 1..munitions) {
        CircleShipAmmunition(ui, n, MunitionsType.InMagazine)
    }
}

@Composable
private fun CircleShipAmmunition(ui: CircleSpaceShipIconUI, n: Int, type: MunitionsType) {
    CircleDrawMunition(center = ui.getAmmunitionOffset(n), ui = ui, type = type)
}
