package com.fair.tool_belt_abv.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fair.tool_belt_abv.data.StorageManager
import com.fair.tool_belt_abv.model.AppState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val storageManager: StorageManager
) : ViewModel() {

    val appState = combine(
        storageManager.settingPreferences,
        storageManager.shouldShowRating
    ) { settings, showRating ->
        val (light, dark) = settings.appTheme.selectedTheme()

        AppState(
            abvUnit = settings.abvUnit,
            abvEquation = settings.abvEquation,
            inDarkMode = settings.inDarkMode,
            colorSchemePalette = settings.appTheme,
            colorSchemeLight = light,
            colorSchemeDark = dark,
            isLoading = false,
            showReview = showRating
        )
    }.stateIn(
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
