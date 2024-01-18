package com.cerve.abv.shared.util

import com.cerve.abv.shared.model.AbvEquation
import com.github.murzagalin.evaluator.Evaluator
import kotlin.math.pow

sealed interface Equation {
    class Calculator(
        private val og: Double,
        private val fg: Double,
        private val roundingPlaces: Int = 2
    ) : Equation {

        fun attenuation(roundTo: Int = 0): Double {
            val result = (((og - fg) / (og - 1) * 100)).round(roundTo)
            return when {
                result.isFinite() && result != -(0.0) -> result
                else -> 0.0
            }
        }

        fun custom(equation: String, roundTo: Int = roundingPlaces): Double {
            return Evaluator().evaluateDouble(
                expression = equation,
                values = mapOf(
                    "${AbvEquation.StaticValues.OG}" to og,
                    "${AbvEquation.StaticValues.FG}" to fg
                )
            ).round(roundTo)

//            return try {
//                Evaluator().evaluateDouble(
//                    expression = equation,
//                    values = mapOf(
//                        "${AbvEquation.StaticValues.OG}" to og,
//                        "${AbvEquation.StaticValues.FG}" to fg
//                    )
//                ).round(roundTo)
//            } catch (_ : Exception) { 0.0 }

        }

    }

    // brix, plato and sg converters
    data class Converter(
        private val value: Double,
        private val roundingPlaces: Int = 10
    ) : Equation {

        val sToB = (((182.4601 * value - 775.6821) * value + 1262.7794) * value - 669.5622)
            .round(roundingPlaces)

        val bToS = ((value / (258.6 - ((value / 258.2) * 227.1))) + 1)
            .round(roundingPlaces)

        val pToS = (1 + (value / (258.6 - ((value / 258.2) * 227.1))))
            .round(roundingPlaces)

        val sToP = ((-1 * 616.868) + (1111.14 * value) - (630.272 * value.pow(2)) + (135.997 * value.pow(3)))
            .round(roundingPlaces)

        val none = value.round(roundingPlaces)
    }

    //todo
    fun Double.round(places: Int): Double {
        var multi = 1.0
        repeat(places) { multi *= 10 }
        return kotlin.math.round(this * multi) / multi
    }
}
