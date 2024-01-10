package com.fair.tool_belt_abv.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.fair.tool_belt_abv.ui.navigation.RootNavGraph
import com.fair.tool_belt_abv.ui.theme.AppTheme
import com.fair.tool_belt_abv.ui.viewmodel.MainViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : ComponentActivity() {

    private val vm: MainViewModel by viewModel()
    private var isLoading: Boolean = true

    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen().apply { setKeepOnScreenCondition { isLoading } }
        super.onCreate(savedInstanceState)

        setContent {
            val uiState by vm.uiState.collectAsStateWithLifecycle()

            uiState.StateWrapper {
                LaunchedEffect(Unit) { isLoading = false }

                ScreenWrapper { state ->
                    AppTheme(
                        colors = state.getColors(),
                        useDarkTheme = state.getInDarkMode()
                    ) { RootNavGraph() }
                }
            }
        }
    }
}
