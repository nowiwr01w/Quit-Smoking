package com.nowiwr01.stop_smoking.utils.logger

import com.nowiwr01.stop_smoking.BuildConfig
import org.koin.dsl.module

val analytics = module {
    single { AndroidLogger() }
    single { AppMetricLogger() }
    single { FirebaseLogger() }
    single { createAnalytics() }
    single { Logger(get()) }
}

fun createAnalytics(): ILogger {
    return LoggerHolder().apply {
        add(FirebaseLogger())
        add(AppMetricLogger())
        if (BuildConfig.DEBUG) {
            add(AndroidLogger())
        }
    }
}