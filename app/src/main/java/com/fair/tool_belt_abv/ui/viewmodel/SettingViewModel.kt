package com.fair.tool_belt_abv.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cerve.abv.shared.StorageManager
import com.cerve.abv.shared.model.AppTheme
import com.fair.tool_belt_abv.model.AppState
import com.fair.tool_belt_abv.ui.screen.Screen
import com.fair.tool_belt_abv.ui.screen.Screen.Companion.asScreenStateIn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

class SettingViewModel(
    private val storageManager: StorageManager
) : ViewModel() {

    val uiState = storageManager.settingPreferences.map {
        val state = AppState(
            inDarkMode = it.inDarkMode,
            colorSchemePalette = it.appTheme
        )
        Screen.Loaded(state)
    }.asScreenStateIn(AppState(), viewModelScope)

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

}
