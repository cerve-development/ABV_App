package com.fair.tool_belt_abv.data

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import com.fair.tool_belt_abv.data.PreferencesKeys.APP_LAST_RATING_PROMPT_TIME
import com.fair.tool_belt_abv.model.AbvEquation
import com.fair.tool_belt_abv.model.AbvUnit
import com.fair.tool_belt_abv.model.AppTheme
import com.fair.tool_belt_abv.model.CalculatorPreferences
import com.fair.tool_belt_abv.model.SettingPreferences
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import java.io.IOException
import java.time.Instant.now
import javax.inject.Inject

class StorageManager @Inject constructor(
    private val dataStore: DataStore<Preferences>
) {

    init {
        CoroutineScope(Dispatchers.Default).launch {
            dataStore.edit { preferences ->
                if (preferences[APP_LAST_RATING_PROMPT_TIME].isNullOrEmpty()) {
                    preferences[APP_LAST_RATING_PROMPT_TIME] = now().toEpochMilli().toString()
                }
            }
        }
    }

    val calculatorPreferences: Flow<CalculatorPreferences> = dataStore.data
        .catch { exception ->
            if (exception is IOException) {
                emit(emptyPreferences())
            } else {
                throw exception
            }
        }.map { preferences ->

            val unit = AbvUnit.valueOf(
                preferences[PreferencesKeys.ABV_UNIT_KEY] ?: AbvUnit.SG.name
            )

            val equation = AbvEquation.valueOf(
                preferences[PreferencesKeys.ABV_EQUATION_KEY] ?: AbvEquation.S.name
            )

            CalculatorPreferences(unit, equation)
        }

    val settingPreferences: Flow<SettingPreferences> = dataStore.data
        .catch { exception ->
            if (exception is IOException) {
                emit(emptyPreferences())
            } else {
                throw exception
            }
        }.map { preferences ->

            val unit = AbvUnit.valueOf(
                preferences[PreferencesKeys.ABV_UNIT_KEY] ?: AbvUnit.SG.name
            )

            val equation = AbvEquation.valueOf(
                preferences[PreferencesKeys.ABV_EQUATION_KEY] ?: AbvEquation.S.name
            )

            val theme = AppTheme.valueOf(
                preferences[PreferencesKeys.APP_THEME_KEY] ?: AppTheme.LEGACY.name
            )

            val inDarkMode = preferences[PreferencesKeys.APP_IS_IN_DARK_MODE]

            SettingPreferences(
                abvUnit = unit,
                abvEquation = equation,
                appTheme = theme,
                inDarkMode = inDarkMode
            )
        }

    suspend fun saveEquation(value: AbvEquation) {
        dataStore.edit { settings ->
            settings[PreferencesKeys.ABV_EQUATION_KEY] = value.name
        }
    }

    suspend fun saveUnit(value: AbvUnit) {
        dataStore.edit { settings ->
            settings[PreferencesKeys.ABV_UNIT_KEY] = value.name
        }
    }

    suspend fun saveAppTheme(value: AppTheme) {
        dataStore.edit { settings ->
            settings[PreferencesKeys.APP_THEME_KEY] = value.name
        }
    }

    suspend fun saveDarkModeState(value: Boolean) {
        dataStore.edit { settings ->
            settings[PreferencesKeys.APP_IS_IN_DARK_MODE] = value
        }
    }
}
