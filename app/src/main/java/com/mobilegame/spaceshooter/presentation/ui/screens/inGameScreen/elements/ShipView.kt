package com.mobilegame.spaceshooter.presentation.ui.screens.inGameScreen.elements

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import com.mobilegame.spaceshooter.logic.model.screen.inGameScreens.duelGameScreen.DuelGameViewModel
import com.mobilegame.spaceshooter.presentation.ui.screens.utils.spaceShips.DefaultShip
import com.mobilegame.spaceshooter.utils.extensions.isLeftNorth
import com.mobilegame.spaceshooter.utils.extensions.isLeftSouth
import com.mobilegame.spaceshooter.utils.extensions.isRightNorth
import com.mobilegame.spaceshooter.utils.extensions.isRightSouth

@Composable
fun SpaceShipView(vm: DuelGameViewModel) {
    val position by remember { vm.motionVM.shipPosition }.collectAsState()
    val motion by remember { vm.motionVM.motion }.collectAsState()

    val angle by animateFloatAsState(
        targetValue = when {
            motion.isLeftNorth() -> -4F
            motion.isRightNorth() -> 4F
            motion.isLeftSouth() -> -8F
            motion.isRightSouth() -> 8F
            else -> 0F
        }
        ,
        animationSpec = tween(400)
    )

    Box(
        Modifier
            .wrapContentSize()
            .offset(position.x, position.y)
            .rotate(angle)
    ) {
        DefaultShip( vm.shipVM, vm.ui.spaceShip )
    }
}