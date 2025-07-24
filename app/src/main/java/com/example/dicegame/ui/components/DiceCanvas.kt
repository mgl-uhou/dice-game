package com.example.dicegame.ui.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.unit.dp
import kotlin.math.min


private fun DrawScope.bullet(number: Int) {
    val w = size.width
    val h = size.height
    val cx = w  / 2f          // center x
    val cy = h  / 2f          // center y
    val qx = w  / 4f          // quarter x
    val qy = h  / 4f          // quarter y
    val tqx = w  * 3f / 4f    // three‑quarters x
    val tqy = h  * 3f / 4f    // three‑quarters y
    val dotRadius = min(w, h) / 10f

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
        else -> {  }
    }
}

@Composable
fun Dice(number: Int, modifier: Modifier){
    Canvas(
        modifier = modifier
            .padding(20.dp)
            .size(200.dp)
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
