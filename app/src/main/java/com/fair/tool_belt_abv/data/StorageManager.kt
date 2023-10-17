package com.fair.tool_belt_abv.data

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import com.fair.tool_belt_abv.data.PreferencesKeys.APP_INSTANCE_COUNT
import com.fair.tool_belt_abv.data.PreferencesKeys.APP_LAST_RATING_TIME
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
import javax.inject.Inject
import kotlin.math.absoluteValue
import kotlin.time.Duration.Companion.minutes
import kotlin.time.DurationUnit
import kotlin.time.toDuration

class StorageManager @Inject constructor(
    private val dataStore: DataStore<Preferences>
) {

    init {
        CoroutineScope(Dispatchers.Default).launch {
            dataStore.edit { preferences ->
                if (preferences[APP_LAST_RATING_TIME] == null) {
                    preferences[APP_LAST_RATING_TIME] = System.currentTimeMillis()
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

    val shouldShowRating: Flow<Boolean> = dataStore.data
        .catch { exception ->
            if (exception is IOException) {
                emit(emptyPreferences())
            } else { throw exception }
        }.map { preferences ->

            val timeToRate = preferences[APP_LAST_RATING_TIME]
                ?.minus(System.currentTimeMillis())?.absoluteValue
                ?.toDuration(DurationUnit.MILLISECONDS)
                ?.let { sinceLastRating -> sinceLastRating > 1.minutes } ?: false

            val instanceCount = preferences[APP_INSTANCE_COUNT]
                .also { println(it) }
                ?.let { count -> count > 5 } ?: false

            timeToRate && instanceCount
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

    suspend fun saveNewRatingTime(value: Long) {
        dataStore.edit { settings ->
            settings[APP_LAST_RATING_TIME] = value
        }
    }

    suspend fun saveInstanceCount(value: (Int?) -> Int) {
        dataStore.edit { settings ->
            settings[APP_INSTANCE_COUNT] = value(settings[APP_INSTANCE_COUNT])
        }
    }
}
