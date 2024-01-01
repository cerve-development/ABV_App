package com.cerve.abv.shared.repository

import com.cerve.abv.shared.model.AbvTestEquation
import kotlinx.coroutines.flow.Flow

interface EquationRepository {
    fun equationList() : Flow<List<AbvTestEquation>>

    suspend fun updateEquationList(data: AbvTestEquation) : Unit
}