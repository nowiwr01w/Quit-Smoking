package com.nowiwr01.stop_smoking.utils.logger

import com.google.firebase.ktx.BuildConfig
import com.nowiwr01.stop_smoking.utils.logger.model.Action
import com.nowiwr01.stop_smoking.utils.logger.model.Category
import com.yandex.metrica.YandexMetrica

class AppMetricLogger: ILogger {
    override fun logEvent(category: Category, action: Action, value: String) {
        if (!BuildConfig.DEBUG) return
        YandexMetrica.reportEvent(
            category.category, "{\"${action.action}\":\"$value\"}"
        )
    }
}