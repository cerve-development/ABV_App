package com.cerve.abv.shared.domain

import com.cerve.abv.shared.util.Equation
import com.github.murzagalin.evaluator.Evaluator
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import org.koin.core.component.KoinComponent

class NewEquationUseCase : KoinComponent {

    operator fun invoke(equation: String) : Flow<State> = flow {

        val og = 1.09
        val fg = 1.07

        val result = Equation.Calculator(og, fg)
                .custom(equation)

        State(
            equation = equation,
            sample = "og : $og, fg : $fg = $result"
        ).also { state -> emit(state) }

    }.catch {
        State(
            equation = equation,
            sample = null
        ).also { state -> emit(state) }
    }

    data class State(
        val equation: String = "",
        val sample: String? = "",
    )
}