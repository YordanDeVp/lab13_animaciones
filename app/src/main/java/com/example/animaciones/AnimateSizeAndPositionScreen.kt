package com.example.animaciones

import androidx.compose.animation.core.tween
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateDpAsState
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
fun AnimateSizeAndPositionScreen() {
    var moved by remember { mutableStateOf(false) }
    var movedRight by remember { mutableStateOf(false) }
    var movedUp by remember { mutableStateOf(false) }
    var movedL by remember { mutableStateOf(false) }

    // Animación del tamaño
    val size by animateDpAsState(
        targetValue = if (moved) 100.dp else 200.dp,
        animationSpec = tween(durationMillis = 500),
        label = "BoxSize"
    )

    // Animación de la posición horizontal
    val offsetX by animateDpAsState(
        targetValue = if (movedRight) 120.dp else 0.dp,
        animationSpec = spring(
            dampingRatio = Spring.DampingRatioMediumBouncy,
            stiffness = Spring.StiffnessLow
        ),
        label = "BoxOffsetX"
    )

    // Animación de la posición Vertical
    val offsetY by animateDpAsState(
        targetValue = if (movedUp) -120.dp else 0.dp,
        animationSpec = spring(
            dampingRatio = Spring.DampingRatioMediumBouncy,
            stiffness = Spring.StiffnessMediumLow
        ),
        label = "BoxOffsetY"
    )

    // Animación de la posición Izquieda
    val offsetL by animateDpAsState(
        targetValue = if (movedL) -120.dp else -0.dp,
        animationSpec = spring(
            dampingRatio = Spring.DampingRatioMediumBouncy,
            stiffness = Spring.StiffnessMediumLow
        ),
        label = "BoxOffsetL"
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

            // Cuadro animado (posición + tamaño)
            Box(
                modifier = Modifier
                    .offset(x = offsetX)  // Movimiento lateral
                    .offset(y = offsetY)
                    .offset(x = offsetL)
                    .size(size)            // Cambio de tamaño
                    .background(Color(0xFF4C61DA)) // Azul Material
            )

            Spacer(modifier = Modifier.height(24.dp))

            Button(onClick = { movedRight = !movedRight }) {
                Text(if (movedRight) "Volver al inicio" else "Mover Derecha")
            }

            Button(
                onClick = { movedUp = !movedUp }) {
                Text(if (movedUp) "Volver al inicio" else "Mover Arriba")
            }

            Button(onClick = { movedL = !movedL }) {
                Text(if (movedL) "Volver al inicio" else "Mover Izquierda")
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewAnimateSizeAndPositionScreen() {
    AnimateSizeAndPositionScreen()
}
