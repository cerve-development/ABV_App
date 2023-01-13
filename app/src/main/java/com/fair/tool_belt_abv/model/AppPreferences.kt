package com.fair.tool_belt_abv.model

import androidx.compose.material3.ColorScheme
import com.example.compose.DarkLegacyColors
import com.example.compose.LightLegacyColors

data class AppPreferences(
    val abvUnit: AbvUnit = AbvUnit.SG,
    val abvEquation: AbvEquation = AbvEquation.S,
    val colorSchemePalette: AppTheme = AppTheme.LEGACY,
    val colorSchemeLight: ColorScheme = LightLegacyColors,
    val colorSchemeDark: ColorScheme = DarkLegacyColors,
    val inDarkMode: Boolean? = null,
    val isLoading: Boolean = true
)
