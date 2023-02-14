package com.ironbit.tvshows.framework

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

// Entry point of the application
class TVShows : Application() {

    // Initialize the global application data
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@TVShows)
            modules(appModule)
        }
    }

}