package com.github.jeffersonrojas.mercadolibre.presentation.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val DarkColorPalette = darkColors(
    primary = Color.Purple200,
    primaryVariant = Color.Purple700,
    secondary = Color.Teal200,
    onPrimary = Color.White,
)

private val LightColorPalette = lightColors(
    primary = Color.Purple500,
    primaryVariant = Color.Purple700,
    secondary = Color.Teal200,
    secondaryVariant= Color.Teal700,
)

@Composable
fun MercadolibreTheme(darkTheme: Boolean = isSystemInDarkTheme(), content: @Composable () -> Unit) {
    val colors = if (darkTheme) {
        DarkColorPalette
    } else {
        LightColorPalette
    }

    MaterialTheme(
        colors = colors,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}