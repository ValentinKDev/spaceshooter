package com.mobilegame.spaceshooter.presentation.ui.screens.utils.spaceShips

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.mobilegame.spaceshooter.domain.model.screen.inGameScreen.SpaceShipViewModel
import com.mobilegame.spaceshooter.domain.model.screen.uiAdapter.SpaceShip.SpaceShipIconAdapter

@Composable
fun DefaultShip(vm: SpaceShipViewModel, ui: SpaceShipIconAdapter) {
    Box(
        Modifier
            .size(ui.sizes.shipBoxDp)
    ) {
        DefaultShipShape(ui)
        DefaultShipMunitions(vm, ui)
    }
}