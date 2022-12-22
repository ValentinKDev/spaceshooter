package com.mobilegame.spaceshooter.presentation.ui.screens.tutoScreens.duelTutoScreen.emulator

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.unit.dp
import com.mobilegame.spaceshooter.domain.model.screen.tutoScreen.TutoScreenViewModel
import com.mobilegame.spaceshooter.presentation.theme.MyColor
import com.mobilegame.spaceshooter.presentation.ui.screens.utils.CenterComposable

@Composable
fun SmartphoneSkin(vm: TutoScreenViewModel) {
    Box {
        Canvas(
            Modifier
                .fillMaxSize()
                .graphicsLayer(alpha = 0.99f)
        ) {
            drawRect(
                color = MyColor.applicationBackground,
                size = size,
                blendMode = BlendMode.Xor
            )
            drawRect(
                color = Color.Black,
                topLeft = vm.ui.smartphoneEmulator.points.screenTopLeft,
                size = Size(vm.ui.smartphoneEmulator.sizes.screenWidth, vm.ui.smartphoneEmulator.sizes.screenHeight),
                blendMode = BlendMode.Xor
            )
        }
        CenterComposable {
            Box(
                Modifier
                    .height(vm.ui.smartphoneEmulator.sizes.screenHeightDp)
                    .width(vm.ui.smartphoneEmulator.sizes.screenWidthDp)
                    .border(
                        width = vm.ui.smartphoneEmulator.sizes.borderDp,
                        shape = RoundedCornerShape(2.dp),
                        color = MyColor.Platinium
                    )
            )
        }
        CenterComposable {
            Box(
                Modifier
                    .fillMaxSize()
                    .border(
                        width = vm.ui.smartphoneEmulator.sizes.borderDp,
                        shape = RoundedCornerShape(4.dp),
                        color = MyColor.Platinium
                    )
            )
        }
    }
}