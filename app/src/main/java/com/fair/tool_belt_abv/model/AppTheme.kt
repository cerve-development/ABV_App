package com.fair.tool_belt_abv.model

import androidx.compose.material3.ColorScheme
import androidx.compose.ui.graphics.Color
import com.example.compose.HopsDarkColors
import com.example.compose.HopsLightColors
import com.fair.tool_belt_abv.ui.theme.color.LegacyDarkColors
import com.fair.tool_belt_abv.ui.theme.color.LegacyLightColors
import com.fair.tool_belt_abv.ui.theme.color.StoutDarkColors
import com.fair.tool_belt_abv.ui.theme.color.StoutLightColors

enum class AppTheme(
    val color: Color
) {
    LEGACY(color = LegacyLightColors.primary),
    STOUT(color = StoutLightColors.primary),
    HOPS(color = HopsLightColors.primary);

    fun selectedTheme() : Pair<ColorScheme, ColorScheme> {
        return when(this) {
            LEGACY -> Pair(LegacyLightColors, LegacyDarkColors)
            STOUT -> Pair(StoutLightColors, StoutDarkColors)
            HOPS -> Pair(HopsLightColors, HopsDarkColors)
        }
    }

}