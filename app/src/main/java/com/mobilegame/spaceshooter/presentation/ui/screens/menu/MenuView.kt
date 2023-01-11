package com.mobilegame.spaceshooter.presentation.ui.screens.menu

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.mobilegame.spaceshooter.logic.model.screen.Screens
import com.mobilegame.spaceshooter.logic.model.screen.mainScreen.PressureNavigationViewModel
import com.mobilegame.spaceshooter.logic.uiHandler.Device
import com.mobilegame.spaceshooter.logic.uiHandler.screens.menu.LetterAUI
import com.mobilegame.spaceshooter.logic.uiHandler.screens.menu.LetterPUI
import com.mobilegame.spaceshooter.logic.uiHandler.screens.menu.LetterSUI
import com.mobilegame.spaceshooter.presentation.ui.navigation.Navigator
import com.mobilegame.spaceshooter.presentation.ui.screens.DrawD
import com.mobilegame.spaceshooter.presentation.ui.screens.menu.letters.DrawA
import com.mobilegame.spaceshooter.presentation.ui.screens.menu.letters.DrawP
import com.mobilegame.spaceshooter.presentation.ui.screens.menu.letters.DrawS
import com.mobilegame.spaceshooter.presentation.ui.screens.utils.CenterComposable
import com.mobilegame.spaceshooter.presentation.ui.screens.utils.ChargingButton
import com.mobilegame.spaceshooter.presentation.ui.screens.utils.ClickableBoundaries
import com.mobilegame.spaceshooter.utils.extensions.toID

@Composable
fun MenuView(navigator: Navigator) {
    ChargingButton(
        handler = PressureNavigationViewModel(Screens.MainScreen),
        sizeDp = Device.sizeDp,
        clickableBoundaries = ClickableBoundaries.OutContent,
        navigator = navigator,
    ) {}
    CenterComposable(id = Screens.MenuScreen.route.toID()) {
        Row {
            DrawD()
            Spacer(Modifier.width(10.dp))
            DrawS( LetterSUI(100.dp, 15.dp) )
            DrawP( LetterPUI(100.dp, 15.dp))
            DrawA( LetterAUI(100.dp, 15.dp) )
        }
    }
}

// P, A, C, E, W, R, L, H, I, T, O, N, I, Y
// spaceShooter, duel, spaceship, wars, spacewar stats, donations, hockey
// SPACEWAR, HOCKEY, STATS, DONATIONS
