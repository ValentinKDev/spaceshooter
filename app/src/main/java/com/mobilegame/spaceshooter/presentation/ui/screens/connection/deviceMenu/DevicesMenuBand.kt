package com.mobilegame.spaceshooter.presentation.ui.screens.connection.deviceMenu

import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.layoutId
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.ConstraintSet
import androidx.constraintlayout.compose.Dimension
import com.mobilegame.spaceshooter.data.connection.wifi.info.WifiClient
import com.mobilegame.spaceshooter.data.connection.wifi.info.WifiInfoService
import com.mobilegame.spaceshooter.logic.model.screen.connection.ConnectedDevice
import com.mobilegame.spaceshooter.logic.uiHandler.screens.connections.DevicesMenuUI
import com.mobilegame.spaceshooter.presentation.theme.MyColor
import java.net.InetAddress

@Composable
fun DeviceMenuBand(
    ui: DevicesMenuUI.BandDeviceMenu,
    deviceName: String,
    facingDevices: List<ConnectedDevice>,
    facingDeviceIndex: Int,
) {
    val constraints = remember {
        ConstraintSet {
            val myNameText = createRefFor(ui.ids.myNameText)
            val connectedDeviceNameText = createRefFor(ui.ids.connectedDeviceText)
            val titleBox = createRefFor(ui.ids.titleBox)
            val wins = createRefFor(ui.ids.winText)
            val loses = createRefFor(ui.ids.losesText)
            val streak = createRefFor(ui.ids.streakText)
            val winsNb = createRefFor(ui.ids.winNumber)
            val losesNb = createRefFor(ui.ids.losesNumber)
            val streakNb = createRefFor(ui.ids.streakNumber)

            constrain(myNameText) {
                top.linkTo(parent.top)
                start.linkTo(parent.start)
                bottom.linkTo(connectedDeviceNameText.top)
                height = Dimension.wrapContent
                width = Dimension.wrapContent
            }
            constrain(connectedDeviceNameText) {
                top.linkTo(myNameText.bottom)
                start.linkTo(parent.start)
                bottom.linkTo(parent.bottom)
                height = Dimension.wrapContent
                width = Dimension.wrapContent
            }

            constrain(titleBox) {
                top.linkTo(parent.top)
                end.linkTo(parent.end)
                bottom.linkTo(parent.bottom)
                height = Dimension.fillToConstraints
                width = Dimension.percent(0.15F)
            }
            constrain(wins) {
                top.linkTo(titleBox.top)
                start.linkTo(titleBox.start)
                end.linkTo(titleBox.end)
                bottom.linkTo(loses.top)
                height = Dimension.fillToConstraints
                width = Dimension.wrapContent
            }
            constrain(loses) {
                top.linkTo(wins.bottom)
                start.linkTo(titleBox.start)
                end.linkTo(titleBox.end)
                bottom.linkTo(streak.top)
                height = Dimension.fillToConstraints
                width = Dimension.wrapContent
            }
            constrain(streak) {
                top.linkTo(loses.bottom)
                start.linkTo(titleBox.start)
                end.linkTo(titleBox.end)
                bottom.linkTo(titleBox.bottom)
                height = Dimension.fillToConstraints
                width = Dimension.wrapContent
            }

            constrain(winsNb) {
                top.linkTo(titleBox.top)
                end.linkTo(titleBox.start)
                bottom.linkTo(loses.top)
                height = Dimension.fillToConstraints
                width = Dimension.wrapContent
            }
            constrain(losesNb) {
                top.linkTo(wins.bottom)
                end.linkTo(titleBox.start)
                bottom.linkTo(streak.top)
                height = Dimension.fillToConstraints
                width = Dimension.wrapContent
            }
            constrain(streakNb) {
                top.linkTo(loses.bottom)
                end.linkTo(titleBox.start)
                bottom.linkTo(titleBox.bottom)
                height = Dimension.fillToConstraints
                width = Dimension.wrapContent
            }
        }
    }

    ConstraintLayout(constraints, Modifier.fillMaxSize()) {
        Text(
            modifier = Modifier.layoutId(ui.ids.myNameText)
            ,
            text = "$deviceName VS",
            color = MyColor.applicationText
        )
        Text(
            modifier = Modifier.layoutId(ui.ids.connectedDeviceText)
            ,
//            text = facingDevices[facingDeviceIndex].name,
            text = facingDevices[facingDeviceIndex].name,
//            text = facingDevices.values.first(),
            color = MyColor.applicationText
        )

        Box( Modifier .layoutId(ui.ids.titleBox) ) { }
        Text(
            modifier = Modifier.layoutId(ui.ids.winText)
            ,
            text = ui.ids.winText,
            color = MyColor.applicationText
        )
        Text(
            modifier = Modifier.layoutId(ui.ids.losesText)
            ,
            text = ui.ids.losesText,
            color = MyColor.applicationText
        )
        Text(
            modifier = Modifier.layoutId(ui.ids.streakText)
            ,
            text = ui.ids.streakText,
            color = MyColor.applicationText
        )

        Text(
            modifier = Modifier.layoutId(ui.ids.winNumber)
            ,
            text = "0",
            color = MyColor.applicationText
        )
        Text(
            modifier = Modifier.layoutId(ui.ids.losesNumber)
            ,
            text = "0",
            color = MyColor.applicationText
        )
        Text(
            modifier = Modifier.layoutId(ui.ids.streakNumber)
            ,
            text = "0",
            color = MyColor.applicationText
        )
    }
}