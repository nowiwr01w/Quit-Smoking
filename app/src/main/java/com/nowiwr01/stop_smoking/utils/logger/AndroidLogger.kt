package com.nowiwr01.stop_smoking.utils.logger

import com.nowiwr01.stop_smoking.utils.logger.model.Action
import com.nowiwr01.stop_smoking.utils.logger.model.Category
import timber.log.Timber

class AndroidLogger: ILogger {
    override fun logEvent(category: Category, action: Action, value: String) {
        val logged = "{ \"${category.category}\" : { \"${action.action}\" : \"$value\" } }"
        Timber.tag("Logging").d("logged vale = $logged")
    }
}