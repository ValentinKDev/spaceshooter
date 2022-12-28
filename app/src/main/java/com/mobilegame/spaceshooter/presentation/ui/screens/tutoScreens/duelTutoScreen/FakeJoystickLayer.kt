package com.mobilegame.spaceshooter.presentation.ui.screens.tutoScreens.duelTutoScreen

import androidx.compose.runtime.remember


import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.mobilegame.spaceshooter.logic.model.screen.inGameScreens.motions.Motions
import com.mobilegame.spaceshooter.logic.model.screen.tutoScreen.TutoScreenViewModel
import com.mobilegame.spaceshooter.presentation.theme.MyColor
import com.mobilegame.spaceshooter.presentation.ui.screens.utils.PaddingComposable

@Composable
fun FakeJoystickLayer(vm: TutoScreenViewModel) {
    val interactionSourceRight = remember { MutableInteractionSource() }
    val rightIsPressed by interactionSourceRight.collectIsPressedAsState()
    val interactionSourceLeft = remember { MutableInteractionSource() }
    val leftIsPressed by interactionSourceLeft.collectIsPressedAsState()
    val interactionSourceUp = remember { MutableInteractionSource() }
    val upIsPressed by interactionSourceUp.collectIsPressedAsState()
    val interactionSourceDown = remember { MutableInteractionSource() }
    val downIsPressed by interactionSourceDown.collectIsPressedAsState()

    LaunchedEffect(key1 = rightIsPressed || leftIsPressed || upIsPressed || downIsPressed) {
//        when {
//            rightIsPressed -> vm.gameVM.motionVM.motionChange(Motions.Right)
//            leftIsPressed -> vm.gameVM.motionVM.motionChange(Motions.Left)
//            upIsPressed -> vm.gameVM.motionVM.motionChange(Motions.Up)
//            downIsPressed -> vm.gameVM.motionVM.motionChange(Motions.Down)
//            else -> vm.gameVM.motionVM.motionChange(Motions.None)
//        }
    }

    val clickLeft = Modifier.clickable (
        interactionSource = interactionSourceLeft,
        indication = null,
        onClick = {

        }
    )
    val clickRight = Modifier.clickable (
        interactionSource = interactionSourceRight,
        indication = null,
        onClick = {}
    )
    val clickUp = Modifier.clickable (
        interactionSource = interactionSourceUp,
        indication = null,
        onClick = {}
    )
    val clickDown = Modifier.clickable (
        interactionSource = interactionSourceDown,
        indication = null,
        onClick = {}
    )

    PaddingComposable(
        startPaddingRatio = 0.05F,
        endPaddingRatio = 0.78F,
        topPaddingRatio = 0.3F,
        bottomPaddingRatio = 0.3F
    ) {
        Box(
            Modifier
                .fillMaxSize()
        ) {
            Box(
                Modifier
                    .size(100.dp)
            ) {
                Box(
                    Modifier
                        .align(Alignment.TopCenter)
                        .size(25.dp)
                        .then(clickUp)
                        .background(MyColor.applicationContrast) )
                Box(
                    Modifier
                        .align(Alignment.CenterStart)
                        .size(25.dp)
                        .then(clickLeft)
                        .background(MyColor.applicationContrast) )
                Box(
                    Modifier
                        .align(Alignment.CenterEnd)
                        .size(25.dp)
                        .then(clickRight)
                        .background(MyColor.applicationContrast) )
                Box(
                    Modifier
                        .align(Alignment.BottomCenter)
                        .size(25.dp)
                        .then(clickDown)
                        .background(MyColor.applicationContrast) )
            }
        }
    }
}