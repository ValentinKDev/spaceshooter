package com.mobilegame.spaceshooter.logic.model.screen.connection

import com.mobilegame.spaceshooter.data.connection.wifi.PreparationState
import java.net.InetAddress

data class ConnectedDevice(
    val name: String,
    val ip: InetAddress,
    val state: PreparationState = PreparationState.Waiting
)