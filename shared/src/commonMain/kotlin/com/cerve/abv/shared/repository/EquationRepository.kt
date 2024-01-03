package com.cerve.abv.shared.repository

import com.cerve.abv.shared.model.AbvEquation
import kotlinx.coroutines.flow.Flow

interface EquationRepository {

    suspend fun findEquationByName(name: String) : AbvEquation?

    fun equationList() : Flow<List<AbvEquation>>

    suspend fun updateEquationList(data: AbvEquation.Entity) : Unit
    suspend fun deleteEquationByName(name: String)
}