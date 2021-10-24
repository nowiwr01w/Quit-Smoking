package com.nowiwr01.stop_smoking.ui.main.login

import com.nowiwr01.stop_smoking.domain.UserData
import com.nowiwr01.stop_smoking.ui.main.login.data.UserHighlightType

abstract class BaseSignViewsController {

    abstract fun getUserData(): UserData

    abstract fun manageProgressBar(isVisible: Boolean)

    abstract fun setErrorByType(numbers: List<UserHighlightType>)

    abstract fun setTextChangedCallback()
}