package com.nowiwr01.stop_smoking

import android.app.Application
import com.nowiwr01.stop_smoking.Const.YANDEX_METRIC_KEY
import com.nowiwr01.stop_smoking.di.koinModules
import com.yandex.metrica.YandexMetrica
import com.yandex.metrica.YandexMetricaConfig
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import timber.log.Timber

class App: Application() {

    override fun onCreate() {
        super.onCreate()
        setupKoin()
        setupTimber()
        setupYandexMetric()
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

    private fun setupYandexMetric() {
        val config = YandexMetricaConfig.newConfigBuilder(YANDEX_METRIC_KEY).build()
        YandexMetrica.activate(this, config)
        YandexMetrica.enableActivityAutoTracking(this)
    }
}