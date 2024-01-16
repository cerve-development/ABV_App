package com.cerve.abv.shared.cache

import androidx.datastore.core.DataStore
import androidx.datastore.core.IOException
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import com.cerve.abv.shared.model.AbvEquation
import com.cerve.abv.shared.model.AbvUnit
import com.cerve.abv.shared.model.AppTheme
import com.cerve.abv.shared.model.preferences.CalculatorPreferences
import com.cerve.abv.shared.model.preferences.SettingPreferences
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import org.koin.core.component.KoinComponent

class PreferenceManager(
    private val dataStore: DataStore<Preferences>
) : KoinComponent {


    val calculatorPreferences: Flow<CalculatorPreferences> = dataStore.data
        .catch { exception ->
            if (exception is IOException) {
                emit(emptyPreferences())
            } else {
                throw exception
            }
        }.map { preferences ->

            val unit = AbvUnit.tryValueOf(preferences[PreferenceKeys.ABV_UNIT_KEY])
            val equation = preferences[PreferenceKeys.ABV_EQUATION_KEY] ?: AbvEquation.Simple.name

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

            val theme = AppTheme.tryValueOf(preferences[PreferenceKeys.APP_THEME_KEY])
            val inDarkMode = preferences[PreferenceKeys.APP_IS_IN_DARK_MODE]

            SettingPreferences(
                appTheme = theme,
                inDarkMode = inDarkMode
            )
        }.catch { emit(SettingPreferences()) }

    suspend fun saveEquation(value: String) {
        dataStore.edit { settings ->
            settings[PreferenceKeys.ABV_EQUATION_KEY] = value
        }
    }

    suspend fun saveUnit(value: String) {
        dataStore.edit { settings ->
            settings[PreferenceKeys.ABV_UNIT_KEY] = value
        }
    }

    suspend fun saveAppTheme(value: AppTheme) {
        dataStore.edit { settings ->
            settings[PreferenceKeys.APP_THEME_KEY] = value.name
        }
    }

    suspend fun saveDarkModeState(value: Boolean) {
        dataStore.edit { settings ->
            settings[PreferenceKeys.APP_IS_IN_DARK_MODE] = value
        }
    }

}
