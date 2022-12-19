package com.mobilegame.spaceshooter.domain.model.screen.uiAdapter

import android.content.Context
import com.mobilegame.spaceshooter.presentation.theme.MyColor
import com.mobilegame.spaceshooter.utils.analyze.displayDataUI
import com.mobilegame.spaceshooter.utils.analyze.vLog
import com.mobilegame.spaceshooter.utils.analyze.wLog

object WifiScreenAdapter {
    val header = HeaderWifiScreenAdapter
    val delimiter = DelimiterWifiScreenAdapter
    val list = ListWifiScreenAdapter
    val color = ColorWifiScreen
    var backButton = BackButtonAdapter

    object ColorWifiScreen {
        val contrast = MyColor.applicationContrast
    }
    object HeaderWifiScreenAdapter {
        val ratios = RatioHeaderWifiScreen
        val sizes = SizesHeaderWifiScreen

        object RatioHeaderWifiScreen {
            const val heightWeight = 1F
        }
        object SizesHeaderWifiScreen {
            var height = 0F
        }
    }
    object DelimiterWifiScreenAdapter {
        val ratios = RatiosDelimiterWifiScreen

        object RatiosDelimiterWifiScreen {
            const val heightWeight = 0.06F
        }
    }
    object ListWifiScreenAdapter {
        val ratios = RatiosListWifiScreen

        object RatiosListWifiScreen {
            const val heightWeight = 4.94F
            const val heightBanner = 1F
        }
    }


    private var widthFull = 0
    private var heightFull = 0
    private var density = 0F
    private var allWeightsHeight = 0F

    private fun initHeader() {
        header.sizes.height = heightFull * (header.ratios.heightWeight / allWeightsHeight)

        displayDataUI?.let {
            wLog("WifiScreenAdapter::initHeader", "header")
            vLog("WifiScreenAdapter::initHeader", "height ${BluetoothScreenAdapter.header.sizes.height}")
        }
    }

    fun create(context: Context): WifiScreenAdapter {
        widthFull = context.resources.displayMetrics.widthPixels
        heightFull = context.resources.displayMetrics.heightPixels
        density = context.resources.displayMetrics.density
        allWeightsHeight = header.ratios.heightWeight + delimiter.ratios.heightWeight + list.ratios.heightWeight

        displayDataUI?.let {
            wLog("WifiScreenAdapter::create", "start")
            vLog("WifiScreenAdapter::create", "widthFull = ${widthFull}")
            vLog("WifiScreenAdapter::create", "heightFull = ${heightFull}")
            vLog("WifiScreenAdapter::create", "density = ${density}")
            vLog( "WifiScreenAdapter::create", "weight height = ${allWeightsHeight}" )
        }

        initHeader()
        backButton = BackButtonAdapter.create(context, header.sizes.height)
        return this
    }
}