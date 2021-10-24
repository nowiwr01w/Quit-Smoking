package com.nowiwr01.stop_smoking.logic.repositories.impl

import com.nowiwr01.stop_smoking.logic.DispatchersProvider
import com.nowiwr01.stop_smoking.logic.VKInfoRequest
import com.nowiwr01.stop_smoking.logic.repositories.VKRepository
import com.vk.api.sdk.VK
import com.vk.api.sdk.auth.VKAccessToken
import kotlinx.coroutines.withContext

class VKRepositoryImpl(
    private val dispatchers: DispatchersProvider
): VKRepository {
    override suspend fun getInfo(token: VKAccessToken) = withContext(dispatchers.io) {
        VK.executeSync(VKInfoRequest(token.userId))
    }
}