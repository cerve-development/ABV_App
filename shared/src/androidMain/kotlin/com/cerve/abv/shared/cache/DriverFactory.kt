package com.cerve.abv.shared.cache

import android.content.Context
import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.android.AndroidSqliteDriver
import com.cerve.abv.shared.model.AbvTestEquation.Default.name
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