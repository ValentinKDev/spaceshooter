package com.mobilegame.spaceshooter.presentation.ui.screens.inGameScreen.backGrounds

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import com.mobilegame.spaceshooter.presentation.ui.screens.inGameScreen.elements.spaceShips.DefaultShip

@Composable
fun DefaultSpaceBackGround(content: @Composable () -> Unit) {
    Box(
        Modifier
            .fillMaxSize()
            .background(Color.Black)
    ) {
        content.invoke()
    }
}