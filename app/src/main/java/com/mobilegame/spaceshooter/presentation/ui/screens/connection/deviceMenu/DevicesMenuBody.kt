package com.mobilegame.spaceshooter.presentation.ui.screens.connection.deviceMenu

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.ConstraintSet
import androidx.constraintlayout.compose.Dimension
import com.mobilegame.spaceshooter.logic.model.screen.pression.PressureReadyViewModel
import com.mobilegame.spaceshooter.logic.uiHandler.screens.connections.DevicesMenuUI
import com.mobilegame.spaceshooter.presentation.theme.MyColor
import com.mobilegame.spaceshooter.presentation.ui.navigation.Navigator
import com.mobilegame.spaceshooter.presentation.ui.screens.utils.ChargingButton
import com.mobilegame.spaceshooter.presentation.ui.screens.utils.chargingEffect.ChargingAnimation
import com.mobilegame.spaceshooter.presentation.ui.screens.utils.chargingEffect.chargingButton.FilterRoundShape

@Composable
fun DevicesMenuBody(
    navigator: Navigator,
    ui: DevicesMenuUI.BodyDeviceMenu,
    pressureVM: PressureReadyViewModel
) {
    val constraints = remember {
        ConstraintSet {
            val instruction = createRefFor(ui.ids.instruction)
            val smartphoneIcon = createRefFor(ui.ids.smartphoneIcon)
            val navBar = createRefFor(ui.ids.smartphoneIcon)

            constrain(instruction) {
                top.linkTo(parent.top)
                end.linkTo(parent.end)
                height = Dimension.wrapContent
                width = Dimension.wrapContent
            }
            constrain(smartphoneIcon) {
                top.linkTo(parent.top)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
                bottom.linkTo(parent.bottom)
                height = Dimension.wrapContent
                width = Dimension.wrapContent
            }
            constrain(navBar) {
                start.linkTo(parent.start)
                end.linkTo(parent.end)
                bottom.linkTo(parent.bottom)
                height = Dimension.wrapContent
                width = Dimension.wrapContent
            }
        }
    }

    ConstraintLayout(constraints, Modifier.fillMaxSize()) {
        Text(
            modifier = Modifier.layoutId(ui.ids.instruction),
            text = ui.ids.instruction,
            color = MyColor.applicationText
        )
        Box(
            Modifier
                .layoutId(ui.ids.smartphoneIcon)
                .height(50.dp)
                .width(75.dp)
                .background(Color.Gray)
        ) { }
        Box(
            Modifier
                .layoutId(ui.ids.navBar)
        ) {
        }
    }

    ChargingButton(handler = pressureVM, sizeDp = ui.sizeDp, navigator = navigator, alphaAnimation = 0.2F) {
        Box(Modifier.fillMaxSize())
    }
}