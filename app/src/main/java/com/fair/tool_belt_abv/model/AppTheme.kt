package com.fair.tool_belt_abv.model

import androidx.compose.ui.graphics.Color
import com.fair.tool_belt_abv.ui.theme.color.LegacyLightColors
import com.fair.tool_belt_abv.ui.theme.color.StoutLightColors

enum class AppTheme(
    val color: Color
) {

    LEGACY(color = LegacyLightColors.primary),
    STOUT(color = StoutLightColors.primary)

}