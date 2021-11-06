package com.nowiwr01.domain.model.error.auth

enum class UserHighlightType(val type: Int) {
    EMAIL_FIELD_ERROR(1),
    USERNAME_FIELD_ERROR(2),
    PASSWORD_FIELD_ERROR(3),
    PASSWORD_AGAIN_FIELD_ERROR(4)
}