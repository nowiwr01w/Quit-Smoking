package com.nowiwr01.stop_smoking

import android.app.Application
import com.nowiwr01.stop_smoking.di.koinModules
import net.danlew.android.joda.JodaTimeAndroid
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import timber.log.Timber

class App: Application() {

    override fun onCreate() {
        super.onCreate()
        setupKoin()
        setupTime()
        setupTimber()
    }

    private fun setupTime() {
        JodaTimeAndroid.init(this)
    }

    private fun setupTimber() {
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }

    private fun setupKoin() {
        startKoin {
            if (BuildConfig.DEBUG) {
                androidLogger()
            }
            androidContext(this@App)
            modules(koinModules)
        }
    }
}