package com.fair.tool_belt_abv.ui.viewmodel

import androidx.lifecycle.ViewModel
import com.fair.tool_belt_abv.domain.ConvertResultUseCase
import com.fair.tool_belt_abv.model.AbvUnit
import com.fair.tool_belt_abv.model.ConverterResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class ConverterViewModel @Inject constructor(
    private val convertResultUseCase: ConvertResultUseCase
) : ViewModel() {

    private val _result = MutableStateFlow(ConverterResult())
    val result = _result.asStateFlow()

    fun updateValue(unit: AbvUnit, value: String) {
        _result.update { result ->
            convertResultUseCase.invoke(
                unit = unit,
                value = value,
                previousResult = result
            )
        }
    }
}
