package com.nowiwr01.stop_smoking.utils.logger

import com.nowiwr01.stop_smoking.utils.logger.model.Action
import com.nowiwr01.stop_smoking.utils.logger.model.Category

interface ILogger {
    fun logEvent(category: Category, action: Action, value: String)
}