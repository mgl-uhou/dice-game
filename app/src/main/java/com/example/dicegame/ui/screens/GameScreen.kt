package com.example.dicegame.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.dicegame.ui.components.Dice
import com.example.dicegame.ui.theme.BrownBase
import kotlinx.coroutines.delay
import kotlin.random.Random

@Composable
fun GameScreen() {
    var roll by remember { mutableStateOf(0) }
    var timer by remember { mutableStateOf(0) }

    LaunchedEffect(key1 = timer) {
        if (timer > 0) {
            delay((500 * (1.0f / timer)).toLong())
            roll = Random.nextInt(1, 7)
            timer -= 1
        }
    }

    Box(modifier = Modifier
        .fillMaxSize()
        .background(Color.LightGray),
        contentAlignment = Alignment.Center
    ) {
        Dice(number = roll, modifier = Modifier)

        Button(
            onClick = {
                timer = 20
            },
            modifier = Modifier
                .align(Alignment.Center)
                .offset(y = (150).dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = BrownBase,
                contentColor = Color.White
            )
        ) {
            if (timer > 0)
                Text("$timer")
            else
                Text("Play")
        }
    }
}
