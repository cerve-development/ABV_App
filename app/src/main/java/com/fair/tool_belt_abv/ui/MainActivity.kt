package com.fair.tool_belt_abv.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.cerve.co.material3extension.designsystem.ExtendedTheme
import com.fair.tool_belt_abv.MainViewModel
import com.fair.tool_belt_abv.ui.screen.AppScreen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val activityViewModel: MainViewModel by viewModels()
    private var isLoading: Boolean = true

    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen().apply { setKeepOnScreenCondition { isLoading } }
        super.onCreate(savedInstanceState)

        setContent {
            val state by activityViewModel.preferences.collectAsStateWithLifecycle()

            LaunchedEffect(state.isLoading) {
                isLoading = state.isLoading
            }

            ExtendedTheme(
                dynamicColor = false,
                darkColorScheme = state.colorSchemeDark,
                lightColorScheme = state.colorSchemeLight,
                darkTheme = state.inDarkMode ?: isSystemInDarkTheme()
            ) { modifier ->
                AppScreen(
                    modifier = modifier,
                    appPreferences = state
                )
            }
        }

    }
}
