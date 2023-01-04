package com.mobilegame.spaceshooter.logic.uiHandler.mainTemplate

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import com.mobilegame.spaceshooter.logic.uiHandler.Device
import com.mobilegame.spaceshooter.presentation.theme.MyColor
import com.mobilegame.spaceshooter.utils.extensions.toDp

class DelimiterTemplate {
    val sizes = SizesDelimiterTemplate()
    val colors = ColorsDelimiterTemplate()

    data class SizesDelimiterTemplate (
        var heightDp: Dp = (Device.height * (MainTemplate.delimiterHeightWeight / MainTemplate.allWeights)).toDp()
    )
    data class ColorsDelimiterTemplate (
        val background: Color = MyColor.applicationText
    )
}