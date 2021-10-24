package com.nowiwr01.stop_smoking.logic.interactors

import com.google.firebase.auth.FirebaseUser
import com.nowiwr01.stop_smoking.logic.errors.SignInTextError
import com.nowiwr01.stop_smoking.logic.errors.SignUpTextError
import com.nowiwr01.stop_smoking.logic.repositories.FirebaseRepository
import com.nowiwr01.stop_smoking.ui.base.ResultRemote
import com.nowiwr01.stop_smoking.ui.base.Result

class FirebaseInteractor(
    private val firebaseRepository: FirebaseRepository
) {

    suspend fun login(email: String, password: String): Result<FirebaseUser, SignInTextError> {
        val user = firebaseRepository.loginUser(email, password)
        return if (user.status == ResultRemote.Status.SUCCESS && user.data != null) {
            Result.Success(user.data)
        } else {
            Result.Fail(SignInTextError.createServerError())
        }
    }

    suspend fun signUp(email: String, password: String): Result<FirebaseUser, SignUpTextError> {
        val user = firebaseRepository.createUser(email, password)
        return if (user.status == ResultRemote.Status.SUCCESS && user.data != null) {
            Result.Success(user.data)
        } else {
            Result.Fail(SignUpTextError.createServerError())
        }
    }
}