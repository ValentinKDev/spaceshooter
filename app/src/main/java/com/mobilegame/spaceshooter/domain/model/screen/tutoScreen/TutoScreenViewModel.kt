package com.mobilegame.spaceshooter.domain.model.screen.tutoScreen

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.mobilegame.spaceshooter.domain.model.screen.Screens
import com.mobilegame.spaceshooter.domain.model.screen.mainScreen.PressureNavigationViewModel
import com.mobilegame.spaceshooter.domain.model.screen.uiAdapter.TutoScreenAdapter

class TutoScreenViewModel(application: Application): AndroidViewModel(application) {
    val ui = TutoScreenAdapter.create(application)

    val backButtonPressureNavigationVM = PressureNavigationViewModel().create(Screens.MainScreen)
}