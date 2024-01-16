package com.fair.tool_belt_abv.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cerve.abv.shared.cache.PreferenceManager
import com.fair.tool_belt_abv.model.AppState
import com.fair.tool_belt_abv.ui.screen.Screen
import com.fair.tool_belt_abv.ui.screen.Screen.Companion.asScreenStateIn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

class MainViewModel(
    private val storageManager: PreferenceManager
) : ViewModel() {

    val uiState = storageManager.settingPreferences.map {
        val state = AppState(
            inDarkMode = it.inDarkMode,
            colorSchemePalette = it.appTheme
        )
        Screen.Loaded(state)
    }.asScreenStateIn(AppState(), viewModelScope)

    fun updateInstanceCount() = viewModelScope.launch {
        storageManager.saveInstanceCount { previous -> previous?.plus(1) ?: 1 }
    }
    fun resetRating() = viewModelScope.launch {
        storageManager.saveInstanceCount { 0 }
        storageManager.saveNewRatingTime(System.currentTimeMillis())
    }
}
