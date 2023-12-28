package com.cerve.abv.shared.domain

import com.cerve.abv.shared.StorageManager
import com.cerve.abv.shared.model.AbvTestEquation
import com.cerve.abv.shared.model.AbvUnit
import com.cerve.abv.shared.repository.EquationRepository
import com.cerve.abv.shared.util.Equation
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import org.koin.core.component.KoinComponent

class CalculatorUseCase(
    private val preferences: StorageManager,
    private val equationRepository: EquationRepository
) : KoinComponent {

    operator fun invoke(
        original: String,
        final: String,
//        unit: AbvUnit,
//        equation: AbvEquation
    ): Flow<CalculatorResult> = combine(
        equationRepository.equationList(),
        preferences.calculatorPreferences
    ) { equations, prefrences ->

//        val result = unit.mapToOriginalAndFinal(
//            og = original.toCalculatorDouble(),
//            fg = final.toCalculatorDouble()
//        )
//
//        val attenuation = Equation.Calculator(result.og, result.fg).attenuation().toString()
//
//        val abv = when(equation) {
//            AbvEquation.S -> Equation.Calculator(result.og, result.fg).default()
//            AbvEquation.A -> Equation.Calculator(result.og, result.fg).alternative()
//            AbvEquation.C -> Equation.Calculator(result.og, result.fg).custom("1+3*2/3")
//        }.toString()
//
//        return CalculatorResult(attenuation = attenuation, abv = abv)

        CalculatorResult(
            original = original,
            final = final,
            equation = AbvTestEquation.Simple,
            unit = AbvUnit.B,
            equations = equations
        )
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
        val original: String,
        val final: String,
        val abv: String = "0.0",
        val attenuation: String = "0.0",
        val warning: Warning = Warning.None,
        val unit: AbvUnit,
        val equation: AbvTestEquation,
        val equations: List<AbvTestEquation>
    )

    data class OriginalAndFinal(
        val og: Double,
        val fg: Double
    )

    enum class Warning {
        None
    }
}