package com.nowiwr01.stop_smoking.ui.main.auth

import androidx.lifecycle.MutableLiveData
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.nowiwr01.stop_smoking.domain.user.User
import com.nowiwr01.stop_smoking.domain.user.UserData
import com.nowiwr01.stop_smoking.domain.user.UserDataSignIn
import com.nowiwr01.stop_smoking.domain.user.UserDataSignUp
import com.nowiwr01.stop_smoking.utils.Event
import com.nowiwr01.stop_smoking.logic.errors.AuthError
import com.nowiwr01.stop_smoking.logic.interactors.AuthInteractor
import com.nowiwr01.stop_smoking.logic.interactors.UserDataInteractor
import com.nowiwr01.stop_smoking.utils.toEvent
import com.nowiwr01.stop_smoking.ui.base.BaseViewModel
import com.nowiwr01.stop_smoking.ui.base.mapBoth
import com.nowiwr01.stop_smoking.ui.main.auth.AuthFragment.Companion.SIGN_UP
import com.vk.api.sdk.auth.VKAccessToken
import kotlinx.coroutines.launch

class AuthViewModel(
    private val interactor: AuthInteractor,
    private val userDataInteractor: UserDataInteractor,

    val user: MutableLiveData<Event<User>> = MutableLiveData(),
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

    private fun showProgress(show: Boolean) {
        progress.postValue(show)
    }

    private fun onSuccess(firebaseUser: User) {
        showProgress(false)
        user.postValue(firebaseUser.toEvent())
    }

    private fun onError(error: AuthError) {
        showProgress(false)
        authError.postValue(error.toEvent())
    }
}