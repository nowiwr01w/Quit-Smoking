package com.nowiwr01.domain.usecase

import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import com.nowiwr01.domain.model.base.Result
import com.nowiwr01.domain.model.error.user.LoadUserError
import com.nowiwr01.domain.model.error.user.SetSmokeInfoError
import com.nowiwr01.domain.model.error.user.UserError
import com.nowiwr01.domain.model.error.user.ValueEventListenerError
import com.nowiwr01.domain.model.user.User
import com.nowiwr01.domain.model.user.smoke_info.SmokeInfo
import com.nowiwr01.domain.repository.UserRepository

class UserUseCase(
    private val userRepository: UserRepository
) {

    suspend fun getStars(user: User) = userRepository.getStars(user)

    suspend fun getSavedTime(user: User) = userRepository.getSavedTime(user)

    suspend fun getSavedMoney(user: User) = userRepository.getSavedMoney(user)

    suspend fun getTimerNotSmoked(user: User) = userRepository.getTimerNotSmoked(user)

    suspend fun getNotSmokedPacks(user: User) = userRepository.getNotSmokedPacks(user)

    suspend fun loadUser(): Result<User, UserError> {
        return try {
            Result.Success(userRepository.loadUser())
        } catch (throwable: Throwable) {
            Result.Fail(LoadUserError())
        }
    }

    suspend fun setSmokeInfo(smokeInfo: SmokeInfo): Result<User, UserError> {
        return try {
            Result.Success(userRepository.updateSmokeInfo(smokeInfo))
        } catch (throwable: Throwable) {
            Result.Fail(SetSmokeInfoError())
        }
    }

    suspend fun addUserListener(
        callback: (User) -> Unit
    ): Result<Pair<DatabaseReference, ValueEventListener>, UserError> {
        return try {
            Result.Success(userRepository.addValueEventListener(callback))
        } catch (throwable: Throwable) {
            Result.Fail(ValueEventListenerError())
        }
    }
}