package com.mobilegame.spaceshooter.logic.uiHandler.mainTemplate

import com.mobilegame.spaceshooter.logic.uiHandler.Device

class EmptySpace {
    val heightWeight = MainTemplate.allWeights - MainTemplate.headerHeightWeight - MainTemplate.delimiterHeightWeight
    val height = heightWeight * Device.height
}