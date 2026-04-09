package com.example.futbolito.components

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.DrawScope


fun DrawScope.drawPitch() {

    drawRect(color = Color(0xFF2E7D32))

    //  Líneas de la cancha
    drawRect(
        color = Color.White.copy(alpha = 0.5f),
        topLeft = Offset(20f, 20f),
        size = Size(size.width - 40f, size.height - 40f),
        style = androidx.compose.ui.graphics.drawscope.Stroke(width = 5f)
    )


    val goalWidth = 300f
    val centerX = size.width / 2


    drawRect(
        color = Color.White,
        topLeft = Offset(centerX - (goalWidth / 2), 0f),
        size = Size(goalWidth, 50f)
    )


    drawRect(
        color = Color.White,
        topLeft = Offset(centerX - (goalWidth / 2), size.height - 50f),
        size = Size(goalWidth, 50f)
    )

    //
    drawRect(
        color = Color.LightGray,
        topLeft = Offset(size.width * 0.25f, size.height * 0.45f),
        size = Size(size.width * 0.5f, 30f)
    )
}