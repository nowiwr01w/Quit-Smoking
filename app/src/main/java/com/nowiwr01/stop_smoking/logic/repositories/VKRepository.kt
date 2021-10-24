package com.nowiwr01.stop_smoking.logic.repositories

import com.nowiwr01.stop_smoking.data.InfoVK
import com.vk.api.sdk.auth.VKAccessToken

interface VKRepository {
    suspend fun getInfo(token: VKAccessToken): InfoVK
}