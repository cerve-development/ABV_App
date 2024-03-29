package com.fair.tool_belt_abv.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cerve.abv.shared.domain.GetAbvDataUseCase
import com.cerve.abv.shared.domain.GetAbvResultUseCase
import com.cerve.abv.shared.domain.SetSelectedAbvEquationUseCase
import com.cerve.abv.shared.domain.SetSelectedAbvUnitUseCase
import com.cerve.abv.shared.model.AbvEquation
import com.cerve.abv.shared.model.AbvUnit
import com.fair.tool_belt_abv.model.UserInput
import com.fair.tool_belt_abv.ui.screen.Screen
import com.fair.tool_belt_abv.ui.screen.Screen.Companion.asScreenStateIn
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class AbvCalculatorViewModel(
    getResult: GetAbvResultUseCase,
    getData: GetAbvDataUseCase,
    private val selectEquation: SetSelectedAbvEquationUseCase,
    private val selectUnit: SetSelectedAbvUnitUseCase
) : ViewModel() {

    private val _userInput = MutableStateFlow(UserInput())
    val uiState = combine(_userInput, getData.invoke()) { input, data ->

        val result = getResult.invoke(
            og = input.og,
            fg = input.fg,
            unit = data.unit,
            equation = data.equation
        )

        val state = AbvCalculatorState(
            og = input.og,
            fg = input.fg,
            abv = result.abv,
            attenuation = result.attenuation,
            selectedAbvUnit = data.unit,
            abvUnitList = data.unitList,
            selectedEquation = data.equation,
            abvEquationList = data.equationList
        )

        Screen.Loaded<AbvCalculatorState?>(state)
    }.asScreenStateIn(null, viewModelScope)

    fun updateUnit(unit: AbvUnit) = viewModelScope.launch {
        selectUnit.invoke(unit.name)
    }
    fun updateEquation(equation: AbvEquation) = viewModelScope.launch {
        selectEquation.invoke(equation.name)
    }
    fun updateOriginalValue(value: String) = _userInput.update { it.copy(og = value) }
    fun updateFinalValue(value: String) = _userInput.update { it.copy(fg = value) }

    data class AbvCalculatorState(
        val og: String,
        val fg: String,
        val abv: String,
        val attenuation: String,
        val selectedAbvUnit: AbvUnit,
        val abvUnitList: List<AbvUnit>,
        val selectedEquation: AbvEquation,
        val abvEquationList: List<AbvEquation>
    )
}
