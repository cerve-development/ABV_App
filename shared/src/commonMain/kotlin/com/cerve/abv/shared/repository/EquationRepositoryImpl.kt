package com.cerve.abv.shared.repository

import app.cash.sqldelight.coroutines.asFlow
import app.cash.sqldelight.coroutines.mapToList
import com.cerve.abv.shared.model.AbvTestEquation
import com.cerve.co.abv.cache.AbvEquationDBQueries
import com.cerve.co.abv.cache.AbvEquationEntity
import org.koin.core.component.KoinComponent

class EquationRepositoryImpl(
    private val abvEquationDBQueries: AbvEquationDBQueries
) : KoinComponent, EquationRepository {

    override fun equationList(): List<AbvTestEquation.Entity> {
        return abvEquationDBQueries.selectAbvEquationEntity(::)//.asFlow().mapToList()
    }

    companion object {
        fun AbvEquationEntity.mapToEntity() : AbvTestEquation.Entity {
            AbvTestEquation.Entity(

            )
        }
    }
}