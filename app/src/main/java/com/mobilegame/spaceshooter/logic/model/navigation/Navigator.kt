package com.mobilegame.spaceshooter.logic.model.navigation

import android.util.Log
import com.mobilegame.spaceshooter.utils.analyze.eLog
import com.mobilegame.spaceshooter.utils.extensions.addNavArg
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow

//todo: get rid of Navigator in composable functions and instanciate a new class whenever it's needed
class Navigator {
    val TAG = "Navigator"
    val currentScreen = Screens.None

    private var dest: MutableSharedFlow<String> = MutableSharedFlow()
    var des: SharedFlow<String> = dest.asSharedFlow()

//    suspend fun navig(destination: NavigationDestination, argumentStr: String = "") {
    suspend fun navig(toScreen: Screens, argumentStr: String = "") {
        val fullRoute =
            if (argumentStr.isEmpty()) toScreen.route
            else toScreen.route.addNavArg(argumentStr)
        Log.e(TAG, "navig: full route:$fullRoute", )
//        Log.e(TAG, "navig: destination : $destination / screens ${Screens.find(destination.route)}", )
        dest.emit(fullRoute)
    }
}