package com.mobilegame.spaceshooter.logic.uiHandler.template

import com.mobilegame.spaceshooter.logic.uiHandler.Device

class EmptySpace {
    val id = "emptySpaceTemplate"
    val heightWeight = MainTemplateUI.allWeights - MainTemplateUI.headerHeightWeight - MainTemplateUI.delimiterHeightWeight
    val height = heightWeight * Device.height

    val percent = PercentEmptySpace()
    data class PercentEmptySpace(
        val heightWithoutBand: Float = 1F - MainTemplateUI.header.percent.height - MainTemplateUI.topDelimiter.percent.height,
        val heightWithBand: Float = 1F - MainTemplateUI.header.percent.height - MainTemplateUI.topDelimiter.percent.height - MainTemplateUI.bottomDelimiter.percent.height - MainTemplateUI.band.percent.height
    )
}