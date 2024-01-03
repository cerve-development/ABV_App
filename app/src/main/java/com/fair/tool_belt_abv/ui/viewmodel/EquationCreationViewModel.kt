package com.fair.tool_belt_abv.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cerve.abv.shared.domain.GetEquationUseCase
import com.cerve.abv.shared.domain.NewEquationUseCase
import com.cerve.abv.shared.domain.SetCalculatorEquationUseCase
import com.cerve.abv.shared.model.AbvEquation
import com.cerve.abv.shared.model.AbvEquation.Custom.stateCopy
import com.fair.tool_belt_abv.ui.screen.Screen
import com.fair.tool_belt_abv.ui.screen.Screen.Companion.asScreenStateIn
import com.fair.tool_belt_abv.ui.screen.Screen.Companion.loaded
import com.fair.tool_belt_abv.ui.screen.Screen.Companion.loading
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.flatMapMerge
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class EquationCreationViewModel(
    name: String?,
    private val getEquationUseCase: GetEquationUseCase,
    private val setCalculatorEquationUseCase: SetCalculatorEquationUseCase,
    equationUseCase: NewEquationUseCase,
) : ViewModel() {

    private val _uiState = MutableStateFlow<Screen<AbvEquation>>(Screen.Loading(AbvEquation.Custom))
    @OptIn(ExperimentalCoroutinesApi::class)
    val uiState = _uiState.flatMapMerge { data ->
        equationUseCase.invoke(data.value.equation).map { solution ->
            val state = UiState(
                name = data.value.name,
                equation = data.value.equation,
                solution = solution
            )
            data.mapValue(state)
        }
    }.asScreenStateIn(UiState(), viewModelScope)

    init {
        viewModelScope.launch {
            val equation = getEquationUseCase.invoke(name)
            _uiState.update { Screen.Loaded(equation) }
        }
    }
    fun updateEquation(
        equation: String
    ) = _uiState.loaded { it.value.stateCopy(equation = equation) }

    fun updateName(
        name: String
    ) = _uiState.loaded { it.value.stateCopy(name = name) }

    fun saveEquation(state: UiState) = viewModelScope.launch {
        _uiState.loading {
            setCalculatorEquationUseCase.invoke(
                AbvEquation.Entity(
                    dbName = state.name,
                    dbEquation = state.equation
                )
            )
            Screen.Event(state = it, type = Screen.EventType.Navigation())
        }
    }

    data class UiState(
        val name: String = EMPTY_STRING,
        val equation: String = EMPTY_STRING,
        val solution: String? = null
    )

    companion object {
        const val EMPTY_STRING = ""
    }

}