package com.nowiwr01.stop_smoking.utils.logger

import com.nowiwr01.domain.model.user.User
import com.nowiwr01.stop_smoking.utils.logger.model.Action
import com.nowiwr01.stop_smoking.utils.logger.model.Category

class Logger(
    private val logger: ILogger
) {
    /***
     * AUTH
     */
    fun logAuth(userData: Pair<String, User>) {
        logger.logEvent(Category.AUTH, Action.AUTH_TYPE, userData.first)
        logger.logEvent(Category.AUTH, Action.AUTH_THROUGH, userData.second.authMethod)
    }
}