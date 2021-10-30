package com.nowiwr01.stop_smoking.utils.logger

import com.google.firebase.analytics.ktx.analytics
import com.google.firebase.analytics.ktx.logEvent
import com.google.firebase.ktx.BuildConfig
import com.google.firebase.ktx.Firebase
import com.nowiwr01.stop_smoking.utils.logger.model.Action
import com.nowiwr01.stop_smoking.utils.logger.model.Category

class FirebaseLogger: ILogger {
    override fun logEvent(category: Category, action: Action, value: String) {
        if (!BuildConfig.DEBUG) return
        Firebase.analytics.logEvent(category.category) {
            param(action.action, value)
        }
    }
}