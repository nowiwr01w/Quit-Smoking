package com.nowiwr01.stop_smoking.auth

import androidx.lifecycle.MutableLiveData
import com.facebook.AccessToken
import com.facebook.login.LoginResult
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import com.nowiwr01.stop_smoking.presentation.main.auth.AuthViewModel
import com.nowiwr01.stop_smoking.presentation.main.auth.social.FacebookAuthActivityResultHandler
import com.nowiwr01.stop_smoking.presentation.main.auth.social.GoogleAuthActivityResultHandler
import com.nowiwr01.stop_smoking.presentation.main.auth.social.VKAuthActivityResultHandler
import com.vk.api.sdk.auth.VKAccessToken
import org.junit.jupiter.api.Test

internal class AuthSocialTest {

    private val progress = mock<MutableLiveData<Boolean>>()
    private val viewModel = mock<AuthViewModel> {
        on { progress } doReturn progress
    }

    private val vkToken = mock<VKAccessToken>()
    private val googleToken = mock<GoogleSignInAccount>()
    private val tokenMock = mock<AccessToken> {
        on { token } doReturn TOKEN
    }
    private val facebookToken = mock<LoginResult> {
        on { accessToken } doReturn tokenMock
    }

    private val vkHandler = VKAuthActivityResultHandler(viewModel)
    private val googleHandler = GoogleAuthActivityResultHandler(viewModel)
    private val facebookHandler = FacebookAuthActivityResultHandler(mock(), mock(), viewModel)

    @Test
    fun `delegate to viewModel when google auth was successful`() {
        googleHandler.onSuccessGoogleCallback(googleToken)
        verify(viewModel).googleAuth(googleToken)
    }

    @Test
    fun `hide progress when google auth failed`() {
        googleHandler.onErrorGoogleCallback(mock())
        verify(viewModel).showProgress(false)
    }

    @Test
    fun `delegate to viewModel when vk auth was successful`() {
        vkHandler.onSuccessVKCallback(vkToken)
        verify(viewModel).authVk(vkToken)
    }

    @Test
    fun `hide progress when vk auth failed`() {
        vkHandler.onErrorVKCallback(mock())
        verify(viewModel).showProgress(false)
    }

    @Test
    fun `delegate to viewModel when facebook auth was successful`() {
        facebookHandler.onSuccessCallback(facebookToken)
        verify(viewModel).facebookAuth(TOKEN)
    }

    @Test
    fun `hide progress when facebook auth failed`() {
        facebookHandler.onErrorCallback(mock())
        verify(viewModel).showProgress(false)
    }

    private companion object {
        const val TOKEN = "token123"
    }
}