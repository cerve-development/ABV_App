package com.fair.tool_belt_abv.ui.theme

import android.os.Build
import androidx.activity.ComponentActivity
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.ColorScheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat

@Composable
fun AppTheme(
    colors: ColorScheme,
    useDarkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable (Modifier) -> Unit = { }
) {
    val context = LocalContext.current
    val view = LocalView.current

    SideEffect {
        (context as ComponentActivity).apply {
            enableEdgeToEdge().also {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                    window.isNavigationBarContrastEnforced = false
                    window.isStatusBarContrastEnforced = false
                }
                val windowInsetsController =
                    WindowCompat.getInsetsController(window, view)

                windowInsetsController.isAppearanceLightStatusBars = !useDarkTheme
                windowInsetsController.isAppearanceLightNavigationBars = !useDarkTheme
            }
        }
    }

    MaterialTheme(colorScheme = colors) {
        content(Modifier)
    }
}
