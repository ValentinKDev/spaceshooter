package com.mobilegame.spaceshooter.presentation.ui.screens.utils.spaceShips

import androidx.compose.foundation.Canvas
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.drawscope.Fill
import com.mobilegame.spaceshooter.logic.model.screen.inGameScreens.SpaceShipViewModel
import com.mobilegame.spaceshooter.logic.model.screen.uiHandler.SpaceShip.SpaceShipIconUI

@Composable
fun DrawMunition(vm: SpaceShipViewModel, ui: SpaceShipIconUI, n: Int) {
    Canvas(Modifier){
        drawCircle(
            center = vm.getMunitionOffset(ui, n),
            radius = ui.munitions.radius,
            style = Fill,
            color = ui.colors.munitions,
        )
    }
}

@Composable
fun DefaultShipMunitions(vm: SpaceShipViewModel, ui: SpaceShipIconUI) {
    val munitions by remember { vm.munitions }.collectAsState()
    for (n in 1..munitions) {
        DrawMunition(vm, ui, n)
    }
}
