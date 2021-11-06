package com.nowiwr01.domain.model.error.user

data class SetSmokeInfoError(
    override val message: String = "Не удалось обновить информацию"
): UserError