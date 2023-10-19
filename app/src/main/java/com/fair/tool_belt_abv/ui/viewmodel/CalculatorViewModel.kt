package com.fair.tool_belt_abv.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fair.tool_belt_abv.data.StorageManager
import com.fair.tool_belt_abv.domain.CalculateResultUseCase
import com.fair.tool_belt_abv.model.AbvEquation
import com.fair.tool_belt_abv.model.AbvUnit
import com.fair.tool_belt_abv.model.CalculatorState
import com.fair.tool_belt_abv.model.UserInput
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CalculatorViewModel @Inject constructor(
    private val preferences: StorageManager,
    calculateResult: CalculateResultUseCase
) : ViewModel() {

    private val userInput = MutableStateFlow(UserInput())
    val state = combine(preferences.calculatorPreferences, userInput) { pref, input ->
        val result = calculateResult(
            original = input.originalText,
            final = input.finalText,
            unit = pref.abvUnit,
            equation = pref.abvEquation
        )

        CalculatorState(
            original = input.originalText,
            final = input.finalText,
            abv = result.abv,
            attenuation = result.attenuation,
            unit = pref.abvUnit,
            equation = pref.abvEquation,
            errorMessage = result.errorMessage
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
    fun updateOriginalValue(value: String) {
        userInput.update { it.copy(originalText = value) }
    }
    fun updateFinalValue(value: String) {
        userInput.update { it.copy(finalText = value) }
    }
}
