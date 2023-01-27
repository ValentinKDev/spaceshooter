package com.mobilegame.spaceshooter.presentation.theme

import android.content.pm.ActivityInfo
import android.os.Build
import android.view.Window
import androidx.appcompat.app.AppCompatDelegate
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalContext
import com.mobilegame.spaceshooter.logic.uiHandler.Device
import com.mobilegame.spaceshooter.presentation.ui.screens.lock.LockScreenOrientation
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
    //todo: dynamic color for the LogoScreen
    // Dynamic color is available on Android 12+
    dynamicColor: Boolean = true,
    content: @Composable () -> Unit
) {
    val context = LocalContext.current

    LaunchedEffect(true) {
        Device.initDatastore(context)
    }

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

    Box(
        Modifier
            .fillMaxSize()
            .background(MyColor.applicationBackground)
            .onGloballyPositioned { layout ->
                Device.initiated ?: run {
                    Device.initLayout(
                        context,
                        layout
                    )
                }
            }
    ) {
        MaterialTheme(
            colorScheme = colorScheme,
            typography = Typography,
            content = content
        )
    }
}