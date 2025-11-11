package com.example.animaciones

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun AnimatedVisibilityScreen() {
    var visible by remember { mutableStateOf(false) }

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {

            // Botón para alternar la visibilidad
            Button(onClick = { visible = !visible }) {
                Text(if (visible) "Ocultar cuadro" else "Mostrar cuadro")
            }

            Spacer(modifier = Modifier.height(20.dp))

            // Cuadro animado con entrada/salida suave
            AnimatedVisibility(
                visible = visible,
                enter = fadeIn(
                    animationSpec = tween(
                        durationMillis = 1000
                    )
                ),
                exit = fadeOut(
                    animationSpec = tween(
                        durationMillis = 1000
                    )
                )
            ) {
                Box(
                    modifier = Modifier
                        .size(150 .dp)
                        .background(Color(0xFF1E6DBB))
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewAnimatedVisibilityScreen() {
    AnimatedVisibilityScreen()
}
