package com.fair.tool_belt_abv.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cerve.abv.shared.StorageManager
import com.cerve.abv.shared.domain.CalculateResultUseCase
import com.fair.tool_belt_abv.model.AbvEquation
import com.fair.tool_belt_abv.model.AbvUnit
import com.fair.tool_belt_abv.model.CalculatorState
import com.fair.tool_belt_abv.model.UserInput
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class CalculatorViewModel(
    private val preferences: StorageManager,
    calculateResult: CalculateResultUseCase
) : ViewModel() {

    private val userInput = MutableStateFlow(UserInput())
    val state =
        combine(preferences.calculatorPreferences, userInput) { pref, input ->
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
            unit = AbvUnit.valueOf(pref.abvUnit.name),
            equation = AbvEquation.valueOf(pref.abvEquation.name),
            errorMessage = result.errorMessage
        )
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000),
        initialValue = null
    )

    fun updateEquation(equation: AbvEquation) {
        viewModelScope.launch {
            preferences.saveEquation(com.cerve.abv.shared.model.AbvEquation.valueOf(equation.name))
        }
    }

    fun updateUnit(unit: AbvUnit) {
        viewModelScope.launch {
            preferences.saveUnit(com.cerve.abv.shared.model.AbvUnit.valueOf(unit.name))
        }
    }
    fun updateOriginalValue(value: String) {
        userInput.update { it.copy(originalText = value) }
    }
    fun updateFinalValue(value: String) {
        userInput.update { it.copy(finalText = value) }
    }
}
