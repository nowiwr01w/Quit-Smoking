package com.nowiwr01.stop_smoking.logic.errors

import com.nowiwr01.stop_smoking.ui.main.login.data.UserHighlightType

interface AuthError {
    val list: List<UserHighlightType>
    val message: String
}