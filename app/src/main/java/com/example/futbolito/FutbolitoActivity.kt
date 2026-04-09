package com.example.futbolito

import android.content.Context
import android.hardware.SensorManager
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel

import com.example.futbolito.components.drawBall
import com.example.futbolito.components.drawPitch

class FutbolitoActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val viewModel: FutbolitoViewModel = viewModel()
            val context = LocalContext.current

            // Manejo del sensor de movimiento
            LaunchedEffect(Unit) {
                val sensorManager = context.getSystemService(Context.SENSOR_SERVICE) as SensorManager
                val accelerometer = sensorManager.getDefaultSensor(android.hardware.Sensor.TYPE_ACCELEROMETER)

                // Vinculamos el sensor directamente con la lógica del ViewModel
                sensorManager.registerListener(viewModel, accelerometer, SensorManager.SENSOR_DELAY_GAME)
            }

            Box(modifier = Modifier.fillMaxSize()) {
                // El área de juego (Capa inferior)
                Canvas(modifier = Modifier.fillMaxSize()) {

                    if (viewModel.screenWidth == 0f) {
                        viewModel.screenWidth = size.width
                        viewModel.screenHeight = size.height
                        viewModel.resetBall()
                    }

                    // Llamamos a las funciones modulares de dibujo
                    drawPitch()
                    drawBall(viewModel.posX, viewModel.posY)
                }

                // Interfaz del Marcador (Capa superior)
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 80.dp) // Espacio para que no estorbe en la portería superior
                        .background(Color.Black.copy(alpha = 0.4f))
                        .padding(vertical = 8.dp),
                    horizontalArrangement = Arrangement.SpaceEvenly,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Text("FC Barcelona", color = Color.White, fontSize = 14.sp)
                        Text("${viewModel.scoreHome}", color = Color.Yellow, fontSize = 32.sp, fontWeight = FontWeight.Bold)
                    }
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Text("Real Madrid", color = Color.White, fontSize = 14.sp)
                        Text("${viewModel.scoreVisitor}", color = Color.Yellow, fontSize = 32.sp, fontWeight = FontWeight.Bold)
                    }
                }
            }
        }
    }
}