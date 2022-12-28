package com.mobilegame.spaceshooter.utils.extensions

import com.mobilegame.spaceshooter.logic.model.screen.inGameScreens.motions.Motions

infix fun Motions.not(motion: Motions): Boolean = this != motion