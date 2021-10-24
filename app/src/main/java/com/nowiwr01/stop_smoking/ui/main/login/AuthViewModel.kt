package com.nowiwr01.stop_smoking.ui.main.login

import androidx.lifecycle.MutableLiveData
import com.google.firebase.auth.FirebaseUser
import com.nowiwr01.stop_smoking.domain.user.User
import com.nowiwr01.stop_smoking.domain.user.UserData
import com.nowiwr01.stop_smoking.domain.user.UserDataSignIn
import com.nowiwr01.stop_smoking.domain.user.UserDataSignUp
import com.nowiwr01.stop_smoking.logic.errors.AuthError
import com.nowiwr01.stop_smoking.logic.interactors.FirebaseInteractor
import com.nowiwr01.stop_smoking.logic.interactors.UserDataInteractor
import com.nowiwr01.stop_smoking.logic.interactors.VkInteractor
import com.nowiwr01.stop_smoking.ui.base.BaseViewModel
import com.nowiwr01.stop_smoking.ui.base.mapBoth
import com.nowiwr01.stop_smoking.ui.base.mapSuccess
import com.nowiwr01.stop_smoking.utils.extensions.mapUser
import com.vk.api.sdk.auth.VKAccessToken
import kotlinx.coroutines.launch
import timber.log.Timber

class AuthViewModel(
    private val vkInteractor: VkInteractor,
    private val firebaseInteractor: FirebaseInteractor,
    private val userDataInteractor: UserDataInteractor,

    val user: MutableLiveData<User> = MutableLiveData(),
    val progress: MutableLiveData<Boolean> = MutableLiveData(),
    val authError: MutableLiveData<AuthError> = MutableLiveData()
): BaseViewModel() {

    fun checkAndAuth(userData: UserData) {
        progress.postValue(true)
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

    private fun signIn(userData: UserData) {
        launch {
            firebaseInteractor.login(userData.email, userData.password).mapBoth(::onSuccess, ::onError)
        }
    }

    private fun signUp(userData: UserData) {
        launch {
            firebaseInteractor.signUp(userData.email, userData.password).mapBoth(::onSuccess, ::onError)
        }
    }

    fun authVk(token: VKAccessToken) {
        launch {
            vkInteractor.getInfo(token).mapSuccess {
                Timber.tag("Auth").d("info = $it")
            }
        }
    }

    private fun onSuccess(firebaseUser: FirebaseUser) {
        progress.postValue(false)
        user.postValue(firebaseUser.mapUser())
    }

    private fun onError(error: AuthError) {
        progress.postValue(false)
        authError.postValue(error)
    }
}