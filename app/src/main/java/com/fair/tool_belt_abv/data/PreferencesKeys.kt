package com.fair.tool_belt_abv.data

import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.longPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey

object PreferencesKeys {
    val ABV_UNIT_KEY = stringPreferencesKey("calculatorUnit")
    val ABV_EQUATION_KEY = stringPreferencesKey("calculatorEquation")
    val APP_THEME_KEY = stringPreferencesKey("appThemeSimpleABV")
    val APP_IS_IN_DARK_MODE = booleanPreferencesKey("appIsInDarkMode")

    val APP_LAST_RATING_TIME = longPreferencesKey("appLastRatingTime")
    val APP_INSTANCE_COUNT = intPreferencesKey("appInstanceCount")

}
