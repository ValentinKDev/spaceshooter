package com.mobilegame.spaceshooter.presentation.theme

import android.content.pm.ActivityInfo
import android.os.Build
import android.view.Window
import androidx.appcompat.app.AppCompatDelegate
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import com.mobilegame.spaceshooter.data.device.Device
import com.mobilegame.spaceshooter.logic.model.screen.metrics.SpaceShooterMetricsViewModel
import com.mobilegame.spaceshooter.logic.repository.device.DeviceDataRepo
import com.mobilegame.spaceshooter.presentation.ui.screens.connection.RegisterDeviceName
import com.mobilegame.spaceshooter.presentation.ui.screens.lock.LockScreenOrientation
import com.mobilegame.spaceshooter.presentation.ui.screens.utils.BackgroundGrid
import com.mobilegame.spaceshooter.presentation.ui.screens.utils.HideScreenBars

private val DarkColorScheme = darkColorScheme(
    primary = Purple80,
    secondary = PurpleGrey80,
    tertiary = Pink80,
    background = Color.Red
)

private val LightColorScheme = lightColorScheme(
    primary = Purple40,
    secondary = PurpleGrey40,
    tertiary = Pink40,
    background = Color.Black
//    background = MyColor.applicationBackground

    /* Other default colors to override
    background = Color(0xFFFFFBFE),
    surface = Color(0xFFFFFBFE),
    onPrimary = Color.White,
    onSecondary = Color.White,
    onTertiary = Color.White,
    onBackground = Color(0xFF1C1B1F),
    onSurface = Color(0xFF1C1B1F),
    */
)

@Composable
fun SpaceShooterTheme(
    window: Window,
    darkTheme: Boolean = isSystemInDarkTheme(),
    vm: SpaceShooterMetricsViewModel = viewModel(),
    //todo: dynamic color for the LogoScreen
    // Dynamic color is available on Android 12+
    dynamicColor: Boolean = true,
    content: @Composable () -> Unit
) {
    val context = LocalContext.current
    val metricsInitiated: Boolean? by remember { vm.initiated }.collectAsState()

    LaunchedEffect(true) { DeviceDataRepo().init(context) }

    LockScreenOrientation(orientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE)

    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)

    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }
        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }

    HideScreenBars(window)

    if (metricsInitiated == null ) {
        Box( Modifier.fillMaxSize().onGloballyPositioned { layout ->
            vm.initMetrics(context, layout)
//            vm.ui.init(context)
            vm.initBackgroundData(context)
        } )
    } else {
        BackgroundGrid(vm.ui)
        MaterialTheme(
            colorScheme = colorScheme,
            typography = Typography,
            content = content
        )
    }
}