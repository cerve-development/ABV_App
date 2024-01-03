package com.cerve.abv.shared.domain

import com.cerve.abv.shared.model.AbvTestEquation
import com.cerve.abv.shared.model.AbvUnit
import com.cerve.abv.shared.util.Equation
import com.cerve.abv.shared.util.Equation.Converter
import org.koin.core.component.KoinComponent

class GetAbvResultUseCase : KoinComponent {

    operator fun invoke(
        og: String,
        fg: String,
        equation: AbvTestEquation,
        unit: AbvUnit
    ) : CalculatorResult {

        val ogConverter = Converter(og.toAbvDouble())
        val fgConverter = Converter(fg.toAbvDouble())

        val (convertedOg, convertedFg) = when(unit) {
            AbvUnit.SG -> Pair(ogConverter.none, fgConverter.none)
            AbvUnit.P -> Pair(ogConverter.pToS, fgConverter.pToS)
            AbvUnit.B -> Pair(ogConverter.bToS, fgConverter.bToS)
        }

        Equation.Calculator(convertedOg, convertedFg).apply {
            return CalculatorResult(
                og = convertedOg.toString(),
                fg = convertedFg.toString(),
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
        val og: String,
        val fg: String,
        val abv: String = "0.0",
        val attenuation: String = "0.0",
    )

}