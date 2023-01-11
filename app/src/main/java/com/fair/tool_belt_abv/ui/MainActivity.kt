package com.fair.tool_belt_abv.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.cerve.co.material3extension.designsystem.ExtendedTheme
import com.fair.tool_belt_abv.ui.screen.AppScreen
import com.fair.tool_belt_abv.ui.theme.color.LegacyDarkColors
import com.fair.tool_belt_abv.ui.theme.color.LegacyLightColors
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

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

            ExtendedTheme(
                darkColorScheme = LegacyDarkColors,
                lightColorScheme = LegacyLightColors,
                dynamicColor = false
            ) { modifier ->
                AppScreen(modifier = modifier)
            }
        }

    }
}
