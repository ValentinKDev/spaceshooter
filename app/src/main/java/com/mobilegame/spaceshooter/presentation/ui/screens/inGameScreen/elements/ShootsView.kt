package com.mobilegame.spaceshooter.presentation.ui.screens.inGameScreen.elements

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import com.mobilegame.spaceshooter.logic.model.screen.inGameScreens.duelGameScreen.SpaceWarGameViewModel
import com.mobilegame.spaceshooter.logic.model.screen.inGameScreens.ship.types.ShipType
import com.mobilegame.spaceshooter.logic.uiHandler.SpaceShip.types.CircleSpaceShipIconUI
import com.mobilegame.spaceshooter.logic.uiHandler.SpaceShip.types.SquareSpaceShipIconUI
import com.mobilegame.spaceshooter.presentation.ui.screens.inGameScreen.elements.spaceShips.types.circle.CircleDrawMunition
import com.mobilegame.spaceshooter.presentation.ui.screens.inGameScreen.elements.spaceShips.types.square.SquareDrawMunition
import com.mobilegame.spaceshooter.utils.extensions.toOffset


@Composable
fun ShootsView(vm: SpaceWarGameViewModel) {
    val shoots by remember { vm.shipVM.motionVM.shootList }.collectAsState()

//todo separate function to select the projectil/ammo in magazin regard to the ship type
    shoots.forEach{
        when (vm.shipVM.type) {
            ShipType.Circle -> CircleDrawMunition(
                center = it.offsetDp.toOffset(),
//                ui = vm.ui.spaceShip,
                ui = vm.ui.spaceShip as CircleSpaceShipIconUI,
//                type = MunitionsType.UserProjectile,
                type = it.from,
            )
            ShipType.Square -> SquareDrawMunition(
//                shoot = it,
//                offset = it.getShootWithUpdatedDpOffset().offsetDp,
                topLeftOffset = it.offsetDp.toOffset(),
//                topLeft = it.offsetDp.toOffset(),
                ui = vm.ui.spaceShip as SquareSpaceShipIconUI,
//                type = MunitionsType.UserProjectile,
                type = it.from,
                particularBehavior = it.particularBehavior
            )
        }
    }
}