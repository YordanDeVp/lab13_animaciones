package com.example.animaciones

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.example.animaciones.ui.theme.AnimacionesTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            AnimacionesTheme {
                Surface(
                    modifier = Modifier.fillMaxSize()
                ) {

                    //AnimateSizeAndPositionScreen()
                    //AnimateColorScreen()
                    //AnimatedVisibilityScreen()
                }
            }
        }
    }
}
