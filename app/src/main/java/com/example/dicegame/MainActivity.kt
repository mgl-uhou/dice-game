package com.example.dicegame

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
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
import androidx.compose.ui.tooling.preview.Preview
import com.example.dicegame.ui.components.DiceTopAppBar
import com.example.dicegame.ui.screens.GameScreen
import com.example.dicegame.ui.theme.DiceGameTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)
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