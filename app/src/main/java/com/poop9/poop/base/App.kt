package com.poop9.poop.base

import android.app.Application
import com.poop9.poop.BuildConfig
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger(logLevel())
            androidContext(this@App)
            modules(appModule)
        }
    }

    private fun logLevel() = if (BuildConfig.DEBUG) Level.DEBUG else Level.INFO
}