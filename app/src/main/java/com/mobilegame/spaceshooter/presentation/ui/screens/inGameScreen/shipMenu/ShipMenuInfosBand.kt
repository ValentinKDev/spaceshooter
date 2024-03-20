package com.mobilegame.spaceshooter.presentation.ui.screens.inGameScreen.shipMenu

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.layoutId
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.ConstraintSet
import androidx.constraintlayout.compose.Dimension
import com.mobilegame.spaceshooter.logic.model.screen.inGameScreens.ship.types.ShipType
import com.mobilegame.spaceshooter.logic.uiHandler.screens.connections.ShipMenuUI
import com.mobilegame.spaceshooter.presentation.theme.MyColor


//@Composable
//fun ShipMenuInfoBand(ui: ShipMenuUI.BandShipMenu, shipType: ShipType) {
//    val constraints = remember {
//        ConstraintSet {
//            val shipName = createRefFor(ui.ids.shipName)
//            val statsTextBox = createRefFor(ui.ids.statsTextBox)
//            val healthText = createRefFor(ui.ids.healthText)
//            val healthBar = createRefFor(ui.ids.healthBar)
//            val speedText = createRefFor(ui.ids.speedText)
//            val speedBar = createRefFor(ui.ids.speedBar)
//            val damageText = createRefFor(ui.ids.damageText)
//            val damageBar = createRefFor(ui.ids.damageBar)
//            val reloadText = createRefFor(ui.ids.reloadText)
//            val reloadBar = createRefFor(ui.ids.reloadBar)
//
//            constrain(shipName) {
//                top.linkTo(parent.top)
//                start.linkTo(parent.start)
//                bottom.linkTo(parent.bottom)
//                height = Dimension.fillToConstraints
//            }
//            constrain(statsTextBox) {
//                top.linkTo(parent.top)
//                bottom.linkTo(parent.bottom)
//                end.linkTo(parent.end)
//                height = Dimension.fillToConstraints
//            }
//            constrain(healthText) {
//                top.linkTo(statsTextBox.top)
//                start.linkTo(statsTextBox.start)
//                end.linkTo(statsTextBox.end)
//                bottom.linkTo(speedText.top)
//                height = Dimension.fillToConstraints
//            }
//            constrain(speedText) {
//                top.linkTo(healthText.bottom)
//                start.linkTo(statsTextBox.start)
//                end.linkTo(statsTextBox.end)
//                bottom.linkTo(damageText.top)
//                height = Dimension.fillToConstraints
//            }
//            constrain(damageText) {
//                top.linkTo(speedText.bottom)
//                start.linkTo(statsTextBox.start)
//                end.linkTo(statsTextBox.end)
//                bottom.linkTo(reloadText.top)
//                height = Dimension.fillToConstraints
//            }
//            constrain(reloadText) {
//                top.linkTo(damageText.bottom)
//                start.linkTo(statsTextBox.start)
//                end.linkTo(statsTextBox.end)
//                bottom.linkTo(statsTextBox.bottom)
//                height = Dimension.fillToConstraints
//            }
//        }
//    }
//    ConstraintLayout(constraints,
//        Modifier
//            .fillMaxSize()
//            .background(MyColor.applicationBackground)) {
//        Text(
//            modifier = Modifier.layoutId(ui.ids.shipName) ,
//            text = "_ship_name",
//            color = MyColor.applicationText
//        )
//        Text(
//            modifier = Modifier.layoutId(ui.ids.healthText) ,
//            text = ui.ids.healthText,
//            color = MyColor.applicationText
//        )
//        Text(
//            modifier = Modifier.layoutId(ui.ids.speedText) ,
//            text = ui.ids.speedText,
//            color = MyColor.applicationText
//        )
//        Text(
//            modifier = Modifier.layoutId(ui.ids.damageText) ,
//            text = ui.ids.damageText,
//            color = MyColor.applicationText
//        )
//        Text(
//            modifier = Modifier.layoutId(ui.ids.reloadText) ,
//            text = ui.ids.reloadText,
//            color = MyColor.applicationText
//        )
//    }
//}