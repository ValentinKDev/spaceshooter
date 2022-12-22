package com.mobilegame.spaceshooter.domain.model.screen.uiHandler.mainTemplate

object MainTemplate {
    const val allWeights = 6F
    const val headerHeightWeight = 1F
    const val delimiterHeightWeight = 0.06F
    const val emptySpaceHeightWeight = allWeights - headerHeightWeight - delimiterHeightWeight

    val header = HeaderTemplate()
    val delimiter = DelimiterTemplate()
    val emptySpace = EmptySpace()
    val backButton = BackButtonUI()
}