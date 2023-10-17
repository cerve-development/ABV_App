package com.fair.tool_belt_abv.ui.theme

import android.os.Build
import androidx.activity.ComponentActivity
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.ColorScheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext

@Composable
fun AppTheme(
    lightColorScheme: ColorScheme,
    darkColorScheme: ColorScheme,
    useDarkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable (Modifier) -> Unit = { }
) {

    val context = LocalContext.current
    val colors = if (useDarkTheme) { darkColorScheme } else { lightColorScheme }

    DisposableEffect(colors) {
        (context as ComponentActivity).apply {
            enableEdgeToEdge().also {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                    window.isNavigationBarContrastEnforced = false
                }
            }
        }
        onDispose {  }
    }

    MaterialTheme(colorScheme = colors) {
        content(Modifier)
    }
}
