package com.cerve.abv.shared.domain

import com.cerve.abv.shared.StorageManager
import com.cerve.abv.shared.model.AbvTestEquation
import com.cerve.abv.shared.model.AbvUnit
import com.cerve.abv.shared.repository.EquationRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import org.koin.core.component.KoinComponent

class CalculatorUseCase(
    private val preferences: StorageManager,
    private val equationRepository: EquationRepository
) : KoinComponent {

    operator fun invoke(
        original: String,
        final: String
    ): Flow<CalculatorResult> = combine(
        equationRepository.equationList(),
        preferences.calculatorPreferences
    ) { equations, pref ->
        val abvEquation = equations.find { equation ->
            equation.name == pref.abvEquation
        } ?: AbvTestEquation.Simple

        CalculatorResult(
            original = original,
            final = final,
            equation = abvEquation,
            unit = AbvUnit.B,
            equations = equations
        )
    }

    data class CalculatorResult(
        val original: String,
        val final: String,
        val abv: String = "0.0",
        val attenuation: String = "0.0",
        val warning: Warning = Warning.None,
        val unit: AbvUnit,
        val equation: AbvTestEquation,
        val equations: List<AbvTestEquation>
    )

    enum class Warning {
        None
    }
}