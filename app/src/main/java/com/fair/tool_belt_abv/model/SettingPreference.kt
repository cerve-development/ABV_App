package com.fair.tool_belt_abv.model

data class SettingPreferences(
    val abvUnit: AbvUnit = AbvUnit.SG,
    val abvEquation: AbvEquation = AbvEquation.S,
    val appTheme: AppTheme = AppTheme.LEGACY,
    val inDarkMode: Boolean? = null
)
