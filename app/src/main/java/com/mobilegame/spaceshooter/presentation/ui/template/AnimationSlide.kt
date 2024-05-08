package com.mobilegame.spaceshooter.presentation.ui.template

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import com.mobilegame.spaceshooter.logic.uiHandler.template.AnimationSlideHandler
import com.mobilegame.spaceshooter.logic.uiHandler.template.LateralDirection

@Composable
fun AnimateSlide(
    handler: AnimationSlideHandler,
    visibility: Boolean,
    view: @Composable () -> Unit,
) {
    val fadeTime = remember { 300 }
    AnimatedVisibility(
        visible = visibility,
        enter = when (handler.direction) {
            LateralDirection.Left -> {
                slideInHorizontally {
                    -it/2
                } + fadeIn(tween(fadeTime))
            }
            LateralDirection.Right -> {
                slideInHorizontally {
                    it/2
                } + fadeIn(tween(fadeTime))
            }
        }
        ,
        exit = when (handler.direction) {
            LateralDirection.Left -> {
                slideOutHorizontally {
                    it/2
                } + fadeOut(tween(fadeTime))
            }
            LateralDirection.Right -> {
                slideOutHorizontally {
                    -it/2
                } + fadeOut(tween(fadeTime))
            }
        }
    ) {
        view.invoke()
    }
}
