package com.nowiwr01.stop_smoking.utils.extensions

import com.vk.api.sdk.auth.VKAccessToken
import com.vk.api.sdk.auth.VKAuthCallback
import com.vk.api.sdk.exceptions.VKAuthException
import timber.log.Timber

fun createVKAuthCallback(callback: (token: VKAccessToken) -> Unit) = object : VKAuthCallback {
    override fun onLogin(token: VKAccessToken) {
        callback.invoke(token)
    }
    override fun onLoginFailed(authException: VKAuthException) {
        Timber.tag("VK").e("VK auth failed: message = ${authException.message}")
    }
}