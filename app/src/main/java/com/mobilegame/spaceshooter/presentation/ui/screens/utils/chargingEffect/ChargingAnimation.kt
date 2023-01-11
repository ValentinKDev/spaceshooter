package com.mobilegame.spaceshooter.presentation.ui.screens.utils.chargingEffect

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import com.mobilegame.spaceshooter.presentation.theme.MyColor


@Composable
fun ChargingAnimation(isPressed: Boolean, timer: Int ) {
    val minWeight = remember { 0.001F }
    val maxWeight = remember { 0.999F }
    val heightWeight by animateFloatAsState(
        targetValue = if (isPressed) maxWeight else minWeight,
        animationSpec = tween(timer)
    )
    Column(Modifier.fillMaxSize()) {
        Box(
            Modifier
                .fillMaxWidth()
                .weight(1F - heightWeight)
        )
        Box(
            Modifier
                .fillMaxWidth()
                .weight(heightWeight)
                .background(MyColor.applicationContrast)
        )
    }

//    Column(Modifier.fillMaxSize()) {
//        Box(
//            Modifier
//                .fillMaxWidth()
//                .weight(maxWeight)
//        )
//        Box(
//            Modifier
//                .fillMaxWidth()
//                .weight(minWeight)
//                .background(MyColor.applicationBackground)
//        )
//    }
}
