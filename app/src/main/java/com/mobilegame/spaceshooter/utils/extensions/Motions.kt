package com.mobilegame.spaceshooter.utils.extensions

import android.mtp.MtpConstants
import com.mobilegame.spaceshooter.logic.model.screen.inGameScreens.motions.MotionLR
import com.mobilegame.spaceshooter.logic.model.screen.inGameScreens.motions.Motions

infix fun Motions.not(motion: Motions): Boolean = this != motion

fun Motions.toMotionLR(): MotionLR = when (this) {
//    Motions.DownLeft, Motions.UpLeft -> MotionLR.Left
//    Motions.DownRight, Motions.UpRight -> MotionLR.Right
    Motions.DownLeftSouth, Motions.DownLeftNorth, Motions.UpLeftNorth, Motions.UpLeftSouth -> MotionLR.Left
    Motions.DownRightNorth, Motions.DownRightSouth, Motions.UpRightNorth, Motions.UpRightSouth -> MotionLR.Right
    Motions.None -> MotionLR.None
}

fun Motions.isUp(): Boolean = this == Motions.UpLeftSouth || this == Motions.UpRightSouth || this == Motions.UpLeftNorth || this == Motions.UpRightNorth
fun Motions.isDown(): Boolean = this == Motions.DownLeftSouth || this == Motions.DownRightSouth || this == Motions.DownRightNorth || this == Motions.DownLeftNorth

fun Motions.isRight(): Boolean = this.isRightNorth() || this.isRightSouth()
fun Motions.isLeft(): Boolean = this.isLeftNorth() || this.isLeftSouth()

fun Motions.isRightNorth(): Boolean = this == Motions.UpRightNorth || this == Motions.DownRightNorth
fun Motions.isRightSouth(): Boolean = this == Motions.UpRightSouth || this == Motions.DownRightSouth
fun Motions.isLeftNorth(): Boolean = this == Motions.UpLeftNorth || this == Motions.DownLeftNorth
fun Motions.isLeftSouth(): Boolean = this == Motions.UpLeftSouth || this == Motions.DownLeftSouth

//private fun Motions.isDownSouth(): Boolean = this == Motions.DownLeftSouth