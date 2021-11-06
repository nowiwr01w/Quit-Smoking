package com.nowiwr01.stop_smoking.presentation.main.auth

import com.nowiwr01.stop_smoking.utils.extensions.navigate

class AuthNavigator(
    private val fragment: AuthFragment
) {
    fun toHomeScreen() {
        fragment.navigate(AuthFragmentDirections.actionAuthToHome(), setOptions = true)
    }

    fun toSmokeInfoScreen() {
        fragment.navigate(AuthFragmentDirections.actionAuthToSmokeInfo(), setOptions = true)
    }
}