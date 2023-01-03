package com.mobilegame.spaceshooter.utils.extensions

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