package com.cerve.abv.shared.util

import com.cerve.abv.shared.model.AbvEquation

sealed class AbvCalculator(
    private val original: String,
    private val final: String,
    private val equation: AbvEquation
) {

    fun calculate() : AbvResult {
        val og = original.toAbvDouble()
        val fg = final.toAbvDouble()

        val attenuation = Equation.aCalculator(og, fg)

        val abv = when(equation) {
            AbvEquation.S -> Equation.simpleE(og, fg)
            AbvEquation.A -> Equation.advancedE(og, fg)
            AbvEquation.C -> 0.0
        }

        return AbvResult(attenuation = attenuation, abv = abv)
    }

//    data class Specific(
//        val original: String,
//        val final: String,
//    ) : AbvCalculator() {
//        fun calculate() {
//
//
//
//        }
//
//    }


    private fun String.toAbvDouble(): Double {
         return this.toDoubleOrNull()?: 0.0
    }
    private fun Double.abvFormatted(i: String = "%.2f") : String {
        return ""//i.format(Locale.,this)
    }

    data class AbvResult(
        val attenuation: String,
        val abv: String,
        val warning: AbvWarning = AbvWarning.None
    )

    enum class AbvWarning {
        None
    }
}

