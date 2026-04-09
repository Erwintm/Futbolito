package com.example.futbolito.components

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.DrawScope

/**
 * Dibuja la pelota en el Canvas.
 * Se usa como una extensión de DrawScope para acceder a las funciones de dibujo.
 */
fun DrawScope.drawBall(posX: Float, posY: Float) {
    drawCircle(
        color = Color.White,
        radius = 35f, // El mismo radio que usamos para las colisiones
        center = Offset(posX, posY)
    )

    // Un pequeño detalle para que parezca balón (opcional)
    drawCircle(
        color = Color.Black.copy(alpha = 0.2f),
        radius = 10f,
        center = Offset(posX, posY)
    )
}