package com.fair.tool_belt_abv.model

import androidx.compose.material3.ColorScheme
import com.fair.tool_belt_abv.ui.theme.color.LegacyDarkColors
import com.fair.tool_belt_abv.ui.theme.color.LegacyLightColors

data class AppPreferences(
    val abvUnit: AbvUnit = AbvUnit.SG,
    val abvEquation: AbvEquation = AbvEquation.S,
    val colorSchemePalette: AppTheme = AppTheme.LEGACY,
    val colorSchemeLight: ColorScheme = LegacyLightColors,
    val colorSchemeDark: ColorScheme = LegacyDarkColors,
    val inDarkMode: Boolean? = null,
    val isLoading: Boolean = true
)
