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

    val activityVM: MainViewModel by viewModels()

    @OptIn(ExperimentalLifecycleComposeApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
////        setContentView(R.layout.activity_main)
//
//        //set preferences on first log in
//        SharedPreference(this).apply {
//
//            if (get("calculatorUnit").isNullOrEmpty()) {
//                saveU("SG")
//            }
//
//            if (get("calculatorEquation").isNullOrEmpty()) {
//                saveE("S")
//            }
//
//        }
//
//        when (SharedPreference(this).get("calculatorAppTheme").toString()) {
//
//            "DM" -> {
//                setDefaultNightMode(MODE_NIGHT_YES)
//                delegate.applyDayNight()
//            }
//            "LM" -> {
//                setDefaultNightMode(MODE_NIGHT_NO)
//                delegate.applyDayNight()
//            }
//            "SD" -> {
//                setDefaultNightMode(MODE_NIGHT_FOLLOW_SYSTEM)
//                delegate.applyDayNight()
//            }
//
//            else -> {
//                setDefaultNightMode(MODE_NIGHT_FOLLOW_SYSTEM)
//                delegate.applyDayNight()
//            }
//
//        }

        setContent {
            val state by activityVM.preferences.collectAsStateWithLifecycle()

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
