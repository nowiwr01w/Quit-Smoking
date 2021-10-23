package com.nowiwr01.stop_smoking.ui.main.login.sign_in

import androidx.lifecycle.MutableLiveData
import com.google.firebase.auth.FirebaseUser
import com.nowiwr01.stop_smoking.domain.User
import com.nowiwr01.stop_smoking.domain.UserDataSignIn
import com.nowiwr01.stop_smoking.logic.errors.SignInTextError
import com.nowiwr01.stop_smoking.logic.interactors.FirebaseInteractor
import com.nowiwr01.stop_smoking.logic.interactors.UserDataInteractor
import com.nowiwr01.stop_smoking.ui.base.BaseViewModel
import com.nowiwr01.stop_smoking.ui.base.mapBoth
import com.nowiwr01.stop_smoking.utils.extensions.mapUser
import kotlinx.coroutines.launch
import timber.log.Timber

class SignInViewModel(
    private val firebaseInteractor: FirebaseInteractor,
    private val userDataInteractor: UserDataInteractor,

    val user: MutableLiveData<User> = MutableLiveData(),
    val progress: MutableLiveData<Boolean> = MutableLiveData(),
    val signInError: MutableLiveData<SignInTextError> = MutableLiveData()
): BaseViewModel() {

    fun login(userData: UserDataSignIn) {
        progress.postValue(true)
        if (!isUserInputValid(userData)) {
            return
        }
        launch {
            firebaseInteractor.login(userData.email, userData.password).mapBoth(::proceedUser, ::onError)
        }
    }

    private fun proceedUser(firebaseUser: FirebaseUser) {
        Timber.tag("Auth").d("proceedUser()")
        progress.postValue(false)
        user.postValue(firebaseUser.mapUser())
    }

    private fun onError(error: SignInTextError) {
        Timber.tag("Auth").d("onError = ${error.message}")
        progress.postValue(false)
        signInError.postValue(error)
    }

    private fun isUserInputValid(userData: UserDataSignIn): Boolean {
        val error = userDataInteractor.isSignInDataValid(userData)
        return if (error != null) {
            onError(error)
            false
        } else {
            true
        }
    }
}