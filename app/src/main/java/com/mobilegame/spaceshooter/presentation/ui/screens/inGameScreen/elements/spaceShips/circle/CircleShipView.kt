package com.mobilegame.spaceshooter.presentation.ui.screens.inGameScreen.elements.spaceShips

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import com.mobilegame.spaceshooter.logic.model.screen.inGameScreens.ship.SpaceShipViewModel
import com.mobilegame.spaceshooter.logic.uiHandler.SpaceShip.types.CircleSpaceShipIconUI
import com.mobilegame.spaceshooter.presentation.ui.screens.inGameScreen.elements.spaceShips.circle.CircleShipAmmunition

@Composable
fun CircleShipView(vm: SpaceShipViewModel, shipBox: Size) {
    val ui = remember { CircleSpaceShipIconUI(shipBox) }

    Box(
        Modifier
            .size(ui.sizes.shipBoxDp)
    ) {
        CircleShipShape(ui)
        CircleShipAmmunition(vm.ammoVM, ui)
    }
}