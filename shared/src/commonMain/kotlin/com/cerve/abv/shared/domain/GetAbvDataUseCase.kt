package com.cerve.abv.shared.domain

import com.cerve.abv.shared.cache.PreferenceManager
import com.cerve.abv.shared.model.AbvEquation
import com.cerve.abv.shared.model.AbvUnit
import com.cerve.abv.shared.repository.EquationRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import org.koin.core.component.KoinComponent

class GetAbvDataUseCase(
    private val preferences: PreferenceManager,
    private val equationRepository: EquationRepository
) : KoinComponent {

    operator fun invoke() : Flow<AbvData> = combine(
        equationRepository.equationList(),
        preferences.calculatorPreferences
    ) { equations, pref ->
        val found =  equations.find { equation -> equation.name == pref.abvEquation }
        val selectedEquation = if(NewEquationUseCase.isValid(found?.equation) && found != null) {
            found
        } else AbvEquation.Simple

        val selectedUnit = pref.abvUnit

        AbvData(
            unit = selectedUnit,
            unitList = AbvUnit.entries,
            equation = selectedEquation,//selectedEquation,
            equationList = equations
        )
    }

    data class AbvData(
        val unit: AbvUnit,
        val unitList: List<AbvUnit>,
        val equation: AbvEquation,
        val equationList: List<AbvEquation>
    )
}