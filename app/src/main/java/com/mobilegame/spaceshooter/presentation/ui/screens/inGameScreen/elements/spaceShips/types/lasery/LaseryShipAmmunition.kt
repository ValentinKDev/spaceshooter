package com.mobilegame.spaceshooter.presentation.ui.screens.inGameScreen.elements.spaceShips.types.lasery

import androidx.compose.runtime.Composable
import com.mobilegame.spaceshooter.logic.uiHandler.SpaceShip.types.LaserySpaceShipIconUI

@Composable
fun LaseryShipMagazine(magazine: Int, ui: LaserySpaceShipIconUI) {
    for (n in 1..magazine) {
        LaseryDrawMagazineAmmo(ui, n)
    }
}

//@Composable
//fun LaseryShipAmmunition(ui: LaserySpaceShipIconUI, n: Int, type: MunitionsType) {
//    if (type)
//    LaseryDrawMunition(topLeftOffset = ui.ammo.getAmmunitionOffset(n), ui = ui, type = type)
//}