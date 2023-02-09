package com.mobilegame.spaceshooter.data.connection.wifi.info

import com.mobilegame.spaceshooter.data.connection.wifi.PreparationState
import java.io.PrintWriter
import java.net.Socket

class WifiServer(
    override var name: String,
    override val state: PreparationState,
    override var socket: Socket,
    override val writer: PrintWriter,
): WifiInfoService {
}
