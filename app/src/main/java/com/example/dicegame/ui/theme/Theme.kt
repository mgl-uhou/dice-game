package com.example.dicegame.ui.theme

import android.app.Activity
import android.os.Build
import android.view.Window
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat

private val FixedColorScheme = lightColorScheme(
    primary = BrownBase,
    onPrimary = Color.White,
    background = Color.LightGray,
    onBackground = Color.Black
)

@Composable
fun DiceGameTheme(
    content: @Composable () -> Unit
) {
    val view = LocalView.current
    if(!view.isInEditMode) {
        SideEffect {
            val window: Window = (view.context as Activity).window
            val insetsController = WindowCompat.getInsetsController(window, view)
            insetsController.isAppearanceLightStatusBars = false
            insetsController.isAppearanceLightNavigationBars = true
        }
    }

    MaterialTheme(
        colorScheme = FixedColorScheme,
        typography = Typography,
        content = content
    )
}