package com.fair.tool_belt_abv.model

import androidx.compose.material3.ColorScheme
import androidx.compose.ui.graphics.Color
import com.fair.tool_belt_abv.ui.theme.DarkLegacyColors
import com.fair.tool_belt_abv.ui.theme.DarkStoutColors
import com.fair.tool_belt_abv.ui.theme.HopsDarkColors
import com.fair.tool_belt_abv.ui.theme.HopsLightColors
import com.fair.tool_belt_abv.ui.theme.LightLegacyColors
import com.fair.tool_belt_abv.ui.theme.LightStoutColors

enum class AppTheme(
    val color: Color
) {
    LEGACY(color = LightLegacyColors.primary),
    STOUT(color = LightStoutColors.primary),
    HOPS(color = HopsLightColors.primary);

    fun selectedTheme() : Pair<ColorScheme, ColorScheme> {
        return when(this) {
            LEGACY -> Pair(LightLegacyColors, DarkLegacyColors)
            STOUT -> Pair(LightStoutColors, DarkStoutColors)
            HOPS -> Pair(HopsLightColors, HopsDarkColors)
        }
    }

}