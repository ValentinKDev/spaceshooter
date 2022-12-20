package com.mobilegame.spaceshooter.domain.model.screen.uiAdapter

import android.content.Context
import com.mobilegame.spaceshooter.utils.analyze.displayDataUI
import com.mobilegame.spaceshooter.utils.analyze.vLog
import com.mobilegame.spaceshooter.utils.analyze.wLog

object TutoScreenAdapter {
    lateinit var backButton: BackButtonAdapter
    var backButtonLayer = BackButtonLayerTutoScreen

    object BackButtonLayerTutoScreen {
        var buttonHeightWeight = 1F
        var emptyHeightWeight = 5F
    }

    private var widthFull = 0
    private var heightFull = 0
    private var density = 0F
    private var allWeightsHeight = 0F

    private fun initBackButton(context: Context) {

        val boxHeight = heightFull * (backButtonLayer.buttonHeightWeight / (backButtonLayer.buttonHeightWeight + backButtonLayer.emptyHeightWeight))
        backButton = BackButtonAdapter.create(context, boxHeight)
    }

    fun create(context: Context): TutoScreenAdapter {
        widthFull = context.resources.displayMetrics.widthPixels
        heightFull = context.resources.displayMetrics.heightPixels
        density = context.resources.displayMetrics.density
//        allWeightsHeight =

        displayDataUI?.let {
            wLog("TutoScreenAdapter::create", "start")
            vLog("TutoScreenAdapter::create", "widthFull = $widthFull")
            vLog("TutoScreenAdapter::create", "heightFull = $heightFull")
            vLog("TutoScreenAdapter::create", "density = $density")
            vLog("TutoScreenAdapter::create", "weight height = $allWeightsHeight")
        }

        initBackButton(context)
        return this
    }

}