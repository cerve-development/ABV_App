package com.fair.tool_belt_abv.model

data class CalculatorState(
    val original: String,
    val final: String,
    val abv: String,
    val attenuation: String,
    val errorMessage: String?,
    val unit: AbvUnit,
    val equation: AbvEquation
)
