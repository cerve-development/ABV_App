package com.cerve.abv.shared.domain

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import org.koin.core.component.KoinComponent

class NewEquationUseCase : KoinComponent {

    operator fun invoke(equation: String) : Flow<State> = flow {
        emit(State(equation))
    }

    data class State(
        val equation: String = "",
        val sample: String = "",
    )
}