package com.cerve.abv.shared.model.preferences

import com.cerve.abv.shared.model.AbvEquation
import com.cerve.abv.shared.model.AbvUnit
import com.cerve.abv.shared.model.AppTheme

data class SettingPreferences(
    val abvUnit: AbvUnit = AbvUnit.SG,
    val abvEquation: AbvEquation = AbvEquation.S,
    val appTheme: AppTheme = AppTheme.LEGACY,
    val inDarkMode: Boolean? = null
)
