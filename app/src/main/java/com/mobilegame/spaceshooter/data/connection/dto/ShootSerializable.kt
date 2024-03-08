package com.mobilegame.spaceshooter.data.connection.dto

import com.mobilegame.spaceshooter.presentation.ui.screens.inGameScreen.elements.spaceShips.circle.MunitionsType
import java.net.InetAddress

data class ShootSerializable(
    val type: String,
    val from: MunitionsType,
    val shooterIp: InetAddress?,
//    val motion: Motions,
    var vector: String,
    val particularBehavior: Int,
    val xRatio: Float,
    val yRatio: Float,
    val damage: Float,
    var offsetDp: String
)
