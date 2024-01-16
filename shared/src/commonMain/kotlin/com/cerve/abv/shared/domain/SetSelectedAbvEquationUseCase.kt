package com.cerve.abv.shared.domain

import com.cerve.abv.shared.cache.PreferenceManager
import org.koin.core.component.KoinComponent

class SetSelectedAbvEquationUseCase(
    private val preferences: PreferenceManager
) : KoinComponent {

    suspend operator fun invoke(name: String) {
        preferences.saveEquation(name)
    }
}