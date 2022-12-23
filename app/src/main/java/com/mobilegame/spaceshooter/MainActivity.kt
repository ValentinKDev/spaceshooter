package com.mobilegame.spaceshooter

import android.app.Activity
import android.os.Bundle
import android.view.OrientationEventListener
import android.view.OrientationListener
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.gestures.Orientation
import com.mobilegame.spaceshooter.presentation.theme.SpaceShooterTheme
import com.mobilegame.spaceshooter.presentation.ui.navigation.Navigation
import com.mobilegame.spaceshooter.presentation.ui.navigation.Navigator

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SpaceShooterTheme {
                Navigation(Navigator())
            }
        }
    }
}