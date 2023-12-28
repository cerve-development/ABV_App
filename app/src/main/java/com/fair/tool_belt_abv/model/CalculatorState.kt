package com.fair.tool_belt_abv.model

import com.cerve.abv.shared.model.AbvTestEquation

data class CalculatorState(
    val original: String,
    val final: String,
    val abv: String,
    val attenuation: String,
    val errorMessage: String?,
    val unit: AbvUnit,
    val equationList: List<AbvTestEquation.Entity>
)
