package com.mobilegame.spaceshooter.presentation.ui.screens.inGameScreen.backGrounds

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.mobilegame.spaceshooter.logic.model.screen.inGameScreens.duelGameScreen.HitAnimationViewModel
import com.mobilegame.spaceshooter.logic.uiHandler.screens.games.background.BackgroundUI

@Composable
fun AnimateScreenBorders(hitVM: HitAnimationViewModel, ui: BackgroundUI) {
    val pairOfIsHitAndColor by remember { hitVM.visibleOpponentColor }.collectAsState()
    val infiniteTransition = rememberInfiniteTransition()
    val sizeRatio by infiniteTransition.animateFloat(
        initialValue = ui.anim.initialBorderSizeRatio,
        targetValue = ui.anim.targetBorderSizeRatio,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 70, easing = LinearEasing),
            repeatMode = RepeatMode.Reverse
        )
    )

    AnimatedVisibility(
        visible = pairOfIsHitAndColor.first,
//        enter = fadeIn( spring(stiffness = Spring.StiffnessMediumLow) ),
//        exit = fadeOut( spring(stiffness = Spring.StiffnessLow) ),
        enter = fadeIn(tween( 200)),
        exit = fadeOut(tween( 200)),
    ) {
        Box(Modifier.fillMaxSize()) {
            Box(
                modifier = Modifier
                    .fillMaxHeight()
                    .width(ui.anim.lateralBorderDp * sizeRatio)
                    .background(pairOfIsHitAndColor.second)
                    .align(Alignment.CenterEnd)
            ) { }
            Box(
                modifier = Modifier
                    .fillMaxHeight()
//                    .width(ui.anim.lateralBorderDp)
                    .width(ui.anim.lateralBorderDp * sizeRatio)
                    .background(pairOfIsHitAndColor.second)
                    .align(Alignment.CenterStart)
            ) { }
            Box(
                modifier = Modifier
                    .fillMaxWidth()
//                    .height(ui.anim.lateralBorderDp)
                    .height(ui.anim.lateralBorderDp * sizeRatio)
                    .background(pairOfIsHitAndColor.second)
                    .align(Alignment.BottomCenter)
            ) { }
        }
    }
}