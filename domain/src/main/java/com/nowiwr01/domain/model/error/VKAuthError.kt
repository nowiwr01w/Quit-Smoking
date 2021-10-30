package com.nowiwr01.domain.model.error

data class VKAuthError(
    override val list: List<UserHighlightType> = listOf(),
    override val message: String = "Не удалось войти через VK. Попробуйте ещё раз или свяжитесь с разработчиками"
) : AuthError