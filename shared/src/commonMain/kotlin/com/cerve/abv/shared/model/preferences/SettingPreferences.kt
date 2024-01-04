package com.cerve.abv.shared.model.preferences

import com.cerve.abv.shared.model.AppTheme

data class SettingPreferences(
    val appTheme: AppTheme = AppTheme.LEGACY,
    val inDarkMode: Boolean? = null
)
