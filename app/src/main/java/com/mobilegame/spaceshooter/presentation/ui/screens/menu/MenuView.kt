package com.mobilegame.spaceshooter.presentation.ui.screens.menu

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.material3.contentColorFor
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import com.mobilegame.spaceshooter.logic.model.screen.Screens
import com.mobilegame.spaceshooter.logic.model.screen.mainScreen.PressureNavigationViewModel
import com.mobilegame.spaceshooter.logic.uiHandler.Device
import com.mobilegame.spaceshooter.logic.uiHandler.screens.menu.LetterAUI
import com.mobilegame.spaceshooter.logic.uiHandler.screens.menu.LetterCUI
import com.mobilegame.spaceshooter.logic.uiHandler.screens.menu.LetterPUI
import com.mobilegame.spaceshooter.logic.uiHandler.screens.menu.LetterSUI
import com.mobilegame.spaceshooter.presentation.theme.MyColor
import com.mobilegame.spaceshooter.presentation.ui.navigation.Navigator
import com.mobilegame.spaceshooter.presentation.ui.screens.menu.letters.*
import com.mobilegame.spaceshooter.presentation.ui.screens.utils.SpacerWithBackground
import com.mobilegame.spaceshooter.presentation.ui.screens.utils.chargingEffect.chargingScreen.ChargingScreen
import com.mobilegame.spaceshooter.utils.extensions.div
import com.mobilegame.spaceshooter.utils.extensions.subtract
import com.mobilegame.spaceshooter.utils.extensions.time
import com.mobilegame.spaceshooter.utils.extensions.toSquare

@Composable
fun MenuScreen(navigator: Navigator) {
    val letterSizeDp = 100.dp.toSquare()
    val letterPaddingDp = letterSizeDp.width * 0.15F
    val letterSpacerSizeDp = DpSize(letterPaddingDp, letterSizeDp.height)
    val verticalPadding = (Device.sizeDp.height subtract letterSizeDp.height) div 2F

    val word = "SPAC"
    ChargingScreen(
        navigator = navigator,
        handler = PressureNavigationViewModel(Screens.MainScreen, 900L),
        contentSize = DpSize((letterSizeDp.width time word.length.toFloat()) + (letterPaddingDp time (word.length - 1).toFloat()), letterSizeDp.height),
        screenSize = Device.sizeDp,
        startPadding = (Device.sizeDp.width subtract (letterSizeDp.width time 4F)) div 2F,
        endPadding = (Device.sizeDp.width subtract (letterSizeDp.width time 4F)) div 2F,
        topPadding = verticalPadding,
        bottomPadding = verticalPadding,
    ) {
        Row {
            DrawS( LetterSUI( letterSizeDp )  )
            SpacerWithBackground(size = letterSpacerSizeDp)
            DrawP( LetterPUI( letterSizeDp )  )
            SpacerWithBackground(size = letterSpacerSizeDp)
            DrawA( LetterAUI( letterSizeDp ) )
            SpacerWithBackground(size = letterSpacerSizeDp)
            DrawC( LetterCUI( letterSizeDp ) )
            SpacerWithBackground(size = letterSpacerSizeDp)
        }
    }
}

// P, E, W, R, L, H, I, T, O, N, I, Y
// spaceShooter, duel, spaceship, wars, spacewar stats, donations, hockey
// SPACEWAR, HOCKEY, STATS, DONATIONS
