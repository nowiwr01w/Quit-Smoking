package com.nowiwr01.stop_smoking.logic.interactors

import com.nowiwr01.stop_smoking.logic.errors.VKError
import com.nowiwr01.stop_smoking.logic.repositories.FirebaseRepository
import com.nowiwr01.stop_smoking.logic.repositories.VKRepository
import com.nowiwr01.stop_smoking.ui.base.Result
import com.nowiwr01.stop_smoking.utils.extensions.mapUser
import com.vk.api.sdk.auth.VKAccessToken

class VkInteractor(
    private val vkRepository: VKRepository,
    private val firebaseRepository: FirebaseRepository
) {

    suspend fun getInfo(token: VKAccessToken): Result<Boolean, VKError> {
        return try {
            val user = vkRepository.getInfo(token).mapUser(token)
           Result.Success(firebaseRepository.addUser(user))
        } catch (throwable: Throwable) {
            Result.Fail(VKError())
        }
    }
}