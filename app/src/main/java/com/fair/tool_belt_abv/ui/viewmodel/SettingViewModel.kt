package com.fair.tool_belt_abv.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cerve.abv.shared.model.AppTheme
import kotlinx.coroutines.launch

class SettingViewModel(
//    private val storageManager: StorageManager
) : ViewModel() {

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
