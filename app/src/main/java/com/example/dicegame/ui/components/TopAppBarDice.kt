package com.example.dicegame.ui.components

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import com.example.dicegame.ui.theme.BrownBase


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DiceTopAppBar() {
    TopAppBar(
        title = { Text("Dice Game") },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = BrownBase,
            titleContentColor = Color.White,
            navigationIconContentColor = Color.White
        )
    )
}
