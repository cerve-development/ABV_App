package com.cerve.abv.shared.domain

import com.cerve.abv.shared.StorageManager
import com.cerve.abv.shared.model.AbvTestEquation
import com.cerve.abv.shared.model.AbvUnit
import com.cerve.abv.shared.repository.EquationRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import org.koin.core.component.KoinComponent

class GetAbvDataUseCase(
    private val preferences: StorageManager,
    private val equationRepository: EquationRepository
) : KoinComponent {

    operator fun invoke() : Flow<AbvData> = combine(
        equationRepository.equationList(),
        preferences.calculatorPreferences
    ) { equations, pref ->
        val selectedEquation = equations.find { equation ->
            equation.name == pref.abvEquation
        } ?: AbvTestEquation.Simple

        val selectedUnit = pref.abvUnit

        AbvData(
            unit = selectedUnit,
            unitList = AbvUnit.entries,
            equation = selectedEquation,
            equationList = equations
        )
    }

    data class AbvData(
        val unit: AbvUnit,
        val unitList: List<AbvUnit>,
        val equation: AbvTestEquation,
        val equationList: List<AbvTestEquation>
    )
}