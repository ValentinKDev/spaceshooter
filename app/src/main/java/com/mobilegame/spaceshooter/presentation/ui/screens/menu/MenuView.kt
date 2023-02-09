package com.mobilegame.spaceshooter.presentation.ui.screens.menu

import androidx.compose.foundation.layout.Row
import androidx.compose.runtime.*
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import com.mobilegame.spaceshooter.logic.model.navigation.PressureViewModel
import com.mobilegame.spaceshooter.data.device.Device
import com.mobilegame.spaceshooter.logic.model.screen.Screens
import com.mobilegame.spaceshooter.logic.uiHandler.screens.menu.*
import com.mobilegame.spaceshooter.logic.model.navigation.Navigator
import com.mobilegame.spaceshooter.presentation.ui.screens.menu.letters.*
import com.mobilegame.spaceshooter.presentation.ui.screens.utils.SpacerWithBackground
import com.mobilegame.spaceshooter.presentation.ui.screens.utils.chargingEffect.chargingScreen.ChargingScreen
import com.mobilegame.spaceshooter.utils.analyze.eLog
import com.mobilegame.spaceshooter.utils.extensions.div
import com.mobilegame.spaceshooter.utils.extensions.subtract
import com.mobilegame.spaceshooter.utils.extensions.time
import com.mobilegame.spaceshooter.utils.extensions.toSquare

@Composable
fun MenuScreen(navigator: Navigator) {

    val pressureVM = remember { PressureViewModel() }
    val startNavigationJob by remember { pressureVM.full }.collectAsState()

    LaunchedEffect(startNavigationJob) {
        if (startNavigationJob) navigator.navig(Screens.MainScreen)
        eLog("MenuScreen", "MenuScreen launch")
    }
    val letterSizeDp = 75.dp.toSquare()
    val letterPaddingDp = letterSizeDp.width * 0.15F
    val letterSpacerSizeDp = DpSize(letterPaddingDp, letterSizeDp.height)
    val verticalPadding = (Device.metrics.sizeDp.height subtract letterSizeDp.height) div 2F

    val word = "SPACEWAR"
    ChargingScreen(
        navigator = navigator,
        handler = pressureVM,
        contentSize = DpSize((letterSizeDp.width time word.length.toFloat()) + (letterPaddingDp time (word.length - 1).toFloat()), letterSizeDp.height),
        screenSize = Device.metrics.sizeDp,
        startPadding = ((Device.metrics.sizeDp.width subtract (letterSizeDp.width time word.length.toFloat())) subtract (letterPaddingDp time (word.length - 1).toFloat())) div 2F,
        endPadding = ((Device.metrics.sizeDp.width subtract (letterSizeDp.width time word.length.toFloat())) subtract ((letterPaddingDp time (word.length - 1).toFloat())))div 2F,
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
            DrawE( LetterEUI( letterSizeDp ) )
            SpacerWithBackground(size = letterSpacerSizeDp)
            DrawW( LetterWUI( letterSizeDp ))
            SpacerWithBackground(size = letterSpacerSizeDp)
            DrawA( LetterAUI( letterSizeDp ) )
            SpacerWithBackground(size = letterSpacerSizeDp)
            DrawR( LetterRUI( letterSizeDp ) )
            SpacerWithBackground(size = letterSpacerSizeDp)
        }
    }
}

// space war    R,
// stats        T
// hockey       H, 0, K, Y
// donation     I, T, N,
// spaceShooter, duel, spaceship, wars, spacewar stats, donations, hockey
// SPACEWAR, HOCKEY, STATS, DONATIONS
