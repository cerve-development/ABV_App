package com.fair.tool_belt_abv.framework.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.core.handlers.ReplaceFileCorruptionHandler
import androidx.datastore.preferences.SharedPreferencesMigration
import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.preferencesDataStoreFile
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.SupervisorJob
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object StorageModule {

    private const val SIMPLE_ABV_PREFERENCES = "simple_abv_application_settings"

    @Singleton
    @Provides
    fun providePreferencesDataStore(
        @ApplicationContext context: Context
    ): DataStore<Preferences> {
        val sharedPreferencesName = context.packageName + "_preferences"

        return PreferenceDataStoreFactory.create(
            migrations = listOf(SharedPreferencesMigration(context, sharedPreferencesName)),
            corruptionHandler = ReplaceFileCorruptionHandler(
                produceNewData = { emptyPreferences() }
            ),
            scope = CoroutineScope(IO + SupervisorJob()),
            produceFile = { context.preferencesDataStoreFile(SIMPLE_ABV_PREFERENCES) }
        )
    }
}
