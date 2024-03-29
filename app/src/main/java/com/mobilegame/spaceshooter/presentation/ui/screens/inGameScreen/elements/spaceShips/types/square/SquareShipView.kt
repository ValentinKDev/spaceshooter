package com.mobilegame.spaceshooter.presentation.ui.screens.inGameScreen.elements.spaceShips.types.square

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import com.mobilegame.spaceshooter.logic.model.screen.inGameScreens.ship.SpaceShipViewModel
import com.mobilegame.spaceshooter.logic.uiHandler.SpaceShip.types.SquareSpaceShipIconUI

@Composable
//fun SquareShipView(vm: SpaceShipViewModel, shipViewBox: Size) {
fun SquareShipView(lifeRatio: Float, magazine: Int, shipViewBox: Size) {
    val ui = remember { SquareSpaceShipIconUI(shipViewBox) }

    Box(
        Modifier
            .size(ui.sizes.shipViewBoxSizeDp)
//            .background(Color.Red)
    ) {
//        SquareShipShape(vm.lifeVM, ui)
        SquareShipShape(lifeRatio, ui)
        SquareShipAmmunition(magazine, ui)
//        SquareShipAmmunition(vm.ammoVM, ui)
    }
}