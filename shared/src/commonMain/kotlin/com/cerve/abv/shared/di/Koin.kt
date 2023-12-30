package com.cerve.abv.shared.di

import com.cerve.abv.shared.StorageManager
import com.cerve.abv.shared.cache.DriverFactory
import com.cerve.abv.shared.cache.createDatabase
import com.cerve.abv.shared.cache.dataStorePreferences
import com.cerve.abv.shared.domain.CalculatorUseCase
import com.cerve.abv.shared.domain.ConverterUseCase
import com.cerve.abv.shared.domain.SetCalculatorEquationUseCase
import com.cerve.abv.shared.domain.NewEquationUseCase
import com.cerve.abv.shared.repository.EquationRepository
import com.cerve.abv.shared.repository.EquationRepositoryImpl
import com.cerve.co.abv.shared.cache.SharedDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import org.koin.core.context.startKoin
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.KoinAppDeclaration
import org.koin.dsl.module

fun initKoin(
    appDeclaration: KoinAppDeclaration
) = startKoin {
    modules(appModule())
    appDeclaration()
}
fun initKoin() = initKoin { }

fun appModule() = listOf(cacheModule, repositoryModule, domainModule)

val cacheModule = module {
    single {
        dataStorePreferences(
            coroutineScope = CoroutineScope(Dispatchers.IO + SupervisorJob()),
            corruptionHandler = null,
            migrations = emptyList()
        )
    }
    single { createDatabase(factory = ::DriverFactory) }
    singleOf(::StorageManager)
    singleOf(SharedDatabase::abvEquationDBQueries)
}

val repositoryModule = module {
    singleOf(::EquationRepositoryImpl) { bind<EquationRepository>() }
}

val domainModule = module {
    singleOf(::SetCalculatorEquationUseCase)
    singleOf(::CalculatorUseCase)
    singleOf(::ConverterUseCase)
    singleOf(::NewEquationUseCase)
}
