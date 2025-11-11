package com.example.animaciones

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
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
fun AnimateColorScreen() {
    var isBlue by remember { mutableStateOf(true) }

    // Animación del color con transición suave
    val backgroundColor by animateColorAsState(
        targetValue = if (isBlue) Color(0xFF2196F3) else Color(0xFF4CAF50), // Azul → Verde
        animationSpec = tween(durationMillis = 1200), // 1 segundo, suave
        label = "ColorTransition"
    )

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Box(
                modifier = Modifier
                    .size(150.dp)
                    .background(backgroundColor)
            )

            Spacer(modifier = Modifier.height(24.dp))

            Button(onClick = { isBlue = !isBlue }) {
                Text(if (isBlue) "Cambiar a Verde" else "Cambiar a Azul")
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewAnimateColorScreen() {
    AnimateColorScreen()
}
