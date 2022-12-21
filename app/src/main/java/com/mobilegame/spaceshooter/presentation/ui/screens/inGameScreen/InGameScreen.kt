package com.mobilegame.spaceshooter.presentation.ui.screens.inGameScreen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import com.mobilegame.spaceshooter.domain.model.screen.inGameScreen.InGameViewModel
import com.mobilegame.spaceshooter.domain.model.screen.uiAdapter.SpaceShip.SpaceShipIconAdapter
import com.mobilegame.spaceshooter.domain.model.screen.uiAdapter.SpaceShip.SpaceShipType
import com.mobilegame.spaceshooter.presentation.ui.navigation.Navigator
import com.mobilegame.spaceshooter.presentation.ui.screens.utils.spaceShips.DefaultShip
import com.mobilegame.spaceshooter.utils.analyze.eLog


@Composable
fun InGameScreen(navigator: Navigator = Navigator(), vm: InGameViewModel) {
    val context = LocalContext.current
    val position by remember { vm.shipVM.pCenterDp }.collectAsState()

    LaunchedEffect(true) {
        eLog("InGameScreen", "Launch Screen")
    }

    Box( Modifier.fillMaxSize() ) {
        Box(
            Modifier
                .wrapContentSize()
                .offset(position.x, position.y)
        ) {
            DefaultShip( vm.shipVM, vm.ui.spaceShip )
        }
    }
}