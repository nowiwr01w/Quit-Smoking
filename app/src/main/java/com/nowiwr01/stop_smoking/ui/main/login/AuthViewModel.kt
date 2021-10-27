package com.nowiwr01.stop_smoking.ui.main.login

import androidx.lifecycle.MutableLiveData
import com.nowiwr01.stop_smoking.domain.user.User
import com.nowiwr01.stop_smoking.domain.user.UserData
import com.nowiwr01.stop_smoking.domain.user.UserDataSignIn
import com.nowiwr01.stop_smoking.domain.user.UserDataSignUp
import com.nowiwr01.stop_smoking.utils.Event
import com.nowiwr01.stop_smoking.logic.errors.AuthError
import com.nowiwr01.stop_smoking.logic.interactors.FirebaseInteractor
import com.nowiwr01.stop_smoking.logic.interactors.UserDataInteractor
import com.nowiwr01.stop_smoking.logic.interactors.VkInteractor
import com.nowiwr01.stop_smoking.utils.toEvent
import com.nowiwr01.stop_smoking.ui.base.BaseViewModel
import com.nowiwr01.stop_smoking.ui.base.mapBoth
import com.vk.api.sdk.auth.VKAccessToken
import kotlinx.coroutines.launch

class AuthViewModel(
    private val vkInteractor: VkInteractor,
    private val firebaseInteractor: FirebaseInteractor,
    private val userDataInteractor: UserDataInteractor,

    val user: MutableLiveData<Event<User>> = MutableLiveData(),
    val progress: MutableLiveData<Boolean> = MutableLiveData(),
    val authError: MutableLiveData<Event<AuthError>> = MutableLiveData()
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
        progress.postValue(true)
        launch {
            vkInteractor.authVk(token).mapBoth(::onSuccess, ::onError)
        }
    }

    private fun onSuccess(firebaseUser: User) {
        progress.postValue(false)
        user.postValue(firebaseUser.toEvent())
    }

    private fun onError(error: AuthError) {
        progress.postValue(false)
        authError.postValue(error.toEvent())
    }
}