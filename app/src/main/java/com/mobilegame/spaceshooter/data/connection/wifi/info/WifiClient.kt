package com.mobilegame.spaceshooter.data.connection.wifi.info

import com.mobilegame.spaceshooter.data.connection.wifi.PreparationState
import java.io.PrintWriter
import java.net.Socket

data class WifiClient(
    override var name: String,
    override val state: PreparationState,
    override var socket: Socket,
    override val writer: PrintWriter,
): WifiInfoService

