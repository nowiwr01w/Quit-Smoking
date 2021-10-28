package com.nowiwr01.stop_smoking.logic.errors

import com.nowiwr01.stop_smoking.ui.main.auth.data.UserHighlightType

interface AuthError {
    val list: List<UserHighlightType>
    val message: String
}