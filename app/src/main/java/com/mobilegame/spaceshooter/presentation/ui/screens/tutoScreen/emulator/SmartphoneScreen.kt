package com.mobilegame.spaceshooter.presentation.ui.screens.tutoScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import com.mobilegame.spaceshooter.domain.model.screen.tutoScreen.TutoScreenViewModel
import com.mobilegame.spaceshooter.domain.model.screen.uiAdapter.SpaceShip.SpaceShipIconAdapter
import com.mobilegame.spaceshooter.domain.model.screen.uiAdapter.SpaceShip.SpaceShipType
import com.mobilegame.spaceshooter.presentation.ui.screens.inGameScreen.InGameScreen
import com.mobilegame.spaceshooter.presentation.ui.screens.utils.CenterComposable
import com.mobilegame.spaceshooter.presentation.ui.screens.utils.spaceShips.DefaultShip

@Composable
fun EmulatorScreen(vm: TutoScreenViewModel) {
    CenterComposable {
        Box(
            Modifier
                .size(vm.ui.smartphoneEmulator.sizes.screenInnerDp)
                .background(Color.Black)
        ) {
            InGameScreen(vm = vm.gameVM)
        }
    }
}
