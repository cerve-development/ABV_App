package com.fair.tool_belt_abv.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.lifecycle.compose.ExperimentalLifecycleComposeApi
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.cerve.co.material3extension.designsystem.ExtendedTheme
import com.fair.tool_belt_abv.MainViewModel
import com.fair.tool_belt_abv.ui.screen.AppScreen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val activityViewModel: MainViewModel by viewModels()

    @OptIn(ExperimentalLifecycleComposeApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            val state by activityViewModel.preferences.collectAsStateWithLifecycle()

            val (light, dark) = remember(state) {
                state.appTheme.selectedTheme()
            }

            ExtendedTheme(
                darkColorScheme = dark,
                lightColorScheme = light,
                dynamicColor = false,
                darkTheme = state.inDarkMode ?: isSystemInDarkTheme()
            ) { modifier ->
                AppScreen(
                    modifier = modifier,
                    settingPreferences = state
                )
            }
        }

    }
}
