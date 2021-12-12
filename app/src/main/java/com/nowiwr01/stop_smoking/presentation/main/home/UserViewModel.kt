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

    var isUserDataInit = false

    suspend fun getStars(user: User) = userUseCase.getStars(user)

    suspend fun getSavedTime(user: User) = userUseCase.getSavedTime(user)

    suspend fun getSavedMoney(user: User) = userUseCase.getSavedMoney(user)

    suspend fun getTimerNotSmoked(user: User) = userUseCase.getTimerNotSmoked(user)

    suspend fun getNotSmokedPacks(user: User) = userUseCase.getNotSmokedPacks(user)

    fun loadUserData() {
        launch {
            userUseCase.loadUser().mapBoth(::onSuccess, ::onError)
        }
    }

    private fun onSuccess(user: User) {
        userData.postValue(user)
    }

    fun setUserListener() {
        launch {
            userUseCase.addUserListener(::onSuccess).mapBoth(::onListenerSetSuccess, ::onError)
        }
    }

    private fun onListenerSetSuccess(event: Pair<DatabaseReference, ValueEventListener>) {
        listeners.add(event)
    }

    private fun onError(error: UserError) {
        Timber.e("UserViewModel, error = ${error.message}")
    }
}