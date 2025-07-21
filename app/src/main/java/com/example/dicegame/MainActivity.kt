package com.example.dicegame

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.foundation.Canvas
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.dicegame.ui.theme.DiceGameTheme
import kotlin.random.Random

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            DiceGameTheme {
                Scaffold { padding ->
                    Surface(
                        modifier = Modifier.fillMaxSize().padding(padding),
                        color = MaterialTheme.colorScheme.background
                    ) {
                        App()
                    }
                }
            }
        }
    }
}

fun DrawScope.circle(offset: () -> Offset){
    val radius = Dp(20f).value
    drawCircle(
        Color.Black,
        radius = radius,
        center = offset()
    )
}

fun DrawScope.center(){
    circle {
        Offset(size.width / 2, size.height / 2)
    }
}

fun DrawScope.centerRight(){
    circle {
        Offset(size.width - Dp(20f).value, size.height / 2)
    }
}

fun DrawScope.centerLeft(){
    circle {
        Offset(Dp(40f).value, size.height / 2)
    }
}

fun DrawScope.topRight(){
    circle {
        Offset(size.width - Dp(20f).value, Dp(40f).value)
    }
}

fun DrawScope.topLeft(){
    circle {
        Offset(Dp(40f).value, Dp(40f).value)
    }
}

fun DrawScope.bottomRight(){
    circle {
        Offset(size.width - Dp(20f).value, size.height - Dp(20f).value)
    }
}

fun DrawScope.bottomLeft() {
    circle {
        Offset(Dp(40f).value, size.height - Dp(20f).value)
    }
}

fun DrawScope.bullet(number: Int) {
    when(number){
        1 -> {
            center()
        }
        2 -> {
            topRight()
            bottomLeft()
        }
        3 -> {
            topRight()
            center()
            bottomLeft()
        }
        4 -> {
            topLeft()
            topRight()
            bottomLeft()
            bottomRight()
        }
        5 -> {
            topLeft()
            topRight()
            center()
            bottomLeft()
            bottomRight()
        }
        6 -> {
            topLeft()
            topRight()
            centerRight()
            centerLeft()
            bottomLeft()
            bottomRight()
        }
        else -> {}
    }
}

@Composable
fun Dice(number: Int, modifier: Modifier){
    Canvas(
        modifier = modifier
            .size(96.dp, 96.dp)
    ){
        drawRoundRect(
            Color.Green,
            cornerRadius = CornerRadius(20f, 20f),
            topLeft = Offset(10f, 10f),
            size = size
        )
       bullet(number = number)
    }
}

@Composable
fun App() {
    Box(modifier = Modifier
        .fillMaxSize()
        .background((Color.Black))
    ) {
        Dice(number = 1, modifier = Modifier.align(Alignment.Center))

       Button(
           onClick = {  },
           modifier = Modifier
               .align(Alignment.Center)
               .offset(y = (100).dp)
           ) {
           Text("Jogar")
       }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    DiceGameTheme {
        Surface(modifier = Modifier.fillMaxSize()){
            App()
        }
    }
}