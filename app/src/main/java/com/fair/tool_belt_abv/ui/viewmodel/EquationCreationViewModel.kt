package com.fair.tool_belt_abv.ui.viewmodel

import androidx.compose.ui.text.input.TextFieldValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cerve.abv.shared.domain.DeleteEquationUseCase
import com.cerve.abv.shared.domain.GetEquationUseCase
import com.cerve.abv.shared.domain.NewEquationUseCase
import com.cerve.abv.shared.domain.SetCalculatorEquationUseCase
import com.cerve.abv.shared.model.AbvEquation
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
    private val deleteEquationUseCase: DeleteEquationUseCase,
    private val getEquationUseCase: GetEquationUseCase,
    private val setCalculatorEquationUseCase: SetCalculatorEquationUseCase,
    equationUseCase: NewEquationUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow<Screen<UiState>>(Screen.Loading(UiState()))

    @OptIn(ExperimentalCoroutinesApi::class)
    val uiState = _uiState.flatMapMerge { data ->
        equationUseCase.invoke(data.value.equation.text).map { solution ->

            val state = UiState(
                name = data.value.name,
                equation = data.value.equation,
                solution = solution,
                isEditable = name != null
            )
            data.mapValue(state)
        }
    }.asScreenStateIn(UiState(), viewModelScope)

    init {
        viewModelScope.launch {
            val data = getEquationUseCase.invoke(name)
            val state = UiState(equation = TextFieldValue(text = data.equation), name = data.name)
            _uiState.update { Screen.Loaded(state) }
        }
    }
    fun updateEquation(
        equation: TextFieldValue
    ) = _uiState.loaded { state ->
        state.value.copy(equation = equation)
    }

    fun updateName(
        name: String
    ) = _uiState.loaded { state ->
        state.value.copy(name = name)
    }

    fun saveEquation(state: UiState) = viewModelScope.launch {
        _uiState.loading {
            setCalculatorEquationUseCase.invoke(
                AbvEquation.Entity(
                    dbName = state.name,
                    dbEquation = state.equation.text
                )
            )
            Screen.Event(state = it, type = Screen.EventType.Navigation())
        }
    }

    fun deleteEquation(name: String) = viewModelScope.launch {
        _uiState.loading {
            deleteEquationUseCase.invoke(name)

            Screen.Event(state = it, type = Screen.EventType.Navigation())
        }
    }

    data class UiState(
        val name: String = EMPTY_STRING,
        val equation: TextFieldValue = TextFieldValue(),
        val solution: String? = null,
        val isEditable: Boolean = false
    )

    companion object {
        const val EMPTY_STRING = ""
    }
}
