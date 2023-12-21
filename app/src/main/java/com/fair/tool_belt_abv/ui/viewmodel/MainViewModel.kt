package com.fair.tool_belt_abv.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cerve.abv.shared.StorageManager
import com.fair.tool_belt_abv.model.AbvEquation
import com.fair.tool_belt_abv.model.AbvUnit
import com.fair.tool_belt_abv.model.AppState
import com.fair.tool_belt_abv.model.AppTheme
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class MainViewModel(
    private val storageManager: StorageManager
) : ViewModel() {

    val appState = combine(
        storageManager.settingPreferences,
        storageManager.shouldShowRating
    ) { settings, showRating ->

        val (light, dark) = AppTheme.valueOf(settings.appTheme.name).selectedTheme()

        AppState(
            abvUnit = AbvUnit.valueOf(settings.abvUnit.name),
            abvEquation = AbvEquation.valueOf(settings.abvEquation.name),
            inDarkMode = settings.inDarkMode,
            colorSchemePalette = AppTheme.valueOf(settings.appTheme.name),
            colorSchemeLight = light,
            colorSchemeDark = dark,
            isLoading = false,
            showReview = showRating
        )
    }
    .stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000),
        initialValue = AppState()
    )

    fun updateInstanceCount() = viewModelScope.launch {
        storageManager.saveInstanceCount { previous -> previous?.plus(1) ?: 1 }
    }
    fun resetRating() = viewModelScope.launch {
        storageManager.saveInstanceCount { 0 }
        storageManager.saveNewRatingTime(System.currentTimeMillis())
    }
}
