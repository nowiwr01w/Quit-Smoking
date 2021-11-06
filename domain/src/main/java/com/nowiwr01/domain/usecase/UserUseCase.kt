package com.nowiwr01.domain.usecase

import com.nowiwr01.domain.model.base.Result
import com.nowiwr01.domain.model.error.user.SetSmokeInfoError
import com.nowiwr01.domain.model.error.user.UserError
import com.nowiwr01.domain.model.user.User
import com.nowiwr01.domain.model.user.smoke_info.SmokeInfo
import com.nowiwr01.domain.repository.UserRepository

class UserUseCase(
    private val userRepository: UserRepository
) {

    suspend fun setSmokeInfo(smokeInfo: SmokeInfo): Result<User, UserError> {
        return try {
            Result.Success(userRepository.updateSmokeInfo(smokeInfo))
        } catch (throwable: Throwable) {
            Result.Fail(SetSmokeInfoError())
        }
    }
}