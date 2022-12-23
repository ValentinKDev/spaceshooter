package com.mobilegame.spaceshooter.logic.model.screen.bluetoothScreen

//object BluetoothScreenObj {
//    val header = HeaderBluetoothScreen
//    val delimiter = DelimiterBluetoothScreen
//    val list = ListBluetoothScreen
//    val colors = ColorsBluetoothScreen
//    var backButton = BackButtonObj
//
//    object ColorsBluetoothScreen {
//        val contrast = MyColor.applicationContrast
//    }
//
//    object HeaderBluetoothScreen {
//        val ratios = RatiosHeaderBluetoothScreen
//
//        object RatiosHeaderBluetoothScreen {
//            const val heightWeight = 1F
//        }
//    }
//
//    object DelimiterBluetoothScreen {
//        val ratios = RatiosDelimiterBluetoothScreen
//
//        object RatiosDelimiterBluetoothScreen {
//            const val heightWeight = 0.06F
//        }
//    }
//
//    object ListBluetoothScreen {
//        val ratios = RatiosBluetoothScreen
//
//        object RatiosBluetoothScreen {
//            const val heightWeight = 4.94F
//        }
//    }
//
//    private var widthFull = 0
//    private var heightFull = 0
//    private var density = 0F
//    private var allWeightsHeight = 0F
//
//    fun create(context: Context): BluetoothScreenObj {
//        widthFull = context.resources.displayMetrics.widthPixels
//        heightFull = context.resources.displayMetrics.heightPixels
//        density = context.resources.displayMetrics.density
//        allWeightsHeight = header.ratios.heightWeight + delimiter.ratios.heightWeight + list.ratios.heightWeight
//
//        displayDataUI?.let {
//            wLog("BluetoothScreenObj::create", "start")
//            vLog("BluetoothScreenObj::create", "widthFull = ${widthFull}")
//            vLog("BluetoothScreenObj::create", "heightFull = ${heightFull}")
//            vLog("BluetoothScreenObj::create", "density = ${density}")
//            vLog("BluetoothScreenObj::create", "weight height = ${allWeightsHeight}")
//        }
//
//        backButton = BackButtonObj.create(context)
//        return this
//    }
//}