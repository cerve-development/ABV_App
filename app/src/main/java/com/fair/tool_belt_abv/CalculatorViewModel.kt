package com.fair.tool_belt_abv

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fair.tool_belt_abv.data.StorageManager
import com.fair.tool_belt_abv.model.AbvEquation
import com.fair.tool_belt_abv.model.AbvUnit
import com.fair.tool_belt_abv.model.CalculatorResult
import com.fair.tool_belt_abv.model.UserInput
import com.fair.tool_belt_abv.util.Calculator.bCalculator
import com.fair.tool_belt_abv.util.Calculator.pCalculator
import com.fair.tool_belt_abv.util.Calculator.sCalculator
import com.fair.tool_belt_abv.util.isLeadingDecimal
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
    private val preferences: StorageManager
) : ViewModel() {

    private val userInput = MutableStateFlow(UserInput())
    val state = combine(preferences.calculatorPreferences, userInput) { pref, input ->
        val result = calculate(
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

    private fun calculate(
        original: String,
        final: String,
        unit: AbvUnit,
        equation: AbvEquation
    ): CalculatorResult {

        val num1 = original.isLeadingDecimal()
        val num2 = final.isLeadingDecimal()

        val (abv, attenuation, error) = when(unit) {
            AbvUnit.SG -> {
                equation.name.sCalculator(num1, num2)
            }
            AbvUnit.P -> {
                equation.name.pCalculator(num1, num2)
            }
            AbvUnit.B -> {
                equation.name.bCalculator(num1, num2)
            }
        }

        return CalculatorResult(abv = abv, attenuation = attenuation, errorMessage = error)
    }

}

data class CalculatorState(
    val original: String,
    val final: String,
    val abv: String,
    val attenuation: String,
    val errorMessage: String?,
    val unit: AbvUnit,
    val equation: AbvEquation
)