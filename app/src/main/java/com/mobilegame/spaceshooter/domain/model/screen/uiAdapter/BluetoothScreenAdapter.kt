package com.mobilegame.spaceshooter.domain.model.screen.uiAdapter

import android.content.Context
import com.mobilegame.spaceshooter.presentation.theme.MyColor
import com.mobilegame.spaceshooter.utils.analyze.displayDataUI
import com.mobilegame.spaceshooter.utils.analyze.vLog
import com.mobilegame.spaceshooter.utils.analyze.wLog

object BluetoothScreenAdapter {
    val header = HeaderBluetoothScreen
    val delimiter = DelimiterBluetoothScreen
    val list = ListBluetoothScreen
    val colors = ColorsBluetoothScreen
    var backButton = BackButtonAdapter
    var banner = BannerBluetoothScreen

    object BannerBluetoothScreen {
        val ratios = RatiosBannerBluetoothScreen
        lateinit var bluetoothIcon: BluetoothIconAdapter

        object RatiosBannerBluetoothScreen {
            const val topPadding = 0.3F
            const val heightBanner = 1F
        }
    }
    object ColorsBluetoothScreen {
        val contrast = MyColor.applicationContrast
    }

    object HeaderBluetoothScreen {
        val ratios = RatiosHeaderBluetoothScreen
        val sizes = SizesHeaderBluetoothScreen

        object RatiosHeaderBluetoothScreen {
            const val heightWeight = 1F
        }
        object SizesHeaderBluetoothScreen {
            var height = 0F
        }
    }

    object DelimiterBluetoothScreen {
        val ratios = RatiosDelimiterBluetoothScreen

        object RatiosDelimiterBluetoothScreen {
            const val heightWeight = 0.06F
        }
    }

    object ListBluetoothScreen {
        val ratios = RatiosBluetoothScreen

        object RatiosBluetoothScreen {
            const val heightWeight = 4.94F
        }
    }

    private fun initHeader() {
        header.sizes.height = heightFull * (header.ratios.heightWeight / allWeightsHeight)

        displayDataUI?.let {
            wLog("BluetoothScreenObj::initHeader", "header")
            vLog("BluetoothScreenObj::initHeader", "height ${header.sizes.height}")
        }
    }
    private fun initBanner(context: Context) {
        banner.bluetoothIcon = BluetoothIconAdapter( context, banner.ratios.heightBanner * heightFull, )
    }

    private var widthFull = 0
    private var heightFull = 0
    private var density = 0F
    private var allWeightsHeight = 0F

    fun create(context: Context): BluetoothScreenAdapter {
        widthFull = context.resources.displayMetrics.widthPixels
        heightFull = context.resources.displayMetrics.heightPixels
        density = context.resources.displayMetrics.density
        allWeightsHeight = header.ratios.heightWeight + delimiter.ratios.heightWeight + list.ratios.heightWeight

        displayDataUI?.let {
            wLog("BluetoothScreenAdapter::create", "start")
            vLog("BluetoothScreenAdapter::create", "widthFull = ${widthFull}")
            vLog("BluetoothScreenAdapter::create", "heightFull = ${heightFull}")
            vLog("BluetoothScreenAdapter::create", "density = ${density}")
            vLog("BluetoothScreenAdapter::create", "weight height = ${allWeightsHeight}")
        }

        initHeader()
        initBanner(context)
        backButton = BackButtonAdapter.create(context, header.sizes.height)
        return this
    }
}
