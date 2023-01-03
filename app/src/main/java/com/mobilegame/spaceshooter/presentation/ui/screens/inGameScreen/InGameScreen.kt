package com.mobilegame.spaceshooter.presentation.ui.screens.inGameScreen

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.animateOffsetAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.unit.DpOffset
import com.mobilegame.spaceshooter.logic.model.screen.inGameScreens.duelInGameScreen.DuelInGameViewModel
import com.mobilegame.spaceshooter.logic.model.screen.inGameScreens.motions.MotionLR
import com.mobilegame.spaceshooter.presentation.ui.navigation.Navigator
import com.mobilegame.spaceshooter.presentation.ui.screens.utils.spaceShips.DefaultShip
import com.mobilegame.spaceshooter.utils.analyze.eLog
import com.mobilegame.spaceshooter.utils.extensions.toDpOffset
import com.mobilegame.spaceshooter.utils.extensions.toOffset


@Composable
fun InGameScreen(navigator: Navigator = Navigator(), vm: DuelInGameViewModel) {
//    val position by remember { vm.shipVM.pCenterDp }.collectAsState()
    val position by remember { vm.motionVM.shipPosition }.collectAsState()
    val motionLR by remember { vm.motionVM.motionLR }.collectAsState()

    LaunchedEffect(true) {
        eLog("InGameScreen", "Launch Screen")
    }

//    val animOffset by animateOffsetAsState(
//        targetValue = position.toOffset(),
//        animationSpec = tween(10)
//    )
//    val animOffSetDp = animOffset.toDpOffset()
    val angle by animateFloatAsState(
        targetValue = when (motionLR) {
            MotionLR.None -> 0F
            MotionLR.Right -> 8F
            MotionLR.Left -> -8F
        }
        ,
        animationSpec = tween(400)
    )


    Box( Modifier.fillMaxSize() ) {
        Box(
            Modifier
                .wrapContentSize()
                .offset(position.x, position.y)
//                .offset(animOffSetDp.x, animOffSetDp.y)
                .rotate(angle)
        ) {
            DefaultShip( vm.shipVM, vm.ui.spaceShip )
        }
    }
}