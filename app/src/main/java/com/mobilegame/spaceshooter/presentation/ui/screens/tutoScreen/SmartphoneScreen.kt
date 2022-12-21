package com.mobilegame.spaceshooter.presentation.ui.screens.tutoScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.mobilegame.spaceshooter.domain.model.screen.inGameScreen.SpaceShipViewModel
import com.mobilegame.spaceshooter.domain.model.screen.tutoScreen.TutoScreenViewModel
import com.mobilegame.spaceshooter.domain.model.screen.uiAdapter.SpaceShip.SpaceShipIconAdapter
import com.mobilegame.spaceshooter.domain.model.screen.uiAdapter.SpaceShip.SpaceShipType
import com.mobilegame.spaceshooter.presentation.theme.MyColor
import com.mobilegame.spaceshooter.presentation.ui.screens.utils.CenterComposable
import com.mobilegame.spaceshooter.presentation.ui.screens.utils.PaddingComposable
import com.mobilegame.spaceshooter.presentation.ui.screens.utils.spaceShips.DefaultShip

@Composable
fun SmartphoneScreen(vm: TutoScreenViewModel) {
    PaddingComposable(
        horizontalPadding = vm.ui.smartphoneEmulator.padd.screenHorizontal,
        verticalPadding = vm.ui.smartphoneEmulator.padd.screenVertical,
    ) {
        Box(
            Modifier
                .fillMaxSize()
                .border(
                    width = vm.ui.smartphoneEmulator.sizes.borderDp,
                    shape = RoundedCornerShape(2.dp),
                    color = MyColor.Platinium
                )
                .background(Color.Black)
        ) {
            CenterComposable {
                DefaultShip(
                    SpaceShipViewModel(),
                    SpaceShipIconAdapter(
                        LocalContext.current,
                        SpaceShipType.Default,
                        shipBox = 50F
                    )
                )
            }
        }
    }
}
