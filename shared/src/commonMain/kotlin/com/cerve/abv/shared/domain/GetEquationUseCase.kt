package com.cerve.abv.shared.domain

import com.cerve.abv.shared.model.AbvEquation
import com.cerve.abv.shared.repository.EquationRepository
import org.koin.core.component.KoinComponent

class GetEquationUseCase(
    private val equationRepository: EquationRepository
) : KoinComponent {
    suspend operator fun invoke(name: String?) : AbvEquation {
        return name?.let {
            equationRepository.findEquationByName(name)
        } ?: AbvEquation.Entity(
            dbName = "",
            dbEquation = ""
        )
    }
}