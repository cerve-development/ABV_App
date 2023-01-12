package com.fair.tool_belt_abv.data

import androidx.datastore.preferences.core.stringPreferencesKey

object PreferencesKeys {
    val ABV_UNIT_KEY = stringPreferencesKey("calculatorUnit")
    val ABV_EQUATION_KEY = stringPreferencesKey("calculatorEquation")
}