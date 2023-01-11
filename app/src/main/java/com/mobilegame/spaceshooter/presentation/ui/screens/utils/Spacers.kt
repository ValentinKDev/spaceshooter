package com.mobilegame.spaceshooter.presentation.ui.screens.utils

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.DpSize
import com.mobilegame.spaceshooter.presentation.theme.MyColor

@Composable
fun SpacerWithBackground(size: DpSize) {
    Spacer(modifier = Modifier.background(MyColor.applicationBackground).size(size))
}
