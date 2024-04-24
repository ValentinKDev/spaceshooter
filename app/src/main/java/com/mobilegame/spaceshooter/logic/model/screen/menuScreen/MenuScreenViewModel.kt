package com.mobilegame.spaceshooter.logic.model.screen.menuScreen

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mobilegame.spaceshooter.logic.model.navigation.Navigator
import com.mobilegame.spaceshooter.logic.model.navigation.PressureViewModel
import com.mobilegame.spaceshooter.logic.model.navigation.Screens
import com.mobilegame.spaceshooter.logic.uiHandler.screens.menu.startMenu.MenuScreenUI
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class MenuScreenViewModel(): ViewModel() {
    private val TAG = "MenuScreenViewModel"
    val ui = MenuScreenUI()
    val pressureVM = PressureViewModel()
//    lateinit var nav: Navigator
//    private val menuSize: Int = MenuScreens.values().size
    private val menuArray: Array<MenuScreens> = MenuScreens.values()
    private val _currentMenu = MutableStateFlow(MenuScreens.Spacewars)
    val currentMenu: StateFlow<MenuScreens> = _currentMenu.asStateFlow()
    fun updateCurrentMenuTo(menu: MenuScreens) { _currentMenu.value = menu }
    private val _navigate = MutableStateFlow(false)
    val navigate: StateFlow<Boolean> = _navigate.asStateFlow()


//    fun initNav(navigator: Navigator) { nav = navigator}
    fun onLeftClick() {
        val i = menuArray.indexOf(currentMenu.value)
        updateCurrentMenuTo(
            if (i == 0) MenuScreens.values()[menuArray.size - 1]
            else MenuScreens.values()[i - 1]
        )
    }
    fun onRightClick() {
        val i = menuArray.indexOf(currentMenu.value)
        updateCurrentMenuTo(
            if (i == menuArray.size - 1) MenuScreens.values()[0]
            else MenuScreens.values()[i + 1]
        )
    }
    suspend fun navigateToMainMenu(nav: Navigator) {
        nav.navig(Screens.MainScreen)
        onCleared()
    }

    init {
        Log.i(TAG, "init: ")
        viewModelScope.launch {
            val lp = async {
                pressureVM.full.collect {
                    Log.i(TAG, "collect full: $it")
//                    if (it) nav.navig(Screens.MainScreen)
                    if (it) _navigate.value = true
                }
            }
        }
    }

    override fun onCleared() {
        Log.i(TAG, "onCleared: ")
        super.onCleared()
    }
}