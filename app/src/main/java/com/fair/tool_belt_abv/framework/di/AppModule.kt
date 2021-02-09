package com.fair.tool_belt_abv.framework.di

import android.content.Context
import com.fair.tool_belt_abv.framework.SimpleABVApplication
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideApplication(@ApplicationContext _context: Context) : SimpleABVApplication {
        return _context as SimpleABVApplication
    }

}