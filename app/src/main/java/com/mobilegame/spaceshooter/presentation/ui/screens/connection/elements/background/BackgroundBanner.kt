package com.mobilegame.spaceshooter.presentation.ui.screens.connection.elements.background

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.ConstraintSet
import androidx.constraintlayout.compose.Dimension
import com.mobilegame.spaceshooter.logic.uiHandler.Icons.BtWifiType
import com.mobilegame.spaceshooter.logic.uiHandler.Icons.WifiIconUI
import com.mobilegame.spaceshooter.logic.uiHandler.screens.connections.banner.ConnectionScreenBannerUIService
import com.mobilegame.spaceshooter.presentation.ui.screens.connection.wifiScreen.elements.WifiBanner
import com.mobilegame.spaceshooter.utils.extensions.alpha

@Composable
fun BackgroundBanner(ui: ConnectionScreenBannerUIService) {
    val constraints = remember {
        ConstraintSet {
            val banner = createRefFor(ui.bannerID)
            val bannerTopPadding = createRefFor(ui.bannerTopPadding)
            val bannerEndPadding = createRefFor(ui.bannerEndPadding)

            constrain(bannerTopPadding) {
                top.linkTo(parent.top)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
                height = Dimension.percent(ui.topPaddingPercent)
                width = Dimension.fillToConstraints
            }
            constrain(bannerEndPadding) {
                top.linkTo(parent.top)
                end.linkTo(parent.end)
                bottom.linkTo(parent.bottom)
                height = Dimension.fillToConstraints
                width = Dimension.percent(ui.endPaddingPercent)
            }
            constrain(banner) {
                top.linkTo(bannerTopPadding.bottom)
                end.linkTo(bannerEndPadding.start)
                height = Dimension.wrapContent
                width = Dimension.wrapContent
            }
        }
    }

    ConstraintLayout(constraints, modifier = Modifier.fillMaxSize()) {
        Box( Modifier .layoutId(ui.bannerTopPadding) )
        Box( Modifier .layoutId(ui.bannerEndPadding) )
        Box(
            Modifier
                .layoutId(ui.bannerID)
        ) {
            when (ui.type) {
                BtWifiType.Wifi ->  WifiBanner(ui = ui.iconUI as WifiIconUI)
                BtWifiType.Bluetooth -> {}
            }
        }
    }
}