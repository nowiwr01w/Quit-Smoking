package com.nowiwr01.stop_smoking.presentation.main.auth.social

import android.content.Intent
import com.nowiwr01.stop_smoking.presentation.main.auth.AuthViewModel
import com.nowiwr01.stop_smoking.utils.handler.ActivityResultHandler
import com.vk.api.sdk.VK
import com.vk.api.sdk.auth.VKAccessToken
import com.vk.api.sdk.auth.VKAuthCallback
import com.vk.api.sdk.exceptions.VKAuthException

class VKAuthActivityResultHandler(
    private val viewModel: AuthViewModel
): ActivityResultHandler {

    internal fun onSuccessVKCallback(token: VKAccessToken) {
        viewModel.authVk(token)
    }

    internal fun onErrorVKCallback(error: VKAuthException) {
        viewModel.showProgress(false)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?): Boolean {
        val callback = object : VKAuthCallback {
            override fun onLogin(token: VKAccessToken) {
                onSuccessVKCallback(token)
            }
            override fun onLoginFailed(authException: VKAuthException) {
                onErrorVKCallback(authException)
            }
        }
       return VK.onActivityResult(requestCode, resultCode, data, callback)
    }
}