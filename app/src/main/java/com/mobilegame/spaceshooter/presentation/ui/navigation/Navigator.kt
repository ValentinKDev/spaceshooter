package com.mobilegame.spaceshooter.presentation.ui.navigation

import com.mobilegame.spaceshooter.logic.model.screen.NavigationDestination
import com.mobilegame.spaceshooter.utils.extensions.addNavArg
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow

class Navigator {
    var dest: MutableSharedFlow<String> = MutableSharedFlow()
    var des: SharedFlow<String> = dest.asSharedFlow()

    suspend fun navig(destination: NavigationDestination, argumentStr: String = "") {
//        AccelerometerListener.stop()
        val fullRoute =
            if (argumentStr.isEmpty()) destination.route
            else destination.route.addNavArg(argumentStr)
        dest.emit(fullRoute)
    }
}
