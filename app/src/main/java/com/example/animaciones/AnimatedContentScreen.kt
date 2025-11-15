package com.example.animaciones

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.core.tween
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

// Enum con los estados posibles
enum class UiState {
    Cargando, Contenido, Error
}

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun AnimatedContentScreen() {
    var currentState by remember { mutableStateOf(UiState.Cargando) }

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

            // Contenedor animado que cambia su contenido
            AnimatedContent(
                targetState = currentState,
                transitionSpec = {
                    fadeIn(animationSpec = tween(1000)) togetherWith
                            fadeOut(animationSpec = tween(800))
                },
                label = "AnimatedContentTransition"
            ) { state ->
                when (state) {
                    UiState.Cargando -> Text(
                        text = "Cargando...",
                        style = MaterialTheme.typography.headlineSmall
                    )

                    UiState.Contenido -> Text(
                        text = "Contenido cargado correctamente",
                        style = MaterialTheme.typography.headlineSmall
                    )

                    UiState.Error -> Text(
                        text = "Error al cargar los datos",
                        style = MaterialTheme.typography.headlineSmall,
                        color = MaterialTheme.colorScheme.error
                    )
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            // Botón para cambiar el estado
            Button(onClick = {
                currentState = when (currentState) {
                    UiState.Cargando -> UiState.Contenido
                    UiState.Contenido -> UiState.Error
                    UiState.Error -> UiState.Cargando
                }
            }) {
                Text("Cambiar estado")
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewAnimatedContentScreen() {
    AnimatedContentScreen()
}
