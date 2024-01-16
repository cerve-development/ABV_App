package com.fair.tool_belt_abv.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cerve.abv.shared.cache.PreferenceManager
import com.fair.tool_belt_abv.model.AppState
import com.fair.tool_belt_abv.ui.screen.Screen
import com.fair.tool_belt_abv.ui.screen.Screen.Companion.asScreenStateIn
import kotlinx.coroutines.flow.map

class MainViewModel(
    storageManager: PreferenceManager
) : ViewModel() {

    val uiState = storageManager.settingPreferences.map {
        val state = AppState(
            inDarkMode = it.inDarkMode,
            colorSchemePalette = it.appTheme
        )
        Screen.Loaded(state)
    }.asScreenStateIn(AppState(), viewModelScope)

}
