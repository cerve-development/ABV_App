package com.fair.tool_belt_abv.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cerve.abv.shared.domain.NewEquationUseCase
import com.cerve.abv.shared.domain.SetCalculatorEquationUseCase
import com.cerve.abv.shared.model.AbvTestEquation
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flatMapMerge
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class EquationCreationViewModel(
    name: String?,
    private val setCalculatorEquationUseCase: SetCalculatorEquationUseCase,
    equationUseCase: NewEquationUseCase,
) : ViewModel() {

    init {
        println(name)
    }

    private val _userInputName = MutableStateFlow(EMPTY_STRING)
    private val _userInputEquation = MutableStateFlow(EMPTY_STRING)
    @OptIn(ExperimentalCoroutinesApi::class)
    val uiState = combine(
        _userInputEquation.flatMapLatest { equation -> equationUseCase.invoke(equation) },
        _userInputName
    ) { result, name ->
        UiState(
            name = name,
            equation = result.equation,
            solution = result.sample
        )
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.Lazily,
        initialValue = UiState()
    )

    fun updateEquation(equation: String) = _userInputEquation.update { equation }
    fun updateName(name: String) = _userInputName.update { name }

    fun saveEquation(state: UiState) = viewModelScope.launch {
        setCalculatorEquationUseCase.invoke(
            AbvTestEquation.Entity(
                dbName = state.name,
                dbEquation = state.equation
            )
        )
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