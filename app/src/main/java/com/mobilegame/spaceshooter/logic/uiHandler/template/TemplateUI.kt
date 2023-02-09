package com.mobilegame.spaceshooter.logic.uiHandler.template

class TemplateUI() {
    val percent = TemplatePercents()
    val header = HeaderTemplate(percent)
    val topDelimiter = DelimiterTemplate(id = "top_delimiter", percent = percent)
    val band = BandTemplate(percent)
    val bottomDelimiter = DelimiterTemplate(id = "bottom_delimiter", percent = percent)
    val body = BodyTemplate(percent)
    val backButton = BackButtonUI(percent)

    class TemplatePercents {
//        val header = 0.16F
        val header = 0.18F
        val topDelimiter = 0.01F
        val band = header
        val bottomDelimiter = topDelimiter
        val bodyWithoutBand = 1F - header - topDelimiter
        val bodyWithBand = 1F - header - topDelimiter - band - bottomDelimiter
    }
}