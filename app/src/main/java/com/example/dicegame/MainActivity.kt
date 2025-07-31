package com.example.dicegame

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.tooling.preview.Preview
import com.example.dicegame.ui.components.DiceTopAppBar
import com.example.dicegame.ui.screens.GameScreen
import com.example.dicegame.ui.theme.DiceGameTheme
import com.example.dicegame.ui.theme.FixedColorScheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val backgroundColor = FixedColorScheme.background.toArgb()
        enableEdgeToEdge(
            navigationBarStyle = SystemBarStyle.auto(
                lightScrim = backgroundColor,
                darkScrim = backgroundColor
            )
        )

        // The following code block requires API 29 or higher
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q)
            window.setNavigationBarContrastEnforced(false)

        setContent {
            DiceGameTheme {
                Scaffold(
                    containerColor = MaterialTheme.colorScheme.background,
                    topBar = { DiceTopAppBar() },
                ) { padding ->
                        GameScreen(modifier = Modifier.padding(padding))
                }
            }
        }
    }
}

@Preview(showBackground = true)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GreetingPreview() {
    DiceGameTheme {
        Surface(modifier = Modifier.fillMaxSize()){
            GameScreen(modifier = Modifier)
        }
    }
}