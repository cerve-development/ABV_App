package com.cerve.abv.shared.cache

import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey

object PreferenceKeys {
    val ABV_UNIT_KEY = stringPreferencesKey("calculatorUnit")
    val ABV_EQUATION_KEY = stringPreferencesKey("calculatorEquation")
    val APP_THEME_KEY = stringPreferencesKey("appThemeSimpleABV")
    val APP_IS_IN_DARK_MODE = booleanPreferencesKey("appIsInDarkMode")
}
