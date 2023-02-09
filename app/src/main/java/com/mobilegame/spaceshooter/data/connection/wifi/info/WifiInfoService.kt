package com.mobilegame.spaceshooter.data.connection.wifi.info

import com.mobilegame.spaceshooter.data.connection.wifi.PreparationState
import java.io.PrintWriter
import java.net.Socket

interface WifiInfoService {
    var name: String
    val state: PreparationState
    var socket: Socket
    val writer: PrintWriter
}