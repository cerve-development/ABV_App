package com.fair.tool_belt_abv

import androidx.lifecycle.ViewModel
import com.fair.tool_belt_abv.model.AbvUnit
import com.fair.tool_belt_abv.util.Converter
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class ConverterViewModel @Inject constructor() : ViewModel() {

    private val _result = MutableStateFlow(ConverterResult())
    val result = _result.asStateFlow()

    fun updateValue(unit: AbvUnit, value: String) {
        _result.update { result ->
            when (unit) {
                AbvUnit.SG -> {
                    val (plato, brix) = Converter.convert(focused = unit.name, value = value)
                    result.copy(sg = value, plato = plato, brix = brix)
                }
                AbvUnit.P -> {
                    val (brix, gravity) = Converter.convert(focused = unit.name, value = value)
                    result.copy(plato = value, brix = brix, sg = gravity)
                }
                AbvUnit.B -> {
                    val (gravity, plato) = Converter.convert(focused = unit.name, value = value)
                    result.copy(brix = value, sg = gravity, plato = plato)
                }
            }
        }
    }
}

data class ConverterResult(
    val brix: String = "",
    val plato: String = "",
    val sg: String = ""
)
