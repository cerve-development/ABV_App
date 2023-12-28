package com.cerve.abv.shared.cache

import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.android.AndroidSqliteDriver
import com.cerve.abv.shared.platform.applicationContext
import com.cerve.co.abv.shared.cache.SharedDatabase

actual class DriverFactory {
    actual fun createDriver(dbName: String): SqlDriver {
        return AndroidSqliteDriver(
            schema = SharedDatabase.Schema,
            context = applicationContext,
            name = dbName
        )
    }
}