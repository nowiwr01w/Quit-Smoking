package com.nowiwr01.domain.repository

import com.nowiwr01.domain.model.vk.InfoVK
import com.vk.api.sdk.auth.VKAccessToken

interface VKRepository {
    suspend fun getInfo(token: VKAccessToken): InfoVK
}