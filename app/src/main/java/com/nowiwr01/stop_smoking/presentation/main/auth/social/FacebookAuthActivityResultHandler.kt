package com.nowiwr01.stop_smoking.presentation.main.auth.social

import android.content.Intent
import androidx.lifecycle.Lifecycle
import com.facebook.CallbackManager
import com.facebook.FacebookCallback
import com.facebook.FacebookException
import com.facebook.login.LoginManager
import com.facebook.login.LoginResult
import com.nowiwr01.stop_smoking.presentation.main.auth.AuthViewModel
import com.nowiwr01.stop_smoking.utils.extensions.addObservers
import com.nowiwr01.stop_smoking.utils.handler.ActivityResultHandler

class FacebookAuthActivityResultHandler(
    lifecycle: Lifecycle,
    private val loginManager: LoginManager,
    private val viewModel: AuthViewModel
) : ActivityResultHandler {

    private val callbackManager by lazy(LazyThreadSafetyMode.NONE) {
        CallbackManager.Factory.create()
    }

    init {
        lifecycle.addObservers(
            onCreateCallback = {
                subscribeToEvents()
            },
            onDestroyCallback = {
                unsubscribeFromEvents()
            }
        )
    }

    internal fun onSuccessCallback(result: LoginResult) {
        viewModel.facebookAuth(result.accessToken.token)
    }

    internal fun onErrorCallback(error: FacebookException) {
        viewModel.showProgress(false)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?): Boolean {
        return callbackManager.onActivityResult(requestCode, resultCode, data)
    }

    private fun subscribeToEvents() {
        loginManager.registerCallback(callbackManager, object : FacebookCallback<LoginResult> {
            override fun onSuccess(result: LoginResult) {
                onSuccessCallback(result)
            }
            override fun onError(error: FacebookException) {
                onErrorCallback(error)
            }
            override fun onCancel() {}
        })
    }

    private fun unsubscribeFromEvents() {
        loginManager.unregisterCallback(callbackManager)
    }
}