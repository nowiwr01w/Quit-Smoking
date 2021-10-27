package com.nowiwr01.stop_smoking.logic.interactors

import com.nowiwr01.stop_smoking.domain.user.User
import com.nowiwr01.stop_smoking.logic.errors.SignInError
import com.nowiwr01.stop_smoking.logic.errors.SignInError.SignInServerError
import com.nowiwr01.stop_smoking.logic.errors.SignUpError
import com.nowiwr01.stop_smoking.logic.errors.SignUpError.SignUpServerError
import com.nowiwr01.stop_smoking.logic.repositories.FirebaseRepository
import com.nowiwr01.stop_smoking.ui.base.Result
import com.nowiwr01.stop_smoking.ui.base.ResultRemote
import com.nowiwr01.stop_smoking.utils.extensions.mapUser

class FirebaseInteractor(
    private val firebaseRepository: FirebaseRepository
) {

    suspend fun login(email: String, password: String): Result<User, SignInError> {
        val user = firebaseRepository.loginUser(email, password)
        return if (user.status == ResultRemote.Status.SUCCESS && user.data != null) {
            Result.Success(user.data.mapUser())
        } else {
            Result.Fail(SignInServerError())
        }
    }

    suspend fun signUp(email: String, password: String): Result<User, SignUpError> {
        val user = firebaseRepository.createUser(email, password)
        return if (user.status == ResultRemote.Status.SUCCESS && user.data != null) {
            Result.Success(user.data.mapUser())
        } else {
            Result.Fail(SignUpServerError())
        }
    }
}