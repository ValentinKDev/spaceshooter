package com.mobilegame.spaceshooter.presentation.ui.screens.inGameScreen.elements

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import com.mobilegame.spaceshooter.logic.model.screen.inGameScreens.duelGameScreen.SpaceWarGameViewModel
import com.mobilegame.spaceshooter.presentation.ui.screens.inGameScreen.elements.spaceShips.circle.CircleDrawMunition
import com.mobilegame.spaceshooter.presentation.ui.screens.inGameScreen.elements.spaceShips.circle.MunitionsType
import com.mobilegame.spaceshooter.utils.extensions.toOffset


@Composable
fun ShootsView(vm: SpaceWarGameViewModel) {
    val shoots by remember { vm.shipVM.motionVM.shootList }.collectAsState()

    shoots.forEach{
        CircleDrawMunition(center = it.offsetDp.toOffset(), ui = vm.ui.spaceShip, type = MunitionsType.Shoot)
    }
}