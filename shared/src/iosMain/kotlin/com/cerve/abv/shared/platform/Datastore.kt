package com.cerve.abv.shared.platform

import androidx.datastore.core.DataMigration
import androidx.datastore.core.DataStore
import androidx.datastore.core.handlers.ReplaceFileCorruptionHandler
import androidx.datastore.preferences.core.Preferences
import kotlinx.cinterop.ExperimentalForeignApi
import kotlinx.coroutines.CoroutineScope

actual fun dataStorePreferences(
    corruptionHandler: ReplaceFileCorruptionHandler<Preferences>?,
    coroutineScope: CoroutineScope,
    migrations: List<DataMigration<Preferences>>,
): DataStore<Preferences> = createDataStoreWithDefaults(
    corruptionHandler = corruptionHandler,
    migrations = migrations,
    coroutineScope = coroutineScope,
    path = {
        val documentDirectory: NSURL? = NSFileManager.defaultManager.URLForDirectory(
            directory = NSDocumentDirectory,
            inDomain = NSUserDomainMask,
            appropriateForURL = null,
            create = false,
            error = null,
        )
        (requireNotNull(documentDirectory).path + "/$SIMPLE_ABV_PREFERENCES")
    }
)