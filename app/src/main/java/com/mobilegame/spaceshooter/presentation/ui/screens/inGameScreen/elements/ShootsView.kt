package com.mobilegame.spaceshooter.presentation.ui.screens.inGameScreen.elements

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.mobilegame.spaceshooter.logic.model.screen.inGameScreens.motions.MotionsViewModel
import com.mobilegame.spaceshooter.utils.analyze.iLog


@Composable
fun ShootsView(vm: MotionsViewModel) {
    val shoots by remember { vm.shootList }.collectAsState()

    shoots.forEach{
        Box(
            Modifier
                .wrapContentSize()
                .offset(it.offsetDp.x, it.offsetDp.y)
                .size(15.dp)
                .background(Color.Red)
        ) {
//            DefaultShip( vm.shipVM, vm.ui.spaceShip )
        }
    }
}