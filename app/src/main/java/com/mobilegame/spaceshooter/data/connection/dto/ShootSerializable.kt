package com.mobilegame.spaceshooter.data.connection.dto

import com.mobilegame.spaceshooter.presentation.ui.screens.inGameScreen.elements.spaceShips.types.circle.MunitionsType
import java.net.InetAddress

data class ShootSerializable(
    val typeName: String,
    val from: MunitionsType,
    val shooterIp: InetAddress,
//    val motion: Motions,
    var vector: String,
    val particularBehavior: Int,
    val xRatio: Float,
    val yRatio: Float,
    val damage: Float,
    val hitBox: Float,
    var offsetDp: String,
    val laserOnUser: String,
    val laserOnOpponent: String,
)
