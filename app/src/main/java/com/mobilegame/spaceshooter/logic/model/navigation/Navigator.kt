package com.mobilegame.spaceshooter.logic.model.navigation

import android.util.Log
import com.mobilegame.spaceshooter.data.device.Device
import com.mobilegame.spaceshooter.utils.analyze.eLog
import com.mobilegame.spaceshooter.utils.extensions.addNavArg
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow

//todo: get rid of Navigator in composable functions and instanciate a new class whenever it's needed
class Navigator {
    val TAG = "Navigator"

    private var dest: MutableSharedFlow<String> = MutableSharedFlow()
    var des: SharedFlow<String> = dest.asSharedFlow()

    suspend fun navig(toScreen: Screens, argumentStr: String = "") {
        //todo : argumentStr to Device.navigation.arg =
        Log.e(TAG, "navig: to Screen ${toScreen.route} / arg : $argumentStr")
        Device.navigation.argStr = argumentStr
        dest.emit(toScreen.route)
    }
}