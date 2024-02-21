package com.mobilegame.spaceshooter

import android.content.Context
import android.net.wifi.WifiManager
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.mobilegame.spaceshooter.logic.model.navigation.Navigator
import com.mobilegame.spaceshooter.presentation.theme.SpaceShooterTheme
import com.mobilegame.spaceshooter.presentation.ui.navigation.Navigation
import com.mobilegame.spaceshooter.utils.analyze.eLog

class MainActivity : ComponentActivity() {
    lateinit var wifiManager: WifiManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        wifiManager = getSystemService(Context.WIFI_SERVICE) as WifiManager

        setContent {
            SpaceShooterTheme(this.window) {
                Navigation(Navigator())
            }
        }
    }

    override fun onStart() {
        super.onStart()
        eLog("MainActivity::onStart", "ON START")
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