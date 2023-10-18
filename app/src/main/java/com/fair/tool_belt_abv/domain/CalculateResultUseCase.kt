package com.fair.tool_belt_abv.domain

import com.fair.tool_belt_abv.model.AbvEquation
import com.fair.tool_belt_abv.model.AbvUnit
import com.fair.tool_belt_abv.model.CalculatorResult
import com.fair.tool_belt_abv.util.Calculator.bCalculator
import com.fair.tool_belt_abv.util.Calculator.pCalculator
import com.fair.tool_belt_abv.util.Calculator.sCalculator
import com.fair.tool_belt_abv.util.isLeadingDecimal
import javax.inject.Inject

class CalculateResultUseCase @Inject constructor() {

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