package com.fair.tool_belt_abv.framework

import android.app.Application
import com.cerve.abv.shared.di.initKoin
import com.fair.tool_belt_abv.framework.di.androidModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger

class SimpleABVApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        initKoin {
            androidLogger()
            androidContext(this@SimpleABVApplication)
            modules(androidModule)
        }
    }
}
