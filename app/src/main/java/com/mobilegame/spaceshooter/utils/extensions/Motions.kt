package com.mobilegame.spaceshooter.utils.extensions

import com.mobilegame.spaceshooter.logic.model.screen.inGameScreens.motions.Motions

infix fun Motions.not(motion: Motions): Boolean = this != motion

//fun Motions.isUp(): Boolean = this == Motions.UpLeft || this == Motions.UpRight
//fun Motions.isDown(): Boolean = this == Motions.DownLeft || this == Motions.DownRight
//
//fun Motions.isRight(): Boolean = this == Motions.UpRight || this == Motions.DownRight
//fun Motions.isLeft(): Boolean = this == Motions.UpLeft || this == Motions.UpRight

fun Motions.isUp(): Boolean = this == Motions.UpLeftSouth || this == Motions.UpRightSouth || this == Motions.UpLeftNorth || this == Motions.UpRightNorth
fun Motions.isDown(): Boolean = this == Motions.DownLeftSouth || this == Motions.DownRightSouth || this == Motions.DownRightNorth || this == Motions.DownLeftNorth

fun Motions.isRight(): Boolean = this.isRightNorth() || this.isRightSouth()
fun Motions.isLeft(): Boolean = this.isLeftNorth() || this.isLeftSouth()

//fun Motions.isSouth(): Boolean = this == Motions.DownLeftSouth || this == Motions.DownRightSouth || this == Motions.UpLeftSouth || this == Motions.UpRightSouth
//fun Motions.isNorth(): Boolean = this == Motions.DownLeftNorth || this == Motions.DownRightNorth || this == Motions.UpLeftNorth || this == Motions.UpRightNorth

fun Motions.isRightNorth(): Boolean = this == Motions.UpRightNorth || this == Motions.DownRightNorth
fun Motions.isRightSouth(): Boolean = this == Motions.UpRightSouth || this == Motions.DownRightSouth
fun Motions.isLeftNorth(): Boolean = this == Motions.UpLeftNorth || this == Motions.DownLeftNorth
fun Motions.isLeftSouth(): Boolean = this == Motions.UpLeftSouth || this == Motions.DownLeftSouth