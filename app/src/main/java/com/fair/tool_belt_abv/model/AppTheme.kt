package com.fair.tool_belt_abv.model

import androidx.compose.material3.ColorScheme
import androidx.compose.ui.graphics.Color
import com.example.compose.DarkLegacyColors
import com.example.compose.DarkStoutColors
import com.example.compose.HopsDarkColors
import com.example.compose.HopsLightColors
import com.example.compose.LightLegacyColors
import com.example.compose.LightStoutColors

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