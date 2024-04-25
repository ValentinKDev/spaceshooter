package com.mobilegame.spaceshooter.logic.model.screen.menuScreen

import android.util.Log
import androidx.lifecycle.ViewModel
import com.mobilegame.spaceshooter.logic.model.navigation.Navigator
import com.mobilegame.spaceshooter.logic.model.navigation.PressureHandler
import com.mobilegame.spaceshooter.logic.model.navigation.Screens
import com.mobilegame.spaceshooter.logic.uiHandler.screens.menu.startMenu.MenuScreenUI
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class MenuScreenViewModel(): ViewModel() {
    private val TAG = "MenuScreenViewModel"
    val ui = MenuScreenUI()
//    val pressureHandler = PressureHandler(Screens.MainScreen)
    val pressureHandler = PressureHandler(null)
//    lateinit var nav: Navigator
//    private val menuSize: Int = MenuScreens.values().size
    private val menuArray: Array<MenuScreens> = MenuScreens.values()
    private val _currentSelection = MutableStateFlow(MenuScreens.Spacewars)
    val currentSelection: StateFlow<MenuScreens> = _currentSelection.asStateFlow()
    fun updateCurrentSelectionTo(menu: MenuScreens) { _currentSelection.value = menu }
    private val _navigate = MutableStateFlow(false)
    val navigate: StateFlow<Boolean> = _navigate.asStateFlow()

//    fun initNav(navigator: Navigator) { nav = navigator}
    fun onLeftClick() {
        val i = menuArray.indexOf(currentSelection.value)
        updateCurrentSelectionTo(
            if (i == 0) MenuScreens.values()[menuArray.size - 1]
            else MenuScreens.values()[i - 1]
        )
    }

    fun onRightClick() {
        val i = menuArray.indexOf(currentSelection.value)
        updateCurrentSelectionTo(
            if (i == menuArray.size - 1) MenuScreens.values()[0]
            else MenuScreens.values()[i + 1]
        )
    }

    suspend fun navigateToMainMenu(nav: Navigator) {
        Log.i(TAG, "navigateToMainMenu: navigate ${navigate.value}")
//        nav.navig(Screens.MenuScreen.forwardNav)
//        pressureVM.clearVM()
        Screens.MenuScreen.forwardNav?.let { nav.navig( it ) }
//        onCleared()
    }

    init {
        Log.i(TAG, "init: ")
    }

    override fun onCleared() {
        Log.i(TAG, "onCleared: ")
        super.onCleared()
    }
}