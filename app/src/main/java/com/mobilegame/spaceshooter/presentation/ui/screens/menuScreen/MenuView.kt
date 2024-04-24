package com.mobilegame.spaceshooter.presentation.ui.screens.menuScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material3.Icon
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.mobilegame.spaceshooter.logic.model.navigation.Navigator
import com.mobilegame.spaceshooter.logic.model.screen.menuScreen.MenuScreenViewModel
import com.mobilegame.spaceshooter.presentation.theme.MyColor
import com.mobilegame.spaceshooter.presentation.ui.screens.menuScreen.letters.*
import com.mobilegame.spaceshooter.presentation.ui.screens.utils.SpacerWithBackground
import com.mobilegame.spaceshooter.presentation.ui.screens.utils.chargingEffect.chargingScreen.ChargingScreen
import com.mobilegame.spaceshooter.utils.analyze.eLog

@Composable
fun MenuScreen(navigator: Navigator, vm: MenuScreenViewModel = viewModel()) {
    val currentMenu by remember { vm.currentMenu }.collectAsState()
    val navigate by remember { vm.navigate }.collectAsState()

//    LaunchedEffect("") {
    LaunchedEffect(navigate) {
        eLog("MenuScreen", "MenuScreen launch")
//        vm.initNav(navigator)
        if (navigate) vm.navigateToMainMenu(navigator)
    }

    Box(
        Modifier
            .fillMaxSize()
    ) {
        ChargingScreen(
            navigator = navigator,
            handler = vm.pressureVM,
            contentSize = vm.ui.getContentSize(currentMenu.text),
            screenSize = vm.ui.screenSize,
            startPadding = vm.ui.getStartPadding(currentMenu.text),
            endPadding = vm.ui.getEndPadding(currentMenu.text),
            topPadding = vm.ui.topPadding,
            bottomPadding = vm.ui.bottomPadding,
        ) {
            Row( modifier = Modifier .align(Alignment.Center) ) {
                for (char in currentMenu.text) {
                    when (char) {
                        'A' -> { DrawA( vm.ui ) }
                        'B' -> { DrawB( vm.ui ) }
                        'C' -> { DrawC( vm.ui ) }
                        'D' -> { DrawD( vm.ui ) }
                        'E' -> { DrawE( vm.ui ) }
                        'O' -> { DrawO( vm.ui ) }
                        'P' -> { DrawP( vm.ui ) }
                        'R' -> { DrawR( vm.ui ) }
                        'S' -> { DrawS( vm.ui ) }
                        'T' -> { DrawT( vm.ui ) }
                        'U' -> { DrawU( vm.ui ) }
                        'W' -> { DrawW( vm.ui ) }
                        else -> {
                            Box(Modifier.size(vm.ui.letterSizeDp).background(MyColor.applicationBackground))
//                            SpacerWithBackground(size = vm.ui.letterSpacerSizeDp)
//                            SpacerWithBackground(size = vm.ui.letterSpacerSizeDp)
                        }
                    }
                    SpacerWithBackground(size = vm.ui.letterSpacerSizeDp)
                }
            }
        }
        Icon(
            imageVector = Icons.Filled.KeyboardArrowLeft,
            contentDescription = "KeyboardArrowLeft",
            modifier = Modifier
                .alpha(0.65F)
                .align(Alignment.CenterStart)
                .size(50.dp)
                .clickable { vm.onLeftClick() }
            ,
            tint = MyColor.applicationContrast
        )
        Icon(
            imageVector = Icons.Filled.KeyboardArrowRight,
            contentDescription = "KeyboardArrowRight",
            modifier = Modifier
                .alpha(0.65F)
                .align(Alignment.CenterEnd)
                .size(50.dp)
                .clickable { vm.onRightClick() }
            ,
            tint = MyColor.applicationContrast
        )
    }
}