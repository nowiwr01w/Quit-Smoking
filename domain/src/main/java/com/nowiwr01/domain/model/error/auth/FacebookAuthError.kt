package com.nowiwr01.domain.model.error.auth

data class FacebookAuthError(
    override val list: List<UserHighlightType> = listOf(),
    override val message: String = "Не удалось войти через Facebook. Попробуйте ещё раз или свяжитесь с разработчиками"
): AuthError