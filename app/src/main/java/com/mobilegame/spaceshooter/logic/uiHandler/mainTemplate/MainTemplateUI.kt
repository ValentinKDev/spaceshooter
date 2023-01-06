package com.mobilegame.spaceshooter.logic.uiHandler.mainTemplate

object MainTemplateUI {
    const val allWeights = 6F
    const val headerHeightWeight = 1F
    const val delimiterHeightWeight = 0.06F
    const val emptySpaceHeightWeight = allWeights - headerHeightWeight - delimiterHeightWeight

    val header = HeaderTemplate()
    val topDelimiter = DelimiterTemplate(id = "top_delimiter")
    val band = BandTemplate()
    val bottomDelimiter = DelimiterTemplate(id = "bottom_delimiter")
    val emptySpace = EmptySpace()
    val backButton = BackButtonUI()
}