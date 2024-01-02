package com.cerve.abv.shared.domain

import com.cerve.abv.shared.model.AbvTestEquation
import com.cerve.abv.shared.model.AbvUnit
import com.cerve.abv.shared.util.Equation
import org.koin.core.component.KoinComponent

class AbvResultUseCase : KoinComponent {

    operator fun invoke(
        og: String,
        fg: String,
        equation: AbvTestEquation,
        unit: AbvUnit
    ) : CalculatorResult {

        Equation.Calculator(og.toAbvDouble(), fg.toAbvDouble()).apply {
            return CalculatorResult(
                abv = custom(equation.equation).toString(),
                attenuation = attenuation().toString()
            )
        }
    }

    private fun String.toAbvDouble() : Double {
        return try {
            toDouble()
        } catch (_: Exception) { 0.0 }
    }

    data class CalculatorResult(
        val abv: String = "0.0",
        val attenuation: String = "0.0",
    )

}