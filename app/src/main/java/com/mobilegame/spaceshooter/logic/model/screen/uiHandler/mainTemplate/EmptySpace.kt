package com.mobilegame.spaceshooter.logic.model.screen.uiHandler.mainTemplate

import com.mobilegame.spaceshooter.logic.model.screen.uiHandler.Device

class EmptySpace {
    val heightWeight = MainTemplate.allWeights - MainTemplate.headerHeightWeight - MainTemplate.delimiterHeightWeight
    val height = heightWeight * Device.height
}