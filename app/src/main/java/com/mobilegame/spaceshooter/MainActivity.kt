package com.mobilegame.spaceshooter

import android.os.Bundle
import android.view.View
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.google.accompanist.systemuicontroller.SystemUiController
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.mobilegame.spaceshooter.presentation.theme.SpaceShooterTheme
import com.mobilegame.spaceshooter.presentation.ui.navigation.Navigation
import com.mobilegame.spaceshooter.presentation.ui.navigation.Navigator
import com.mobilegame.spaceshooter.utils.analyze.eLog

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SpaceShooterTheme(this.window) {
                Navigation(Navigator())
            }
        }
    }

    override fun onStart() {
        super.onStart()
        eLog("MainActivity", "ON START")
    }

    override fun onPause() {
        super.onPause()
        eLog("MainActivity", "ON PAUSE")
    }

    override fun onResume() {
        super.onResume()
        eLog("MainActivity", "ON RESUME")
    }

    override fun onDestroy() {
        super.onDestroy()
        eLog("MainActivity", "ON DESTROY")
    }
}