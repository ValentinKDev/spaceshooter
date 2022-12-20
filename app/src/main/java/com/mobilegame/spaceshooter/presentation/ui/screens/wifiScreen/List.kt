package com.mobilegame.spaceshooter.presentation.ui.screens.wifiScreen

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Wifi
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.unit.dp
import com.mobilegame.spaceshooter.domain.model.screen.wifiScreen.WifiScreenViewModel
import com.mobilegame.spaceshooter.presentation.theme.MyColor
import com.mobilegame.spaceshooter.presentation.ui.screens.mainScreen.elements.buttons.WifiButton.WifiIcon.WifiIcon
import com.mobilegame.spaceshooter.presentation.ui.screens.utils.AlignComposableToEnd
import com.mobilegame.spaceshooter.presentation.ui.screens.utils.AlignComposableToStart
import com.mobilegame.spaceshooter.presentation.ui.screens.utils.CenterComposable
import com.mobilegame.spaceshooter.presentation.ui.screens.utils.PaddingComposable
import com.mobilegame.spaceshooter.presentation.ui.screens.wifiScreen.elements.WifiBanner
import com.mobilegame.spaceshooter.utils.extensions.alpha

@Composable
internal fun List(vm: WifiScreenViewModel) {
    PaddingComposable(
//        topPaddingRatio = 0.2F,
        topPaddingRatio = 0.4F,
    ) {
        WifiBanner(ui = vm.ui.banner.wifiIcon)
    }
}