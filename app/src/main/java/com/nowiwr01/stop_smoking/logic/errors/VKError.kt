package com.nowiwr01.stop_smoking.logic.errors

import com.nowiwr01.stop_smoking.ui.main.auth.data.UserHighlightType

data class VKError(
    override val list: List<UserHighlightType> = listOf(),
    override val message: String = "Не удалось войти через VK. Попробуйте ещё раз или свяжитесь с разработчиками"
) : AuthError