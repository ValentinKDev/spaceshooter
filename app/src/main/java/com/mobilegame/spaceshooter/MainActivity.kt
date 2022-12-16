package com.mobilegame.spaceshooter

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
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