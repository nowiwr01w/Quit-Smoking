package com.nowiwr01.domain.repository

import com.nowiwr01.domain.model.user.User
import com.nowiwr01.domain.model.user.smoke_info.SmokeInfo

interface UserRepository {
    suspend fun getUser(): User
    suspend fun setUser(user: User): User

    suspend fun updateSmokeInfo(info: SmokeInfo): User
}