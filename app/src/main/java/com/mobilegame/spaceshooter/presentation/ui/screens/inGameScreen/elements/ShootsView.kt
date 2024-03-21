package com.mobilegame.spaceshooter.presentation.ui.screens.inGameScreen.elements

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import com.mobilegame.spaceshooter.logic.model.screen.inGameScreens.duelGameScreen.SpaceWarGameViewModel
import com.mobilegame.spaceshooter.logic.model.screen.inGameScreens.ship.types.ShipType
import com.mobilegame.spaceshooter.logic.uiHandler.SpaceShip.types.CircleSpaceShipIconUI
import com.mobilegame.spaceshooter.logic.uiHandler.SpaceShip.types.SquareSpaceShipIconUI
import com.mobilegame.spaceshooter.presentation.ui.screens.inGameScreen.elements.spaceShips.types.circle.CircleDrawMunition
import com.mobilegame.spaceshooter.presentation.ui.screens.inGameScreen.elements.spaceShips.types.circle.MunitionsType
import com.mobilegame.spaceshooter.presentation.ui.screens.inGameScreen.elements.spaceShips.types.square.SquareDrawMunition
import com.mobilegame.spaceshooter.utils.extensions.toOffset


@Composable
fun ShootsView(vm: SpaceWarGameViewModel) {
    val shoots by remember { vm.shipVM.motionVM.shootList }.collectAsState()
//    val shoots by remember { vm.shipVM.motionVM.projectileListFlow }.collectAsState(initial = listOf())

    LaunchedEffect(key1 = "") {
        Log.e("ShootsView", "ShootsView: \n\n\n\n\n\n\n\n shoot list size ${shoots.size} ", )
        shoots.forEach { Log.i("ShootsView", "ShootsView: shoots list ${it.type.name} ${it.from.name} == FromUser ${it.from == MunitionsType.UserProjectile}") }

    }
//todo separate function to select the projectil/ammo in magazin regard to the ship type
    shoots.forEach{ projectile ->
//        when (vm.shipVM.type) {
        when (projectile.type) {
            ShipType.Circle -> CircleDrawMunition(
                center = projectile.offsetDp.toOffset(),
//                ui = vm.ui.spaceShip,
//                ui = vm.ui.spaceShip as CircleSpaceShipIconUI,
                ui = ShipType.getTypeShipUI(projectile.type, vm.ui.sizes.shipViewBox) as CircleSpaceShipIconUI,
//                type = MunitionsType.UserProjectile,
                type = projectile.from,
            )
            ShipType.Square -> SquareDrawMunition(
//                shoot = projectile,
//                offset = projectile.getShootWithUpdatedDpOffset().offsetDp,
                topLeftOffset = projectile.offsetDp.toOffset(),
//                topLeft = projectile.offsetDp.toOffset(),
//                ui = vm.ui.spaceShip as SquareSpaceShipIconUI,
                ui = ShipType.getTypeShipUI(projectile.type, vm.ui.sizes.shipViewBox) as SquareSpaceShipIconUI,
//                type = MunitionsType.UserProjectile,
                type = projectile.from,
                particularBehavior = projectile.particularBehavior
            )
        }
    }
}