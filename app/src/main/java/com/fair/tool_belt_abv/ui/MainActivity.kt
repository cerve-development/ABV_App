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
import com.google.android.play.core.review.ReviewException
import com.google.android.play.core.review.ReviewManagerFactory
import com.google.android.play.core.review.model.ReviewErrorCode
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlin.time.Duration.Companion.seconds

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val vm: MainViewModel by viewModels()
    private var isLoading: Boolean = true

    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen().apply { setKeepOnScreenCondition { isLoading } }
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false)
        vm.updateInstanceCount()

        setContent {
            val state by vm.appState.collectAsStateWithLifecycle()

            LaunchedEffect(state.isLoading, state.requestReview()) {
                isLoading = state.isLoading

                delay(5.seconds)
                if (state.requestReview()) {

                    val manager = ReviewManagerFactory.create(this@MainActivity)
                    val request = manager.requestReviewFlow()
                    request.addOnCompleteListener { task ->
                        if (task.isSuccessful) {
                            // We got the ReviewInfo object
                            val reviewInfo = task.result
                            val flow = manager.launchReviewFlow(this@MainActivity, reviewInfo)
                            flow.addOnCompleteListener { _ ->
                                vm.resetRating()
                                // The flow has finished. The API does not indicate whether the user
                                // reviewed or not, or even whether the review dialog was shown. Thus, no
                                // matter the result, we continue our app flow.
                            }
                        } else {
                            // There was some problem, log or handle the error code.
                            @ReviewErrorCode
                            val reviewErrorCode = (task.exception as ReviewException).errorCode
                        }
                    }
                }
            }

            AppTheme(
                darkColorScheme = state.colorSchemeDark,
                lightColorScheme = state.colorSchemeLight,
                useDarkTheme = state.inDarkMode ?: isSystemInDarkTheme()
            ) { AppScreen(appPreferences = state) }

        }
    }
}
