package com.fair.tool_belt_abv.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cerve.abv.shared.domain.NewEquationUseCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.flatMapMerge
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update

class EquationCreationViewModel(
    equationUseCase: NewEquationUseCase,
) : ViewModel() {

    private val _userInput = MutableStateFlow(EMPTY_STRING)
    @OptIn(ExperimentalCoroutinesApi::class)
    val uiState = _userInput.flatMapMerge { equation ->
        equationUseCase.invoke(equation)
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.Lazily,
        initialValue = NewEquationUseCase.State()
    )

    fun updateEquation(equation: String) = _userInput.update { equation }

    companion object {
        const val EMPTY_STRING = ""
    }

}