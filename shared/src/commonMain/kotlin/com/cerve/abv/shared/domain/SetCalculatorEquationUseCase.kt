package com.cerve.abv.shared.domain

import com.cerve.abv.shared.model.AbvEquation
import com.cerve.abv.shared.repository.EquationRepository
import org.koin.core.component.KoinComponent

class SetCalculatorEquationUseCase(
    private val equationRepository: EquationRepository
) : KoinComponent {

    suspend operator fun invoke(data: AbvEquation.Entity) {
        equationRepository.updateEquationList(data)
    }

}