package com.example.futbolito

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener

class FutbolitoViewModel : ViewModel(), SensorEventListener {
    var posX by mutableStateOf(0f)
    var posY by mutableStateOf(0f)
    var scoreHome by mutableStateOf(0)
    var scoreVisitor by mutableStateOf(0)

    var screenWidth = 0f
    var screenHeight = 0f

    // Radio de la pelota
    private val ballRadius = 35f

    override fun onSensorChanged(event: SensorEvent?) {
        if (event?.sensor?.type == Sensor.TYPE_ACCELEROMETER) {
            val ax = event.values[0]
            val ay = event.values[1]

       
            if (Math.abs(ax) < 0.2f && Math.abs(ay) < 0.2f) return

            // Sensibilidad:
            val nextX = posX - (ax * 7f)
            val nextY = posY + (ay * 7f)

            // Colisiones con bordes de la pantalla
            if (nextX >= ballRadius && nextX <= screenWidth - ballRadius) {
                posX = nextX
            }
            if (nextY >= ballRadius && nextY <= screenHeight - ballRadius) {
                posY = nextY
            }

            checkGoal()
        }
    }

    private fun checkGoal() {
        val center = screenWidth / 2
        val goalRange = (center - 150)..(center + 150)


        if (posY <= ballRadius + 50 && posX in goalRange) {
            scoreVisitor++
            resetBall()
        }


        if (posY >= screenHeight - ballRadius - 50 && posX in goalRange) {
            scoreHome++
            resetBall()
        }
    }

    fun resetBall() {
        posX = screenWidth / 2
        posY = screenHeight / 2
    }

    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {}
}