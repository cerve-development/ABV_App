package com.cerve.abv.shared.domain

import com.cerve.abv.shared.StorageManager
import com.cerve.abv.shared.model.AbvTestEquation
import com.cerve.abv.shared.repository.EquationRepository
import org.koin.core.component.KoinComponent

class SetCalculatorEquationUseCase(
    private val preferences: StorageManager,
    private val equationRepository: EquationRepository
) : KoinComponent {

    suspend operator fun invoke(data: AbvTestEquation) {
        preferences.saveEquation(data.name)
        equationRepository.updateEquationList(data)
    }

}