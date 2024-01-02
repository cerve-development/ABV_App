package com.cerve.abv.shared.domain

import com.cerve.abv.shared.StorageManager
import org.koin.core.component.KoinComponent

class SetSelectedAbvUnitUseCase(
    private val preferences: StorageManager
) : KoinComponent {
    suspend operator fun invoke(name: String) {
        preferences.saveUnit(name)
    }
}