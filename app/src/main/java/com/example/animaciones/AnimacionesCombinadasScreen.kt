package com.example.animaciones

import androidx.compose.animation.*
import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun AnimacionesCombinadasScreen() {
    var expanded by remember { mutableStateOf(false) }
    var showButton by remember { mutableStateOf(true) }
    var darkMode by remember { mutableStateOf(false) }

    // Animación de tamaño
    val size by animateDpAsState(
        targetValue = if (expanded) 200.dp else 100.dp,
        animationSpec = spring(
            dampingRatio = Spring.DampingRatioMediumBouncy,
            stiffness = Spring.StiffnessLow
        ),
        label = "BoxSize"
    )

    // Animación de color
    val color by animateColorAsState(
        targetValue = if (expanded) Color(0xFF4CAF50) else Color(0xFF2196F3),
        animationSpec = tween(durationMillis = 1000, easing = LinearOutSlowInEasing),
        label = "BoxColor"
    )

    // Fondo según modo
    val backgroundColor by animateColorAsState(
        targetValue = if (darkMode) Color(0xFF121212) else Color(0xFFFFFFFF),
        animationSpec = tween(durationMillis = 800),
        label = "BackgroundColor"
    )

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = backgroundColor
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(20.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            // Elemento que cambia tamaño y color
            Box(
                modifier = Modifier
                    .size(size)
                    .background(color)
                    .animateContentSize()
            )

            Spacer(modifier = Modifier.height(24.dp))

            // Botón que aparece/desaparece con AnimatedVisibility
            AnimatedVisibility(
                visible = showButton,
                enter = fadeIn(animationSpec = tween(800)) + slideInVertically(),
                exit = fadeOut(animationSpec = tween(800)) + slideOutVertically()
            ) {
                Button(onClick = { expanded = !expanded }) {
                    Text(if (expanded) "Reducir y cambiar color" else "Agrandar y cambiar color")
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            // Transición de contenido: modo claro / oscuro
            AnimatedContent(
                targetState = darkMode,
                transitionSpec = {
                    fadeIn(animationSpec = tween(700)) togetherWith fadeOut(animationSpec = tween(700))
                },
                label = "DarkModeTransition"
            ) { isDark ->
                Text(
                    text = if (isDark) "🌙 Modo Oscuro Activado" else "☀️ Modo Claro Activado",
                    style = MaterialTheme.typography.headlineSmall,
                    color = if (isDark) Color.White else Color.Black
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Botones de control
            Row(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
                Button(onClick = { showButton = !showButton }) {
                    Text(if (showButton) "Ocultar botón" else "Mostrar botón")
                }
                Button(onClick = { darkMode = !darkMode }) {
                    Text("Cambiar modo")
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewAnimacionesCombinadasScreen() {
    AnimacionesCombinadasScreen()
}
