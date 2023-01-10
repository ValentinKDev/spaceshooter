package com.mobilegame.spaceshooter.logic.uiHandler.template

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import com.mobilegame.spaceshooter.logic.uiHandler.Device
import com.mobilegame.spaceshooter.presentation.theme.MyColor
import com.mobilegame.spaceshooter.utils.extensions.toDp

class DelimiterTemplate(val id: String, percent: TemplateUI.TemplatePercents) {
    var heightDp: Dp = (percent.topDelimiter * Device.height).toDp()
    val color: Color = MyColor.applicationText
}