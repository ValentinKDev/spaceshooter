package com.mobilegame.spaceshooter.presentation.ui.screens.inGameScreen.elements

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import com.mobilegame.spaceshooter.logic.model.screen.inGameScreens.duelGameScreen.SpaceWarGameViewModel
import com.mobilegame.spaceshooter.logic.model.screen.inGameScreens.ship.types.ShipType
import com.mobilegame.spaceshooter.presentation.ui.screens.inGameScreen.elements.spaceShips.circle.CircleDrawMunition
import com.mobilegame.spaceshooter.presentation.ui.screens.inGameScreen.elements.spaceShips.circle.MunitionsType
import com.mobilegame.spaceshooter.presentation.ui.screens.inGameScreen.elements.spaceShips.square.SquareDrawMunition
import com.mobilegame.spaceshooter.utils.extensions.toOffset


@Composable
fun ShootsView(vm: SpaceWarGameViewModel) {
    val shoots by remember { vm.shipVM.motionVM.shootList }.collectAsState()

//todo separate function to select the projectil/ammo in magazin regard to the ship type
    shoots.forEach{
        when (vm.shipVM.type) {
            ShipType.Circle -> CircleDrawMunition(center = it.offsetDp.toOffset(), ui = vm.ui.spaceShip, type = MunitionsType.Shoot)
            ShipType.Square -> SquareDrawMunition(
                topLeft = it.offsetDp.toOffset(),
                ui = vm.ui.spaceShip,
                type = MunitionsType.Shoot
            )
        }
    }
}