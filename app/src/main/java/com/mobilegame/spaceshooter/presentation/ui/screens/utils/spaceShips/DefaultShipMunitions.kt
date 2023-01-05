package com.mobilegame.spaceshooter.presentation.ui.screens.utils.spaceShips

import androidx.compose.foundation.Canvas
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.drawscope.Fill
import com.mobilegame.spaceshooter.logic.model.screen.inGameScreens.ship.AmmunitionViewModel
import com.mobilegame.spaceshooter.logic.model.screen.inGameScreens.ship.SpaceShipViewModel
import com.mobilegame.spaceshooter.logic.uiHandler.SpaceShip.SpaceShipIconUI

@Composable
fun DrawMunition(vm: AmmunitionViewModel, ui: SpaceShipIconUI, n: Int) {
    Canvas(Modifier){
        drawCircle(
            center = vm.getAmmunitionOffset(ui, n),
            radius = ui.ammunition.radius,
            style = Fill,
            color = ui.colors.munitions,
        )
    }
}

@Composable
fun DefaultShipMunitions(vm: AmmunitionViewModel, ui: SpaceShipIconUI) {
    val munitions by remember { vm.ammunition }.collectAsState()
    for (n in 1..munitions) {
        DrawMunition(vm, ui, n)
    }
}
