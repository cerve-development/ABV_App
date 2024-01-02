package com.cerve.abv.shared.model.preferences

import com.cerve.abv.shared.model.AbvUnit

data class CalculatorPreferences(
    val abvUnit: AbvUnit,
    val abvEquation: String
)
