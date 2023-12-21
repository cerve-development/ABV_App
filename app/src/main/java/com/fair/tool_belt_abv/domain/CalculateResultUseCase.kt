package com.fair.tool_belt_abv.domain

//class CalculateResultUseCase @Inject constructor() {
//
//    operator fun invoke(
//        original: String,
//        final: String,
//        unit: AbvUnit,
//        equation: AbvEquation
//    ): CalculatorResult {
//        val num1 = original.isLeadingDecimal()
//        val num2 = final.isLeadingDecimal()
//
//        val (abv, attenuation, error) = when (unit) {
//            AbvUnit.SG -> equation.name.sCalculator(num1, num2)
//            AbvUnit.P -> equation.name.pCalculator(num1, num2)
//            AbvUnit.B -> equation.name.bCalculator(num1, num2)
//        }
//
//        return CalculatorResult(abv = abv, attenuation = attenuation, errorMessage = error)
//    }
//}
