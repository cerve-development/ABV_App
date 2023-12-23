package com.cerve.abv.shared.domain

import com.cerve.abv.shared.model.AbvUnit
import com.cerve.abv.shared.model.ConverterResult
import com.cerve.abv.shared.util.Equation
import org.koin.core.component.KoinComponent

class ConverterUseCase: KoinComponent {

    operator fun invoke(
        unit: AbvUnit,
        value: String
    ): ConverterResult {
        val converter = value.toConverterDouble()

        return when (unit) {
            AbvUnit.SG -> {
                val plato = Equation.Converter(converter).sToP.toString()
                val brix = Equation.Converter(converter).sToB.toString()

                ConverterResult(gravity = value, plato = plato, brix = brix)
            }
            AbvUnit.P -> {

                val convertedGravity = Equation.Converter(converter).pToS
                val gravity = convertedGravity.toString()
                val brix = Equation.Converter(convertedGravity).sToB.toString()

                ConverterResult(gravity = gravity, plato = value, brix = brix)
            }
            AbvUnit.B -> {

                val convertedGravity = Equation.Converter(converter).bToS
                val gravity = convertedGravity.toString()
                val plato = Equation.Converter(convertedGravity).sToP.toString()

                ConverterResult(gravity = gravity, plato = plato, brix = value)
            }
        }
    }

    private fun String.toConverterDouble(): Double {
        return this.toDoubleOrNull()?: 0.0
    }
}