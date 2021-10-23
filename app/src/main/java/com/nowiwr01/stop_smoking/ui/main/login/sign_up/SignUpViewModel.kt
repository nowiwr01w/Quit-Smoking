package com.nowiwr01.stop_smoking.ui.main.login.sign_up

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.google.firebase.auth.FirebaseUser
import com.nowiwr01.stop_smoking.domain.UserDataSignUp
import com.nowiwr01.stop_smoking.logic.errors.SignUpError
import com.nowiwr01.stop_smoking.logic.errors.SignUpTextError
import com.nowiwr01.stop_smoking.logic.interactors.FirebaseInteractor
import com.nowiwr01.stop_smoking.logic.interactors.UserDataInteractor
import com.nowiwr01.stop_smoking.ui.base.BaseViewModel
import com.nowiwr01.stop_smoking.ui.base.mapBoth
import kotlinx.coroutines.launch
import timber.log.Timber

class SignUpViewModel(
    private val firebaseInteractor: FirebaseInteractor,
    private val userDataInteractor: UserDataInteractor,

    val signUpError: MutableLiveData<SignUpTextError> = MutableLiveData()
): BaseViewModel() {

    fun signUp(userData: UserDataSignUp) {
        if (!isUserInputValid(userData)) {
            return
        }
        launch {
            firebaseInteractor.signUp(userData.email, userData.password).mapBoth(::onSuccess, ::onError)
        }
    }

    private fun onSuccess(user: FirebaseUser) {
        Timber.tag("Auth").d( "SignUpViewModel, onSuccess, заебись")
    }

    private fun onError(error: SignUpError) {
        Timber.tag("Auth").d( "SignUpViewModel, onError, хуёва")
    }

    private fun isUserInputValid(userData: UserDataSignUp): Boolean {
        val error = userDataInteractor.isSignUpDataValid(userData)
        return if (error != null) {
            signUpError.postValue(error)
            false
        } else {
            true
        }
    }
}