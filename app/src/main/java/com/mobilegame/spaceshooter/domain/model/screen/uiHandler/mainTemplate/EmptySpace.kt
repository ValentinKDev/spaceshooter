package com.mobilegame.spaceshooter.domain.model.screen.uiHandler.mainTemplate

import com.mobilegame.spaceshooter.domain.model.screen.uiHandler.Device

class EmptySpace {
    val heightWeight = MainTemplate.allWeights - MainTemplate.headerHeightWeight - MainTemplate.delimiterHeightWeight
    val height = heightWeight * Device.height
}