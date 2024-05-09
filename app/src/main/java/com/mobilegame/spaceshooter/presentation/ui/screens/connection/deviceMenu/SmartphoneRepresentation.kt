package com.mobilegame.spaceshooter.presentation.ui.screens.connection.deviceMenu

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.mobilegame.spaceshooter.presentation.theme.MyColor

@Composable
fun SmartphoneRepresentation() {
    Box(
        Modifier
            .height(50.dp)
            .width(80.dp)
            .clip(RoundedCornerShape(7.dp))
            .background(MyColor.applicationContrast)
    ) {
        Box(
            Modifier
                .align(Alignment.Center)
                .height(47.dp)
                .width(77.dp)
                .clip(RoundedCornerShape(5.dp))
                .background(MyColor.applicationBackground)
        ) {
            Box(
                Modifier
                    .height(40.dp)
                    .width(65.dp)
                    .align(Alignment.Center)
                    .clip(RoundedCornerShape(2.dp))
                    .border(BorderStroke(1.dp, MyColor.applicationContrast))
                    .background(Color.Black)
            ) { }
        }
    }
}