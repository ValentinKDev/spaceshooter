package com.mobilegame.spaceshooter.presentation.ui.screens.connection.spaceShipMenuScreen

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import com.mobilegame.spaceshooter.logic.model.navigation.Navigator

@Composable
fun SpaceShipMenuScreen(navigator: Navigator) {
    LaunchedEffect(true) {
        Log.e("SpaceShipMenuScreen", "SpaceShipMenuScreen")
    }
}