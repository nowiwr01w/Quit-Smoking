package com.nowiwr01.stop_smoking.presentation.main.auth

import androidx.lifecycle.MutableLiveData
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.nowiwr01.domain.model.base.mapBoth
import com.nowiwr01.domain.model.user.User
import com.nowiwr01.domain.model.user.UserData
import com.nowiwr01.domain.model.user.UserDataSignIn
import com.nowiwr01.domain.model.user.UserDataSignUp
import com.nowiwr01.stop_smoking.utils.Event
import com.nowiwr01.domain.model.error.auth.AuthError
import com.nowiwr01.domain.usecase.AuthUseCase
import com.nowiwr01.domain.usecase.UserDataUseCase
import com.nowiwr01.stop_smoking.presentation.base.BaseViewModel
import com.nowiwr01.stop_smoking.presentation.main.auth.AuthFragment.Companion.SIGN_UP
import com.nowiwr01.stop_smoking.utils.logger.Logger
import com.nowiwr01.stop_smoking.utils.toEvent
import com.vk.api.sdk.auth.VKAccessToken
import kotlinx.coroutines.launch

class AuthViewModel(
    private val interactor: AuthUseCase,
    private val userDataInteractor: UserDataUseCase,
    private val logger: Logger,

    val userData: MutableLiveData<Pair<String, User>> = MutableLiveData(),
    val progress: MutableLiveData<Boolean> = MutableLiveData(),
    val authError: MutableLiveData<Event<AuthError>> = MutableLiveData()
): BaseViewModel() {

    var currentMode = SIGN_UP

    fun checkAndAuth(userData: UserData) {
        showProgress(true)
        checkUserInput(userData)
    }

    private fun checkUserInput(userData: UserData) {
        launch {
            userDataInteractor.checkUserInput(userData).mapBoth(::onUserInputValid, ::onError)
        }
    }

    private fun onUserInputValid(userData: UserData) {
        when (userData) {
            is UserDataSignIn -> signIn(userData)
            is UserDataSignUp -> signUp(userData)
        }
    }

    private fun signIn(userData: UserDataSignIn) {
        launch {
            interactor.login(userData).mapBoth(::onSuccess, ::onError)
        }
    }

    private fun signUp(userData: UserDataSignUp) {
        launch {
            interactor.signUp(userData).mapBoth(::onSuccess, ::onError)
        }
    }

    fun authVk(token: VKAccessToken) {
        showProgress(true)
        launch {
            interactor.authVk(token).mapBoth(::onSuccess, ::onError)
        }
    }

    fun googleAuth(account: GoogleSignInAccount) {
        showProgress(true)
        launch {
            interactor.authGoogle(account).mapBoth(::onSuccess, ::onError)
        }
    }

    fun facebookAuth(token: String) {
        showProgress(true)
        launch {
            interactor.authFacebook(token).mapBoth(::onSuccess, ::onError)
        }
    }

    private fun showProgress(show: Boolean) {
        progress.postValue(show)
    }

    private fun onSuccess(authInfo: Pair<String, User>) {
        showProgress(false)
        logger.logAuth(authInfo)
        userData.postValue(authInfo)
    }

    private fun onError(error: AuthError) {
        showProgress(false)
        authError.postValue(error.toEvent())
    }
}