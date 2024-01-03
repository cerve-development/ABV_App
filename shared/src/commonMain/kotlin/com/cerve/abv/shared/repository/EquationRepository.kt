package com.cerve.abv.shared.repository

import com.cerve.abv.shared.model.AbvTestEquation
import kotlinx.coroutines.flow.Flow

interface EquationRepository {

    suspend fun findEquationByName(name: String) : AbvTestEquation?

    fun equationList() : Flow<List<AbvTestEquation>>

    suspend fun updateEquationList(data: AbvTestEquation.Entity) : Unit
    suspend fun deleteEquation()
}