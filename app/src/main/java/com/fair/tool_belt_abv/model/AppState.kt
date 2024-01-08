package com.fair.tool_belt_abv.model

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.ColorScheme
import androidx.compose.runtime.Composable
import com.cerve.abv.shared.model.AppTheme
import com.fair.tool_belt_abv.ui.theme.HopsDarkColors
import com.fair.tool_belt_abv.ui.theme.HopsLightColors
import com.fair.tool_belt_abv.ui.theme.LagerDarkColors
import com.fair.tool_belt_abv.ui.theme.LagerLightColors
import com.fair.tool_belt_abv.ui.theme.LegacyDarkColors
import com.fair.tool_belt_abv.ui.theme.LegacyLightColors
import com.fair.tool_belt_abv.ui.theme.StoutDarkColors
import com.fair.tool_belt_abv.ui.theme.StoutLightColors

data class AppState(
    private val inDarkMode: Boolean? = false,
    val showReview: Boolean = false,
    val colorSchemePalette: AppTheme = AppTheme.LEGACY
) {

    @Composable
    fun getInDarkMode() : Boolean = inDarkMode ?: isSystemInDarkTheme()
    @Composable
    fun getColors() : ColorScheme = if (getInDarkMode()) darkColorScheme else lightColorScheme

    private val darkColorScheme: ColorScheme = when(colorSchemePalette) {
        AppTheme.LEGACY -> LegacyDarkColors
        AppTheme.STOUT -> StoutDarkColors
        AppTheme.HOPS -> HopsDarkColors
        AppTheme.LAGER -> LagerDarkColors
    }

    private val lightColorScheme: ColorScheme = when(colorSchemePalette) {
        AppTheme.LEGACY -> LegacyLightColors
        AppTheme.STOUT -> StoutLightColors
        AppTheme.HOPS -> HopsLightColors
        AppTheme.LAGER -> LagerLightColors
    }

}
