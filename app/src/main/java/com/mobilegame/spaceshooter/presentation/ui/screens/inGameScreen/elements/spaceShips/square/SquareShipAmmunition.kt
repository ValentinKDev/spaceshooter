package com.mobilegame.spaceshooter.presentation.ui.screens.inGameScreen.elements.spaceShips.square

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import com.mobilegame.spaceshooter.logic.model.screen.inGameScreens.ship.MunitionsViewModel
import com.mobilegame.spaceshooter.logic.uiHandler.SpaceShip.SquareSpaceShipIconUI
import com.mobilegame.spaceshooter.presentation.ui.screens.inGameScreen.elements.spaceShips.circle.MunitionsType

@Composable
fun SquareShipAmmunition(vm: MunitionsViewModel, ui: SquareSpaceShipIconUI) {
    val munitions by remember { vm.magazineSize }.collectAsState()

    for (n in 1..munitions) {
        SquareShipAmmunition(ui, n, MunitionsType.InMagazine)
    }
}

@Composable
private fun SquareShipAmmunition(ui: SquareSpaceShipIconUI, n: Int, type: MunitionsType) {
//    SquareDrawMunition(topLeft = ui.ammos.getAmmunitionOffset(n), ui = ui, type = type)
    SquareDrawMunition(topLeftOffset = ui.ammos.getAmmunitionOffset(n), ui = ui, type = type)
}
