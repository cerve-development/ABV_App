package com.cerve.abv.shared.repository

import com.cerve.co.abv.cache.AbvEquationDBQueries
import org.koin.core.component.KoinComponent

class EquationRepositoryImpl(
    private val abvEquationDBQueries: AbvEquationDBQueries
) : KoinComponent, EquationRepository {

}