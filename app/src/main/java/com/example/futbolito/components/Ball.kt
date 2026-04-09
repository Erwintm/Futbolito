package com.example.futbolito.components

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.DrawScope


fun DrawScope.drawBall(posX: Float, posY: Float) {
    drawCircle(
        color = Color.White,
        radius = 35f,
        center = Offset(posX, posY)
    )


    drawCircle(
        color = Color.Black.copy(alpha = 0.2f),
        radius = 10f,
        center = Offset(posX, posY)
    )
}