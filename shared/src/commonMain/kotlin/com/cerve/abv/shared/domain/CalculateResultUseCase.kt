package com.cerve.abv.shared.domain

import com.cerve.abv.shared.helpers.isLeadingDecimal
import com.cerve.abv.shared.model.AbvEquation
import com.cerve.abv.shared.model.AbvUnit
import com.cerve.abv.shared.model.CalculatorResult
import com.cerve.abv.shared.util.Calculator.bCalculator
import com.cerve.abv.shared.util.Calculator.pCalculator
import com.cerve.abv.shared.util.Calculator.sCalculator
import org.koin.core.component.KoinComponent

class CalculateResultUseCase : KoinComponent {

    operator fun invoke(
        original: String,
        final: String,
        unit: AbvUnit,
        equation: AbvEquation
    ): CalculatorResult {
        val num1 = original.isLeadingDecimal()
        val num2 = final.isLeadingDecimal()

        val (abv, attenuation, error) = when (unit) {
            AbvUnit.SG -> equation.name.sCalculator(num1, num2)
            AbvUnit.P -> equation.name.pCalculator(num1, num2)
            AbvUnit.B -> equation.name.bCalculator(num1, num2)
        }

        return CalculatorResult(abv = abv, attenuation = attenuation, errorMessage = error)
    }

}