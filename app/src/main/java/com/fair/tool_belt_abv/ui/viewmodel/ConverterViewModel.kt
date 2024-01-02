package com.fair.tool_belt_abv.ui.viewmodel

import androidx.lifecycle.ViewModel
import com.cerve.abv.shared.domain.ConverterUseCase
import com.cerve.abv.shared.model.AbvUnit
import com.cerve.abv.shared.model.ConverterResult
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class ConverterViewModel(
    private val converter: ConverterUseCase
) : ViewModel() {

    private val _result = MutableStateFlow(ConverterResult())
    val result = _result.asStateFlow()

    fun updateValue(unit: AbvUnit, value: String) {
        _result.update {
            converter.invoke(
                unit = com.cerve.abv.shared.model.AbvUnit.valueOf(unit.name),
                value = value
            )
        }
    }
}
