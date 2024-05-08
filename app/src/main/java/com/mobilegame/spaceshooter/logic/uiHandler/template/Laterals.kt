package com.mobilegame.spaceshooter.logic.uiHandler.template

import com.mobilegame.spaceshooter.logic.uiHandler.template.LateralMovement.*

enum class LateralMovement {
    LeftToLeft, LeftToRight, RightToLeft, RightToRight, None
}

enum class LateralDirection {
    Left, Right
}

fun LateralMovement.after(direction: LateralDirection): LateralMovement = when (this) {
    LeftToLeft -> { if (direction == LateralDirection.Left) LeftToLeft else LeftToRight }
    LeftToRight -> { if (direction == LateralDirection.Right) RightToRight else RightToLeft }
    RightToLeft -> { if (direction == LateralDirection.Left) LeftToLeft else LeftToRight }
    RightToRight -> { if (direction == LateralDirection.Right) RightToRight else RightToLeft }
    None -> { if (direction == LateralDirection.Right) LeftToRight else RightToLeft }
}
