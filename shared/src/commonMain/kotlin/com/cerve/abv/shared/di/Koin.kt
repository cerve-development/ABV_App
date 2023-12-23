package com.cerve.abv.shared.di

import com.cerve.abv.shared.StorageManager
import com.cerve.abv.shared.domain.CalculatorUseCase
import com.cerve.abv.shared.domain.ConverterUseCase
import com.cerve.abv.shared.platform.dataStorePreferences
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.SupervisorJob
import org.koin.core.context.startKoin
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

fun appModule() = listOf(commonModule)

val commonModule = module {
    single { dataStorePreferences(
        coroutineScope = CoroutineScope(Dispatchers.IO + SupervisorJob()),
        corruptionHandler = null,
        migrations = emptyList()
    ) }
    singleOf(::StorageManager)
    singleOf(::CalculatorUseCase)
    singleOf(::ConverterUseCase)
}