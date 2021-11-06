package com.nowiwr01.stop_smoking.presentation.main.home

import androidx.lifecycle.MutableLiveData
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import com.nowiwr01.domain.model.base.mapBoth
import com.nowiwr01.domain.model.error.user.UserError
import com.nowiwr01.domain.model.user.User
import com.nowiwr01.domain.usecase.UserUseCase
import com.nowiwr01.stop_smoking.presentation.base.BaseViewModel
import kotlinx.coroutines.launch
import timber.log.Timber

class UserViewModel(
    private val userUseCase: UserUseCase,

    val userData: MutableLiveData<User> = MutableLiveData()
): BaseViewModel() {

    fun loadUserData() {
        launch {
            userUseCase.loadUser().mapBoth(::onSuccess, ::onError)
        }
    }

    fun setUserListener() {
        launch {
            userUseCase.addUserListener(::onSuccess).mapBoth(::onListenerSetSuccess, ::onError)
        }
    }

    private fun onSuccess(user: User) {
        userData.postValue(user)
    }

    private fun onListenerSetSuccess(event: Pair<DatabaseReference, ValueEventListener>) {
        listeners.add(event)
    }

    private fun onError(error: UserError) {
        Timber.e("UserViewModel, error = ${error.message}")
    }
}