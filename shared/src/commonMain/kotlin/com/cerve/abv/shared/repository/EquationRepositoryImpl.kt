package com.cerve.abv.shared.repository

import app.cash.sqldelight.coroutines.asFlow
import app.cash.sqldelight.coroutines.mapToList
import com.cerve.abv.shared.model.AbvTestEquation
import com.cerve.co.abv.cache.AbvEquationDBQueries
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import org.koin.core.component.KoinComponent

class EquationRepositoryImpl(
    private val abvEquationDBQueries: AbvEquationDBQueries
) : KoinComponent, EquationRepository {

    override fun equationList(): Flow<List<AbvTestEquation>> {
        return abvEquationDBQueries
            .selectAbvEquationEntity(::mapToEntity)
            .asFlow().mapToList(Dispatchers.Default).map {
                it + AbvTestEquation.Simple + AbvTestEquation.Advance
            }

    }

    override suspend fun updateEquationList(
        data: AbvTestEquation
    ) {
        abvEquationDBQueries.upsertAbvEquationEntity(
            name = data.name,
            equation = data.equation,
            updatedAt = data.updatedAt
        )
    }


    companion object {
        fun mapToEntity(
            name: String,
            equation: String,
            updatedAt: Long
        ) : AbvTestEquation.Entity {
            return AbvTestEquation.Entity(
                dbName = name,
                dbEquation = equation,
                dbUpdatedAt = updatedAt
            )
        }
    }
}