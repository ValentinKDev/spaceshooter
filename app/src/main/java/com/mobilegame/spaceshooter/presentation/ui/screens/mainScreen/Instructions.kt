package com.mobilegame.spaceshooter.presentation.ui.screens.mainScreen

import androidx.compose.animation.animateColor
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import com.mobilegame.spaceshooter.domain.model.screen.mainScreen.MainScreenViewModel
import com.mobilegame.spaceshooter.presentation.ui.screens.utils.AlignComposableToBottom
import com.mobilegame.spaceshooter.presentation.ui.screens.utils.CenterComposable
import com.mobilegame.spaceshooter.presentation.ui.screens.utils.CenterComposableHorizontally
import com.mobilegame.spaceshooter.presentation.ui.screens.utils.PaddingComposable
import com.mobilegame.spaceshooter.utils.extensions.alpha

@Composable
internal fun Instructions(vm: MainScreenViewModel) {
    val infiniteTransition = rememberInfiniteTransition()
    val animateColor by infiniteTransition.animateColor(
        initialValue = vm.ui.instruction.color.initialColor,
        targetValue = vm.ui.instruction.color.targetColor,
        animationSpec = infiniteRepeatable(
            tween(durationMillis = 1500, easing = FastOutLinearInEasing),
            RepeatMode.Reverse
        )
    )
    
    PaddingComposable(
        startPaddingRatio = vm.ui.instruction.padding.start,
        endPaddingRatio = vm.ui.instruction.padding.end,
        topPaddingRatio = vm.ui.instruction.padding.top,
        bottomPaddingRatio = vm.ui.instruction.padding.bottom,
    ) {
        Box(
            Modifier
                .fillMaxSize()
//                .background(Color.Blue.alpha(0.2F))
        ) {
            AlignComposableToBottom {
                CenterComposableHorizontally {
                    Text(
                        text = vm.ui.instruction.text.message,
                        color = animateColor,
                        style = TextStyle(
                            fontSize = vm.ui.instruction.sizes.textSp,
                            fontWeight = FontWeight.SemiBold
                        ),
                        textAlign = TextAlign.Center
                    )
                }
            }
        }
    }
}