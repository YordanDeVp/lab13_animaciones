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
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.ImageLoader
import coil.compose.AsyncImage
import coil.decode.GifDecoder
import coil.decode.ImageDecoderDecoder
import coil.request.ImageRequest
import com.example.animaciones.R

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun AnimacionesCombinadasScreen() {

    // ESTADOS
    var expanded by remember { mutableStateOf(false) }
    var colorChanged by remember { mutableStateOf(false) }
    var darkMode by remember { mutableStateOf(false) }
    var flashing by remember { mutableStateOf(false) }
    var showGif by remember { mutableStateOf(true) }
    var rotate by remember { mutableStateOf(false) }

    // ROTACIÓN ANIMADA
    val rotationAngle by animateFloatAsState(
        targetValue = if (rotate) 360f else 0f,
        animationSpec = tween(1000, easing = LinearEasing),
        label = "Rotation"
    )

    // CAMBIO DE TAMAÑO
    val size by animateDpAsState(
        targetValue = if (expanded) 200.dp else 100.dp,
        animationSpec = spring(
            dampingRatio = Spring.DampingRatioMediumBouncy,
            stiffness = Spring.StiffnessLow
        ),
        label = "BoxSize"
    )

    // CAMBIO DE COLOR + PARPADEO
    val finalColor = if (colorChanged) Color(0xFFFF9800) else Color(0xFF2196F3)

    val infiniteTransition = rememberInfiniteTransition(label = "")
    val flashingColor by infiniteTransition.animateColor(
        initialValue = finalColor,
        targetValue = Color.White,
        animationSpec = infiniteRepeatable(
            animation = tween(150),
            repeatMode = RepeatMode.Reverse
        ),
        label = "FlashColor"
    )

    LaunchedEffect(flashing) {
        if (flashing) {
            kotlinx.coroutines.delay(3000)
            flashing = false
        }
    }

    val boxColor = if (flashing) flashingColor else finalColor

    // FONDO MODO OSCURO / CLAR
    val backgroundColor by animateColorAsState(
        if (darkMode) Color(0xFF121212) else Color.White,
        tween(500),
        label = "BG"
    )

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = backgroundColor
    ) {

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(20.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            // MODO OSCURO/CLARO ARRIBA (fade down)
            AnimatedContent(
                targetState = darkMode,
                transitionSpec = {
                    (fadeIn(tween(600)) + slideInVertically { -40 }) togetherWith
                            (fadeOut(tween(600)) + slideOutVertically { 40 })
                },
                label = "ModeTransition"
            ) { isDark ->

                Text(
                    text = if (isDark) "Modo Oscuro Activado" else "Modo Claro Activado",
                    style = MaterialTheme.typography.headlineSmall,
                    color = if (isDark) Color.White else Color.Black
                )
            }

            Spacer(Modifier.height(16.dp))

            Button(onClick = { darkMode = !darkMode }) {
                Text("Cambiar modo")
            }

            Spacer(Modifier.height(40.dp))

            // BOX que cambia TAMAÑO + COLOR + ROTACIÓN + PARPADEO
            Box(
                modifier = Modifier
                    .size(size)
                    .graphicsLayer { rotationZ = rotationAngle }
                    .background(boxColor)
            )

            Spacer(Modifier.height(24.dp))

            // GIF PATO — ANIMADO REAL (con imageLoader personalizado)
            AnimatedVisibility(
                visible = showGif,
                enter = fadeIn(tween(700)) + slideInVertically(),
                exit = fadeOut(tween(700)) + slideOutVertically()
            ) {

                val context = LocalContext.current

                val imageLoader = ImageLoader.Builder(context)
                    .components {
                        add(ImageDecoderDecoder.Factory())
                        add(GifDecoder.Factory())
                    }
                    .build()

                AsyncImage(
                    model = ImageRequest.Builder(context)
                        .data(R.drawable.pato)
                        .build(),
                    imageLoader = imageLoader,
                    contentDescription = "GIF pato",
                    modifier = Modifier.size(150.dp)
                )
            }

            Spacer(Modifier.height(24.dp))

            // BOTONES
            Button(onClick = { showGif = !showGif }) {
                Text(if (showGif) "Ocultar GIF" else "Mostrar GIF")
            }

            Spacer(Modifier.height(16.dp))

            Button(onClick = { expanded = !expanded }) {
                Text("Cambiar tamaño")
            }

            Spacer(Modifier.height(16.dp))

            Button(onClick = {
                colorChanged = !colorChanged
                flashing = true
            }) {
                Text("Cambiar color")
            }

            Spacer(Modifier.height(16.dp))

            Button(onClick = { rotate = !rotate }) {
                Text("Girar")
            }
        }
    }
}