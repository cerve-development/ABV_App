package com.fair.tool_belt_abv.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cerve.abv.shared.domain.CalculatorUseCase
import com.fair.tool_belt_abv.model.AbvEquation
import com.fair.tool_belt_abv.model.AbvUnit
import com.fair.tool_belt_abv.model.UserInput
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.flatMapMerge
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class CalculatorViewModel(
//    private val preferences: StorageManager,
    calculateResultState: CalculatorUseCase
) : ViewModel() {

    private val userInput = MutableStateFlow(UserInput())
    @OptIn(ExperimentalCoroutinesApi::class)
    val state = (userInput).flatMapMerge { input ->
        calculateResultState.invoke(
            original = input.originalText,
            final = input.finalText,
        )
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.Lazily,
        initialValue = null
    )

    fun updateEquation(equation: AbvEquation) {
        viewModelScope.launch {
//            preferences.saveEquation(com.cerve.abv.shared.model.AbvEquation.valueOf(equation.name))
        }
    }

    fun updateUnit(unit: AbvUnit) {
        viewModelScope.launch {
//            preferences.saveUnit(com.cerve.abv.shared.model.AbvUnit.valueOf(unit.name))
        }
    }
    fun updateOriginalValue(value: String) {
        userInput.update { it.copy(originalText = value) }
    }
    fun updateFinalValue(value: String) {
        userInput.update { it.copy(finalText = value) }
    }
}
