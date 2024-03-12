package com.mobilegame.spaceshooter.logic.model.screen.inGameScreens.duelGameScreen

import java.net.InetAddress
import java.util.Date

data class LooseInfo(
    val shooterIp: InetAddress,
    val shooterName: String,
    val deadPlayerName: String,
    val deadPlayerIp: InetAddress,
    val exactMoment: String,
)
