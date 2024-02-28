package com.mobilegame.spaceshooter.utils.extensions

import com.mobilegame.spaceshooter.data.connection.wifi.info.WifiInfoService
import com.mobilegame.spaceshooter.logic.model.screen.connection.wifiScreen.channel.WifiChannel
import java.net.InetAddress

//fun List<WifiInfoService>.get(inetAddress: InetAddress): WifiInfoService? = this.find { it.socket.inetAddress == inetAddress }
fun List<WifiChannel>.getInfo(inetAddress: InetAddress): WifiInfoService? = this.find { it.info?.socket?.localAddress == inetAddress }?.info
//fun List<WifiChannel>.getInfo(inetAddress: InetAddress): WifiInfoService? = this.find { it.info?.socket?.localAddress == inetAddress }?.info
