package com.nowiwr01.stop_smoking.utils.logger

import com.nowiwr01.stop_smoking.utils.logger.model.Action
import com.nowiwr01.stop_smoking.utils.logger.model.Category

class Logger(
    private val logger: ILogger
) {
    /***
     * AUTH
     */
    fun logAuth(authMethod: String) {
        logger.logEvent(Category.AUTH, Action.AUTH_TYPE, authMethod)
    }
}