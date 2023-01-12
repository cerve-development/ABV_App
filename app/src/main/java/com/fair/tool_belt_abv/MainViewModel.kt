package com.fair.tool_belt_abv

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fair.tool_belt_abv.data.StorageManager
import com.fair.tool_belt_abv.model.AppPreferences
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    storageManager: StorageManager
) : ViewModel() {

    val preferences = storageManager.settingPreferences
        .map { settings ->
            val (light, dark) = settings.appTheme.selectedTheme()

            AppPreferences(
                abvUnit = settings.abvUnit,
                abvEquation = settings.abvEquation,
                inDarkMode = settings.inDarkMode,
                colorSchemePalette = settings.appTheme,
                colorSchemeLight = light,
                colorSchemeDark = dark,
                isLoading = false
            )

        }.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = AppPreferences()
        )

}