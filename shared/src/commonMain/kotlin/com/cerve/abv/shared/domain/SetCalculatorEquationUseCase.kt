package com.cerve.abv.shared.domain

import com.cerve.abv.shared.StorageManager
import org.koin.core.component.KoinComponent

class SetCalculatorEquationUseCase(
    private val preferences: StorageManager
) : KoinComponent {

    suspend operator fun invoke(name: String) {
        preferences.saveEquation(name)
    }

}