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
    private var dest: MutableSharedFlow<String> = MutableSharedFlow()
    var des: SharedFlow<String> = dest.asSharedFlow()

    suspend fun navig(destination: NavigationDestination, argumentStr: String = "") {
        val fullRoute =
            if (argumentStr.isEmpty()) destination.route
            else destination.route.addNavArg(argumentStr)
        Log.e(TAG, "navig: full route:$fullRoute", )
        dest.emit(fullRoute)
    }
}