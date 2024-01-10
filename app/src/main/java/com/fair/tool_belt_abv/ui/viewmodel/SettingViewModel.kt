package com.fair.tool_belt_abv.ui.viewmodel

import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cerve.abv.shared.StorageManager
import com.cerve.abv.shared.model.AppTheme
import com.fair.tool_belt_abv.ui.screen.Screen
import com.fair.tool_belt_abv.ui.screen.Screen.Companion.asScreenStateIn
import com.fair.tool_belt_abv.ui.theme.HopsLightColors
import com.fair.tool_belt_abv.ui.theme.LagerLightColors
import com.fair.tool_belt_abv.ui.theme.LegacyLightColors
import com.fair.tool_belt_abv.ui.theme.StoutLightColors
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

class SettingViewModel(
    private val storageManager: StorageManager
) : ViewModel() {

    val uiState = storageManager.settingPreferences.map {
        Screen.Loaded(SettingsState(it.inDarkMode, it.appTheme))
    }.asScreenStateIn(SettingsState(), viewModelScope)

    fun updateAppTheme(appTheme: AppTheme) {
        viewModelScope.launch {
            storageManager.saveAppTheme(appTheme)
        }
    }

    fun updateDarkModeValue(value: Boolean) {
        viewModelScope.launch {
            storageManager.saveDarkModeState(value)
        }
    }

    data class SettingsState(
        val inDarkMode: Boolean? = null,
        val colorSchemePalette: AppTheme = AppTheme.LEGACY
    ) {
        companion object {
            data class Selected(
                val color: Color,
                val theme: AppTheme
            )
            fun primaryColors() = AppTheme.entries.mapNotNull { mappingTheme ->
                when (mappingTheme) {
                    AppTheme.LEGACY -> Selected(LegacyLightColors.primary, mappingTheme)
                    AppTheme.STOUT -> Selected(StoutLightColors.primary, mappingTheme)
                    AppTheme.HOPS -> Selected(HopsLightColors.primary, mappingTheme)
                    AppTheme.LAGER -> Selected(LagerLightColors.primary, mappingTheme)
                    else -> null
                }
            }
        }
    }
}
