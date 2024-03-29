package com.mobilegame.spaceshooter.presentation.ui.screens.menuScreen.letters

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import com.mobilegame.spaceshooter.logic.uiHandler.screens.menu.MenuScreenUI

@Composable
fun DrawU(menuUI: MenuScreenUI) {
    Box( Modifier .wrapContentSize() .rotate(270F) ){
        DrawC(menuUI)
    }
}
