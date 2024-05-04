package com.mobilegame.spaceshooter.presentation.ui.template.backButton

import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.drawscope.Fill
import com.mobilegame.spaceshooter.logic.model.navigation.PressureHandler
import com.mobilegame.spaceshooter.logic.uiHandler.template.BackButtonUI
import com.mobilegame.spaceshooter.presentation.ui.screens.utils.CenterComposableVertically
import com.mobilegame.spaceshooter.presentation.ui.screens.utils.ChargingButton
import com.mobilegame.spaceshooter.presentation.ui.screens.utils.PaddingComposable
import com.mobilegame.spaceshooter.presentation.ui.screens.utils.chargingEffect.chargingButton.ChargingButtonInstant

@Composable
fun BackButton(
    handler: PressureHandler,
//    navigator: Navigator,
    ui: BackButtonUI,
//    backNav: Screens,
) {
//    val startBackJob by remember { vm.full }.collectAsState()
    val instant = remember { ui.instant }

    LaunchedEffect(true) {
        Log.i("BackButton", "BackButton: instant $instant")
    }
//    LaunchedEffect(startBackJob) {
//        Log.i("BackButton", "BackButton: startBackJob $startBackJob")
//        if (startBackJob) navigator.navig(backNav)
//    }

    PaddingComposable(
        startPaddingRatio = ui.padding.start
    ) {
        CenterComposableVertically(ui.id) {
            if (instant)  {
                ChargingButtonInstant(
                    handler = handler,
                    sizeDp = ui.sizes.canvasSizeDp,
                    contentUncharged = { BackIconOutlineOrFilled(ui = ui, style = ui.stroke) },
                    contentCharged = { BackIconOutlineOrFilled(ui = ui, style = Fill) }
                )
            } else {
                ChargingButton(
                    handler = handler,
                    sizeDp = ui.sizes.canvasSizeDp,
                    roundShape = true,
                    content = {
                        Box(
                            Modifier
                                .fillMaxHeight()
                        ) {
                            BackIcon(ui)
                        }
                    },
                )
            }
        }
    }
}