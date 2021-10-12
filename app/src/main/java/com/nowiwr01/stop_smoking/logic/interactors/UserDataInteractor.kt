package com.nowiwr01.stop_smoking.logic.interactors

import com.nowiwr01.stop_smoking.domain.UserDataSignIn
import com.nowiwr01.stop_smoking.domain.UserDataSignUp
import com.nowiwr01.stop_smoking.logic.repositories.UserDataRepository

class UserDataInteractor(
    private val userDataRepository: UserDataRepository
) {

    fun isSignUpDataValid(userData: UserDataSignUp) = userDataRepository.isSignUpDataValid(userData)

    fun isSignInDataValid(userData: UserDataSignIn) = userDataRepository.isSignInDataValid(userData)
}