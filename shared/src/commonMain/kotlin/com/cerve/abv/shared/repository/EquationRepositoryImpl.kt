package com.cerve.abv.shared.repository

import app.cash.sqldelight.coroutines.asFlow
import app.cash.sqldelight.coroutines.mapToList
import com.cerve.abv.shared.model.AbvEquation
import com.cerve.co.abv.cache.AbvEquationDBQueries
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import org.koin.core.component.KoinComponent

class EquationRepositoryImpl(
    private val abvEquationDBQueries: AbvEquationDBQueries
) : KoinComponent, EquationRepository {
    override suspend fun findEquationByName(name: String): AbvEquation? {
        return abvEquationDBQueries.selectAbvEquationByNameEntity(name,::mapToEntity)
            .executeAsOneOrNull()
    }

    override fun equationList(): Flow<List<AbvEquation>> {
        return abvEquationDBQueries
            .selectAbvEquationEntity(::mapToEntity)
            .asFlow().mapToList(Dispatchers.Default)
            .map { customList ->
                listOf(AbvEquation.Simple, AbvEquation.Advance) + customList
            }

    }

    override suspend fun updateEquationList(
        data: AbvEquation.Entity
    ) {
        abvEquationDBQueries.upsertAbvEquationEntity(
            name = data.name,
            equation = data.equation,
            updatedAt = data.updatedAtOrNow()
        )
    }

    override suspend fun deleteEquation() {
        TODO("Not yet implemented")
    }


    companion object {
        fun mapToEntity(
            name: String,
            equation: String,
            updatedAt: Long
        ) : AbvEquation.Entity {
            return AbvEquation.Entity(
                dbName = name,
                dbEquation = equation,
                dbUpdatedAt = updatedAt
            )
        }
    }
}