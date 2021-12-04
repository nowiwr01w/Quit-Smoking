package com.nowiwr01.stop_smoking.presentation.main.auth.social

import android.app.Activity
import android.content.Intent
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.nowiwr01.stop_smoking.Const.GOOGLE_MARK
import com.nowiwr01.stop_smoking.presentation.main.auth.AuthViewModel
import com.nowiwr01.stop_smoking.utils.handler.ActivityResultHandler
import java.lang.Exception

class GoogleAuthActivityResultHandler(
    private val viewModel: AuthViewModel
) : ActivityResultHandler {

    internal fun onSuccessGoogleCallback(account: GoogleSignInAccount) {
        viewModel.googleAuth(account)
    }

    internal fun onErrorGoogleCallback(error: Exception) {
        viewModel.showProgress(false)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?): Boolean {
        if (resultCode == Activity.RESULT_OK && requestCode == GOOGLE_MARK) {
            GoogleSignIn.getSignedInAccountFromIntent(data)
                .addOnSuccessListener {
                    onSuccessGoogleCallback(it)
                }
                .addOnFailureListener {
                    onErrorGoogleCallback(it)
                }
            return true
        }
        return false
    }
}