package com.nowiwr01.stop_smoking.ui.main.login.sign_up

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.google.firebase.auth.FirebaseUser
import com.nowiwr01.stop_smoking.domain.User
import com.nowiwr01.stop_smoking.domain.UserDataSignUp
import com.nowiwr01.stop_smoking.logic.errors.SignUpError
import com.nowiwr01.stop_smoking.logic.errors.SignUpTextError
import com.nowiwr01.stop_smoking.logic.interactors.FirebaseInteractor
import com.nowiwr01.stop_smoking.logic.interactors.UserDataInteractor
import com.nowiwr01.stop_smoking.ui.base.BaseViewModel
import com.nowiwr01.stop_smoking.ui.base.mapBoth
import com.nowiwr01.stop_smoking.utils.extensions.mapUser
import kotlinx.coroutines.launch

class SignUpViewModel(
    private val firebaseInteractor: FirebaseInteractor,
    private val userDataInteractor: UserDataInteractor,
    val userData: MutableLiveData<User> = MutableLiveData()
): BaseViewModel() {

    fun signUp(email: String, password: String) {
        launch {
            firebaseInteractor.signUp(email, password).mapBoth(::onSuccess, ::onError)
        }
    }

    private fun onSuccess(user: FirebaseUser) {
        userData.postValue(user.mapUser())
        Log.d("Auth", "SignUpViewModel, onSuccess, заебись")
    }

    private fun onError(error: SignUpError) {
        Log.d("Auth", "SignUpViewModel, onError, хуёва")
    }

    fun isUserInputValid(userData: UserDataSignUp): SignUpTextError? {
        return userDataInteractor.isSignUpDataValid(userData)
    }
}