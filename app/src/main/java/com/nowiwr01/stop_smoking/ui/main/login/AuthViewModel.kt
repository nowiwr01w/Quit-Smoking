package com.nowiwr01.stop_smoking.ui.main.login

import androidx.lifecycle.MutableLiveData
import com.google.firebase.auth.FirebaseUser
import com.nowiwr01.stop_smoking.domain.User
import com.nowiwr01.stop_smoking.domain.UserData
import com.nowiwr01.stop_smoking.domain.UserDataSignIn
import com.nowiwr01.stop_smoking.domain.UserDataSignUp
import com.nowiwr01.stop_smoking.logic.errors.AuthError
import com.nowiwr01.stop_smoking.logic.interactors.FirebaseInteractor
import com.nowiwr01.stop_smoking.logic.interactors.UserDataInteractor
import com.nowiwr01.stop_smoking.ui.base.BaseViewModel
import com.nowiwr01.stop_smoking.ui.base.mapBoth
import com.nowiwr01.stop_smoking.utils.extensions.mapUser
import kotlinx.coroutines.launch

class AuthViewModel(
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

    private fun onSuccess(firebaseUser: FirebaseUser) {
        progress.postValue(false)
        user.postValue(firebaseUser.mapUser())
    }

    private fun onError(error: AuthError) {
        progress.postValue(false)
        authError.postValue(error)
    }
}