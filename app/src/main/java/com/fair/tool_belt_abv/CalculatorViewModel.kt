package com.fair.tool_belt_abv

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fair.tool_belt_abv.data.StorageManager
import com.fair.tool_belt_abv.model.AbvEquation
import com.fair.tool_belt_abv.model.AbvUnit
import com.fair.tool_belt_abv.model.CalculatorResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CalculatorViewModel @Inject constructor(
    private val preferences: StorageManager
) : ViewModel() {

    private val result = MutableStateFlow(CalculatorResult())
    val state = combine(preferences.calculatorPreferences, result) { pref, res ->
        CalculatorState(
            abv = res.abv,
            attenuation = res.attenuation,
            unit = pref.abvUnit,
            equation = pref.abvEquation,
            errorMessage = null
        )
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000),
        initialValue = null
    )

    fun updateEquation(equation: AbvEquation) {
        viewModelScope.launch {
            preferences.saveEquation(equation)
        }
    }

    fun updateUnit(unit: AbvUnit) {
        viewModelScope.launch {
            preferences.saveUnit(unit)
        }
    }

}

data class CalculatorState(
    val abv: String = "0",
    val attenuation: String = "0",
    val errorMessage: String?,
    val unit: AbvUnit,
    val equation: AbvEquation
)