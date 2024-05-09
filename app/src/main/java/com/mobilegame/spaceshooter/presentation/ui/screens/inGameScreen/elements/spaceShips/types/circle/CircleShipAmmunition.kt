package com.mobilegame.spaceshooter.presentation.ui.screens.inGameScreen.elements.spaceShips.types.circle

import androidx.compose.runtime.*
import com.mobilegame.spaceshooter.logic.model.screen.inGameScreens.ship.MunitionsViewModel
import com.mobilegame.spaceshooter.logic.uiHandler.SpaceShip.types.CircleSpaceShipIconUI

@Composable
fun CircleShipAmmunition(magazine: Int, ui: CircleSpaceShipIconUI) {
    for (n in 1..magazine) {
        CircleShipAmmunition(ui, n, MunitionsType.InMagazine)
    }
}

@Composable
private fun CircleShipAmmunition(ui: CircleSpaceShipIconUI, n: Int, type: MunitionsType) {
    CircleDrawMunition(center = ui.getAmmunitionOffset(n), ui = ui, type = type)
}