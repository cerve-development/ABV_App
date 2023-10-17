package com.fair.tool_belt_abv.model

import androidx.compose.material3.ColorScheme
import com.fair.tool_belt_abv.ui.theme.DarkLegacyColors
import com.fair.tool_belt_abv.ui.theme.LightLegacyColors

data class AppState(
    val abvUnit: AbvUnit = AbvUnit.SG,
    val abvEquation: AbvEquation = AbvEquation.S,
    val inDarkMode: Boolean? = null,
    val isLoading: Boolean = true,
    val showReview: Boolean = false,
    val colorSchemePalette: AppTheme = AppTheme.LEGACY,
    val colorSchemeLight: ColorScheme = LightLegacyColors,
    val colorSchemeDark: ColorScheme = DarkLegacyColors
) {
    fun requestReview() = !isLoading && showReview
}