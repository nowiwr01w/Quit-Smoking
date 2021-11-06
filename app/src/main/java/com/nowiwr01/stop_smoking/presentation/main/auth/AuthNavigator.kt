package com.nowiwr01.stop_smoking.presentation.main.auth

import com.nowiwr01.stop_smoking.R
import com.nowiwr01.stop_smoking.utils.extensions.navigate

class AuthNavigator(
    private val fragment: AuthFragment
) {
    fun toHomeScreen() {
        fragment.navigate(R.id.action_auth_to_home)
    }

    fun toSmokeInfoScreen() {
        fragment.navigate(R.id.action_auth_to_smoke_info)
    }
}