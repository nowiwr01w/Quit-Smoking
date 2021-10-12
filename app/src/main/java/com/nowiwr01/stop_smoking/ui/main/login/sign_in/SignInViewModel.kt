package com.nowiwr01.stop_smoking.ui.main.login.sign_in

import android.util.Log
import com.google.firebase.auth.FirebaseUser
import com.nowiwr01.stop_smoking.domain.UserDataSignIn
import com.nowiwr01.stop_smoking.logic.errors.SignInError
import com.nowiwr01.stop_smoking.logic.errors.SignInTextError
import com.nowiwr01.stop_smoking.logic.interactors.FirebaseInteractor
import com.nowiwr01.stop_smoking.logic.interactors.UserDataInteractor
import com.nowiwr01.stop_smoking.ui.base.BaseViewModel
import com.nowiwr01.stop_smoking.ui.base.mapBoth
import kotlinx.coroutines.launch

class SignInViewModel(
    private val firebaseInteractor: FirebaseInteractor,
    private val userDataInteractor: UserDataInteractor
): BaseViewModel() {

    fun login(email: String, password: String) {
        launch {
            firebaseInteractor.login(email, password).mapBoth(::proceedUser, ::onError)
        }
    }

    private fun proceedUser(user: FirebaseUser) {
        Log.d("Auth", "SignInViewModel, proceedUser, заебись")
    }

    private fun onError(error: SignInError) {
        Log.d("Auth", "SignInViewModel, onError, хуёва")
    }

    fun isUserInputValid(userData: UserDataSignIn): SignInTextError? {
        return userDataInteractor.isSignInDataValid(userData)
    }
}