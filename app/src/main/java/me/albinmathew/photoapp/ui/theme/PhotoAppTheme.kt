package me.albinmathew.photoapp.ui.theme

import androidx.compose.material.MaterialTheme
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color // Ensure Color is imported

// Define actual app colors
val AppColorPrimary = Color(0xFF008577)
val AppColorPrimaryDark = Color(0xFF00574B)
val AppColorAccent = Color(0xFFD81B60)

private val LightColorPalette = lightColors(
    primary = AppColorPrimary,
    primaryVariant = AppColorPrimaryDark,
    secondary = AppColorAccent
    // You might want to define other colors like background, surface, error, onPrimary, onSecondary etc.
    // For now, we'll just use the primary ones from the old theme.
)

@Composable
fun PhotoAppTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        colors = LightColorPalette,
        // typography = Typography, // Define Typography later if needed
        // shapes = Shapes, // Define Shapes later if needed
        content = content
    )
}
