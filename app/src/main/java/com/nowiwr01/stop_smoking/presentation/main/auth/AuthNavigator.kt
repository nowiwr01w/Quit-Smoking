package com.nowiwr01.stop_smoking.presentation.main.auth

import com.facebook.login.LoginManager
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.nowiwr01.stop_smoking.Const
import com.nowiwr01.stop_smoking.utils.extensions.navigate
import com.vk.api.sdk.VK
import com.vk.api.sdk.auth.VKScope

class AuthNavigator(
    private val fragment: AuthFragment
) {
    fun toHomeScreen() {
        fragment.navigate(AuthFragmentDirections.actionAuthToHome(), true)
    }

    fun toSmokeInfoScreen() {
        fragment.navigate(AuthFragmentDirections.actionAuthToSmokeInfo(), true)
    }

    fun toVkAuth() {
        val permissions = arrayListOf(VKScope.EMAIL)
        VK.login(fragment.baseActivity, permissions)
    }

    fun toFacebookAuth() {
        val permissions = listOf("email", "public_profile")
        LoginManager.getInstance().logInWithReadPermissions(fragment.baseActivity, permissions)
    }

    fun toGoogleAuth() {
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestEmail()
            .requestProfile()
            .requestIdToken(Const.GOOGLE_CLIENT_ID)
            .build()
        val client = GoogleSignIn.getClient(fragment.baseActivity, gso)
        fragment.baseActivity.startActivityForResult(
            client.signInIntent, Const.GOOGLE_MARK
        )
    }
}