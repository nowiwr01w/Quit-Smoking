package com.nowiwr01.domain.model.error.user

interface UserError {
    val message: String
}

data class LoadUserError(
    override val message: String = "Не удалось загрузить информацию о пользователе"
): UserError

data class SetSmokeInfoError(
    override val message: String = "Не удалось обновить информацию"
): UserError

data class ValueEventListenerError(
    override val message: String = "Не удалось установить ValueEventListener"
): UserError
