package com.mobilegame.spaceshooter.presentation.ui.screens.inGameScreen.elements

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.mobilegame.spaceshooter.logic.model.screen.inGameScreens.duelGameScreen.DuelGameViewModel
import com.mobilegame.spaceshooter.presentation.ui.screens.inGameScreen.elements.spaceShips.cercle.DrawMunition
import com.mobilegame.spaceshooter.presentation.ui.screens.inGameScreen.elements.spaceShips.cercle.MunitionsType
import com.mobilegame.spaceshooter.utils.extensions.toOffset


@Composable
fun ShootsView(vm: DuelGameViewModel) {
    val shoots by remember { vm.shipVM.motionVM.shootList }.collectAsState()

    shoots.forEach{
//        Box(
//            Modifier
//                .wrapContentSize()
//                .offset(it.offsetDp.x, it.offsetDp.y)
////                .size(15.dp)
//                .background(Color.Red)
//        ) {
//        }
        DrawMunition(center = it.offsetDp.toOffset(), ui = vm.ui.spaceShip, type = MunitionsType.Shoot)
    }
}