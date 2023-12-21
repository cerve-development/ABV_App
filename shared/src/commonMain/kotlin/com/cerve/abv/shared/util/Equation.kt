package com.cerve.abv.shared.util

import kotlin.math.pow
class Equation(
    private val og: Double,
    private val fg: Double,
    private val roundingPlaces: Int = 2
) {

    fun alternative(roundTo: Int = roundingPlaces) : Double {
        return (76.08 * (og - fg) / (1.775 - og) * (fg / 0.794)).round(roundTo)

    }
    fun default(roundTo: Int = roundingPlaces) : Double {
        return ((og - fg) * 131.25).round(roundTo)
    }

    fun attenuation(roundTo: Int = roundingPlaces) : Double {
        return (((og - fg) / (og - 1) * 100)).round(roundTo)
    }

    fun custom(equation: String, roundTo: Int = roundingPlaces) : Double {
        return 0.0
    }

    // brix, plato and sg converters
    val sToB = { sg: Double -> (((182.4601 * sg - 775.6821) * sg + 1262.7794) * sg - 669.5622).round(4) }
    val bToS = { bx: Double -> ((bx / (258.6 - ((bx / 258.2) * 227.1))) + 1).round(4) }
    val pToS = { pl: Double -> (1 + (pl / (258.6 - ((pl / 258.2) * 227.1)))).round(4) }
    val sToP = { sg: Double -> ((-1 * 616.868) + (1111.14 * sg) - (630.272 * sg.pow(2)) + (135.997 * sg.pow(3))).round(4) }

    internal fun Double.round(places: Int): Double {
        var multi = 1.0
        repeat(places) { multi *= 10 }
        return kotlin.math.round(this * multi) / multi
    }
}
