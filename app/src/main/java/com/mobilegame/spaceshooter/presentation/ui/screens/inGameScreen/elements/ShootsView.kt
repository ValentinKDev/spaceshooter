package com.mobilegame.spaceshooter.presentation.ui.screens.inGameScreen.elements

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import com.mobilegame.spaceshooter.logic.model.screen.inGameScreens.duelGameScreen.SpaceWarGameViewModel
import com.mobilegame.spaceshooter.logic.model.screen.inGameScreens.ship.types.ShipType
import com.mobilegame.spaceshooter.logic.uiHandler.SpaceShip.types.CircleSpaceShipIconUI
import com.mobilegame.spaceshooter.logic.uiHandler.SpaceShip.types.LaserySpaceShipIconUI
import com.mobilegame.spaceshooter.logic.uiHandler.SpaceShip.types.SquareSpaceShipIconUI
import com.mobilegame.spaceshooter.presentation.ui.screens.inGameScreen.elements.spaceShips.types.circle.CircleDrawMunition
import com.mobilegame.spaceshooter.presentation.ui.screens.inGameScreen.elements.spaceShips.types.circle.MunitionsType
import com.mobilegame.spaceshooter.presentation.ui.screens.inGameScreen.elements.spaceShips.types.lasery.LaseryDrawProjectile
import com.mobilegame.spaceshooter.presentation.ui.screens.inGameScreen.elements.spaceShips.types.square.SquareDrawMunition
import com.mobilegame.spaceshooter.utils.extensions.alpha
import com.mobilegame.spaceshooter.utils.extensions.toDp
import com.mobilegame.spaceshooter.utils.extensions.toDpSize
import com.mobilegame.spaceshooter.utils.extensions.toOffset
import com.mobilegame.spaceshooter.utils.extensions.toPair


@Composable
fun ShootsView(vm: SpaceWarGameViewModel) {
    val shoots by remember { vm.shipVM.motionVM.shootList }.collectAsState()
    val lasers by remember { vm.shipVM.motionVM.laserList }.collectAsState()
    val listShipUI = remember { vm.ui.listShipUI }
//    val test by remember { vm.shipVM.motionVM.testMunitionHitBox }.collectAsState()

//todo separate function to select the projectil/ammo in magazin regard to the ship type
    shoots.forEach{ projectile ->
        when (projectile.type) {
            ShipType.Circle -> CircleDrawMunition(
                center = projectile.offsetDp.toOffset(),
                ui = listShipUI[0] as CircleSpaceShipIconUI,
                type = projectile.from,
            )
            ShipType.Square -> SquareDrawMunition(
                topLeftOffset = projectile.offsetDp.toOffset(),
                ui = listShipUI[1] as SquareSpaceShipIconUI,
                type = projectile.from,
                particularBehavior = projectile.particularBehavior
            )
            ShipType.Lasery -> {}
        }
//        Box(
//            modifier = Modifier
//                .size(projectile.boxDp)
//                .offset(projectile.offsetDp.x, projectile.offsetDp.y)
//                .background(Color.Green.alpha(0.5F))
//        ){ }
    }
    lasers.forEach { laser ->
        LaseryDrawProjectile(
            laser = when (laser.from) {
                MunitionsType.UserProjectile -> laser.laserOnUserScreen
                MunitionsType.EnemiesProjectile -> laser.laserOnOpponentScreen
                MunitionsType.InMagazine -> {
                    Log.e( "ShootsView", "ShootsView: ERROR laser from ${laser.from}" )
                    Offset.Unspecified.toPair()
                }
            },
            ui = listShipUI[2] as LaserySpaceShipIconUI,
            particularBehavior = laser.particularBehavior,
            color = when (laser.from) {
                MunitionsType.UserProjectile -> { vm.userShipType.info.color }
                MunitionsType.EnemiesProjectile -> { Color.Red }
                MunitionsType.InMagazine -> {
                    Log.e( "ShootsView", "ShootsView: ERROR laser from ${laser.from}" )
                    Color.White
                }
            },
        )
    }
}