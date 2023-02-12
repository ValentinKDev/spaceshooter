package com.mobilegame.spaceshooter.utils.extensions

import com.mobilegame.spaceshooter.data.connection.wifi.info.WifiInfoService
import com.mobilegame.spaceshooter.logic.model.screen.connection.wifiScreen.channel.WifiChannel

suspend fun List<WifiChannel>.open(info: WifiInfoService) = this.find { it.info == info }?.open()
suspend fun List<WifiChannel>.open(channel: WifiChannel) = this.get(channel)?.open()

fun List<WifiChannel>.close(info: WifiInfoService) = this.find { it.info == info }?.let { this.close(it) }
fun List<WifiChannel>.close(channel: WifiChannel) = this.get(channel)?.let { _channel ->
    _channel.close()
    _channel.info?.let {
        it.socket.close()
        it.writer.close()
    }
}
fun List<WifiChannel>.closeAll() = this.forEach {
    it.close()
    it.info?.let {
        it.socket.close()
        it.writer.close()
    }
}

fun MutableList<WifiChannel>.add(info: WifiInfoService) = this.add(WifiChannel(info))