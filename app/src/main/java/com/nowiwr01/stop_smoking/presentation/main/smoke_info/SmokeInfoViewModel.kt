package com.nowiwr01.stop_smoking.presentation.main.smoke_info

import androidx.lifecycle.MutableLiveData
import com.nowiwr01.domain.model.base.mapBoth
import com.nowiwr01.domain.model.error.user.UserError
import com.nowiwr01.domain.model.user.User
import com.nowiwr01.domain.model.user.smoke_info.SmokeInfo
import com.nowiwr01.domain.usecase.UserUseCase
import com.nowiwr01.stop_smoking.presentation.base.BaseViewModel
import com.nowiwr01.stop_smoking.utils.Event
import com.nowiwr01.stop_smoking.utils.toEvent
import kotlinx.coroutines.launch
import org.joda.money.CurrencyUnit
import org.joda.time.DateTime

class SmokeInfoViewModel(
    private val userUseCase: UserUseCase,

    val userData: MutableLiveData<User> = MutableLiveData(),
    val currency: MutableLiveData<CurrencyUnit> = MutableLiveData(),
    val breakDate: MutableLiveData<DateTime> = MutableLiveData(),
    val progress: MutableLiveData<Boolean> = MutableLiveData(),
    val errorMessage: MutableLiveData<Event<String>> = MutableLiveData()
): BaseViewModel() {

    fun setCurrency(currencyUnit: CurrencyUnit) {
        currency.postValue(currencyUnit)
    }

    fun setBreakDate(dateTime: DateTime) {
        breakDate.postValue(dateTime)
    }

    fun setProgress(isVisible: Boolean) {
        progress.postValue(isVisible)
    }

    fun setSmokeInfo(smokeInfo: SmokeInfo) {
        launch {
            userUseCase.setSmokeInfo(smokeInfo).mapBoth(::onSuccess, ::onError)
        }
    }

    private fun onSuccess(user: User) {
        userData.postValue(user)
    }

    private fun onError(error: UserError) {
        errorMessage.postValue(error.message.toEvent())
    }
}