package com.mobilegame.spaceshooter.presentation.ui.screens.inGameScreen.elements

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import com.mobilegame.spaceshooter.logic.model.screen.inGameScreens.duelGameScreen.SpaceWarGameViewModel
import com.mobilegame.spaceshooter.logic.model.screen.inGameScreens.ship.types.ShipType
import com.mobilegame.spaceshooter.presentation.ui.screens.inGameScreen.elements.spaceShips.CircleShipView
import com.mobilegame.spaceshooter.presentation.ui.screens.inGameScreen.elements.spaceShips.square.SquareShipView

@Composable
fun SpaceShipView(vm: SpaceWarGameViewModel) {
    val position by remember { vm.shipVM.motionVM.shipPosition }.collectAsState()
    val motion by remember { vm.shipVM.motionVM.motion }.collectAsState()
    val speed by remember { vm.shipVM.motionVM.speedMagnitude }.collectAsState()

    val angle by animateFloatAsState(
        targetValue = vm.shipVM.motionVM.getTargetAngle(motion, speed),
        animationSpec = tween(600)
    )

    Box(
        Modifier
            .wrapContentSize()
            .offset(position.x, position.y)
            .rotate(angle)
//            .background(Color.Red.alpha(0.5F))
    ) {
        when (vm.shipVM.type) {
            ShipType.Circle -> CircleShipView( vm.shipVM,  vm.ui.sizes.shipViewBox)
            ShipType.Square -> SquareShipView(vm.shipVM, vm.ui.sizes.shipViewBox)
        }
    }
}