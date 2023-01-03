package com.mobilegame.spaceshooter.presentation.ui.screens.utils.spaceShips

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import com.mobilegame.spaceshooter.logic.model.screen.inGameScreens.SpaceShipViewModel
import com.mobilegame.spaceshooter.logic.model.screen.uiHandler.SpaceShip.SpaceShipIconUI

@Composable
fun DefaultShip(vm: SpaceShipViewModel, ui: SpaceShipIconUI) {

    Box(
        Modifier
            .size(ui.sizes.shipBoxDp)
    ) {
        DefaultShipShape(ui)
        DefaultShipMunitions(vm, ui)
    }
}