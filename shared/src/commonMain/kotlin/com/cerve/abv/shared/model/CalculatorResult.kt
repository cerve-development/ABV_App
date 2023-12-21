package com.cerve.abv.shared.model

data class CalculatorResult(
    val abv: String = "0",
    val attenuation: String = "0",
    val errorMessage: String? = null
)
