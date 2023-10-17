package com.fair.tool_belt_abv.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.core.view.WindowCompat
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.fair.tool_belt_abv.ui.screen.AppScreen
import com.fair.tool_belt_abv.ui.theme.AppTheme
import com.fair.tool_belt_abv.ui.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val vm: MainViewModel by viewModels()
    private var isLoading: Boolean = true

    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen().apply { setKeepOnScreenCondition { isLoading } }
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false)

        setContent {
            val state by vm.preferences.collectAsStateWithLifecycle()

            LaunchedEffect(state.isLoading) {
                isLoading = state.isLoading
            }

            AppTheme(
                darkColorScheme = state.colorSchemeDark,
                lightColorScheme = state.colorSchemeLight,
                useDarkTheme = state.inDarkMode ?: isSystemInDarkTheme()
            ) { AppScreen(appPreferences = state) }

        }
    }
}
