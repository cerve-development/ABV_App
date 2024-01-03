package com.cerve.abv.shared.domain

import com.cerve.abv.shared.repository.EquationRepository
import org.koin.core.component.KoinComponent

class DeleteEquationUseCase(
    private val equationRepository: EquationRepository
) : KoinComponent {

    suspend operator fun invoke(name: String) {
        equationRepository.deleteEquationByName(name)
    }

}