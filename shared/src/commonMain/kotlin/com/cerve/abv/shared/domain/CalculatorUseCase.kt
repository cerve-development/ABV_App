package com.cerve.abv.shared.domain

import com.cerve.abv.shared.model.AbvEquation
import com.cerve.abv.shared.model.AbvUnit
import com.cerve.abv.shared.util.Equation
import org.koin.core.component.KoinComponent

class CalculatorUseCase : KoinComponent {

    operator fun invoke(
        original: String,
        final: String,
        unit: AbvUnit,
        equation: AbvEquation
    ): CalculatorResult {

        val result = unit.mapToOriginalAndFinal(
            og = original.toCalculatorDouble(),
            fg = final.toCalculatorDouble()
        )

        val attenuation = Equation.Calculator(result.og, result.fg).attenuation().toString()

        val abv = when(equation) {
            AbvEquation.S -> Equation.Calculator(result.og, result.fg).default()
            AbvEquation.A -> Equation.Calculator(result.og, result.fg).alternative()
            AbvEquation.C -> Equation.Calculator(result.og, result.fg).custom("1+1")
        }.toString()

        return CalculatorResult(attenuation = attenuation, abv = abv)
    }

    private fun String.toCalculatorDouble(): Double {
        return this.toDoubleOrNull()?: 0.0
    }

    private fun AbvUnit.mapToOriginalAndFinal(
        og: Double,
        fg: Double
    ) : OriginalAndFinal {
        return when(this) {
            AbvUnit.SG -> OriginalAndFinal(og = og, fg = fg)
            AbvUnit.P -> {
                OriginalAndFinal(
                    og = Equation.Converter(og).pToS,
                    fg = Equation.Converter(fg).pToS
                )
            }
            AbvUnit.B -> {
                OriginalAndFinal(
                    og = Equation.Converter(og).bToS,
                    fg = Equation.Converter(fg).bToS
                )
            }
        }
    }

    data class CalculatorResult(
        val abv: String = "0.0",
        val attenuation: String = "0.0",
        val warning: Warning = Warning.None
    )

    data class OriginalAndFinal(
        val og: Double,
        val fg: Double
    )

    enum class Warning {
        None
    }
}