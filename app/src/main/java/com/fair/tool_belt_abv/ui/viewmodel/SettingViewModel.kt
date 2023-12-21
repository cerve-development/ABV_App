package com.fair.tool_belt_abv.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fair.tool_belt_abv.model.AbvEquation
import com.fair.tool_belt_abv.model.AbvUnit
import com.fair.tool_belt_abv.model.AppTheme
import kotlinx.coroutines.launch

class SettingViewModel(
//    private val storageManager: StorageManager
) : ViewModel() {

    fun updateEquation(equation: AbvEquation) {
        viewModelScope.launch {
//            storageManager.saveEquation(equation)
        }
    }

    fun updateUnit(unit: AbvUnit) {
        viewModelScope.launch {
//            storageManager.saveUnit(unit)
        }
    }

    fun updateAppTheme(appTheme: AppTheme) {
        viewModelScope.launch {
//            storageManager.saveAppTheme(appTheme)
        }
    }

    fun updateDarkModeValue(value: Boolean) {
        viewModelScope.launch {
//            storageManager.saveDarkModeState(value)
        }
    }
}
