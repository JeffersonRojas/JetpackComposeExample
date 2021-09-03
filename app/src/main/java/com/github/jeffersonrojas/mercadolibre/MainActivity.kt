package com.github.jeffersonrojas.mercadolibre

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.github.jeffersonrojas.mercadolibre.ui.theme.MercadolibreTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MercadolibreTheme {
            }
        }
    }
}
