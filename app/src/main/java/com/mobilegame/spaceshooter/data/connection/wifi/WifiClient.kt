package com.mobilegame.spaceshooter.data.connection.wifi

import java.io.PrintWriter
import java.net.Socket

data class WifiClient(val socket: Socket, val name: String, val writer: PrintWriter)
