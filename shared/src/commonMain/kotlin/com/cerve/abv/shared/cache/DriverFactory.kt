package com.cerve.abv.shared.cache

import app.cash.sqldelight.db.SqlDriver
import com.cerve.co.abv.shared.cache.SharedDatabase
import kotlin.reflect.KFunction0

expect class DriverFactory {
    fun createDriver(dbName: String): SqlDriver
}

private const val SIMPLE_ABV_DATABASE_NAME = "cerve_simple_abv.db"

fun createDatabase(factory: KFunction0<DriverFactory>): SharedDatabase {
    val driver = factory().createDriver(SIMPLE_ABV_DATABASE_NAME)
    return SharedDatabase(driver)
}