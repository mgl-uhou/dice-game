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
import androidx.compose.foundation.Canvas
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.dicegame.ui.theme.BrownBase
import com.example.dicegame.ui.theme.DiceGameTheme
import kotlinx.coroutines.delay
import kotlin.math.min
import kotlin.random.Random

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            DiceGameTheme {
                Scaffold(
                    topBar = { MyTopAppBar() },
                ) { padding ->
                    Surface(
                        modifier = Modifier.fillMaxSize().padding(padding),
                        color = MaterialTheme.colorScheme.background
                    ) {
                        MyTopAppBar()
                        App()
                    }
                }
            }
        }
    }
}

fun DrawScope.bullet(number: Int) {
    // calcula alguns valores usados abaixo
    val w = size.width
    val h = size.height
    val cx = w  / 2f          // center x
    val cy = h  / 2f          // center y
    val qx = w  / 4f          // quarter x
    val qy = h  / 4f          // quarter y
    val tqx = w  * 3f / 4f    // three‑quarters x
    val tqy = h  * 3f / 4f    // three‑quarters y
    val dotRadius = min(w, h) / 10f  // ajustável: tamanho do ponto

    // desenha círculo preto em cada posição
    fun drawDot(at: Offset) {
        drawCircle(Color.Black, radius = dotRadius, center = at)
    }

    when (number) {
        1 -> drawDot(Offset(cx, cy))
        2 -> {
            drawDot(Offset(qx, qy))
            drawDot(Offset(tqx, tqy))
        }
        3 -> {
            drawDot(Offset(qx, qy))
            drawDot(Offset(cx, cy))
            drawDot(Offset(tqx, tqy))
        }
        4 -> {
            drawDot(Offset(qx, qy))
            drawDot(Offset(tqx, qy))
            drawDot(Offset(qx, tqy))
            drawDot(Offset(tqx, tqy))
        }
        5 -> {
            drawDot(Offset(qx, qy))
            drawDot(Offset(tqx, qy))
            drawDot(Offset(cx, cy))
            drawDot(Offset(qx, tqy))
            drawDot(Offset(tqx, tqy))
        }
        6 -> {
            drawDot(Offset(qx, qy))
            drawDot(Offset(tqx, qy))
            drawDot(Offset(qx, cy))
            drawDot(Offset(tqx, cy))
            drawDot(Offset(qx, tqy))
            drawDot(Offset(tqx, tqy))
        }
        else -> { /* nada */ }
    }
}

@Composable
fun Dice(number: Int, modifier: Modifier){
    Canvas(
        modifier = modifier
            .padding(16.dp)
            .size(112.dp)
    ){
        drawRoundRect(
            color        = Color.White,
            cornerRadius = CornerRadius(20f, 20f),
            topLeft      = Offset.Zero,
            size         = size
        )
       bullet(number = number)
    }
}

@Composable
fun App() {
    var r by remember { mutableStateOf(0) }
    var timer by remember { mutableStateOf(0) }

    LaunchedEffect(key1 = timer) {
        if (timer > 0) {
            delay((500 * (1.0f / timer)).toLong())
            r = Random.nextInt(1, 7)
            timer -= 1
        }
    }

    Box(modifier = Modifier
        .fillMaxSize()
        .background(Color.LightGray),
        contentAlignment = Alignment.Center
    ) {
        Dice(number = r, modifier = Modifier)

       Button(
           onClick = {
               timer = 20
           },
           modifier = Modifier
               .align(Alignment.Center)
               .offset(y = (100).dp),
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyTopAppBar() {
    TopAppBar(
        title = { Text("Dice Game") },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = BrownBase,
            titleContentColor = Color.White,
            navigationIconContentColor = Color.White
        )
    )
}

@Preview(showBackground = true)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GreetingPreview() {
    DiceGameTheme {
        Surface(modifier = Modifier.fillMaxSize()){
            App()
        }
    }
}