package com.nowiwr01.domain.model.error.auth

import com.nowiwr01.domain.model.error.auth.UserHighlightType.*
import com.nowiwr01.domain.model.error.auth.AuthError
import com.nowiwr01.domain.model.error.auth.UserHighlightType
import com.nowiwr01.domain.utils.extensions.getFieldNames

sealed class SignUpError: AuthError {

    abstract override val list: List<UserHighlightType>
    abstract override val message: String

    class SignUpServerError: SignUpError() {
        override val list = listOf<UserHighlightType>()
        override val message = "Произошла ошибка, попробуйте снова"
    }

    class SignUpInvalidEmailError: SignUpError() {
        override val list = listOf(EMAIL_FIELD_ERROR)
        override val message = "Неверный формат электронной почты"
    }

    class SignUpWeakPasswordError: SignUpError() {
        override val list = listOf(PASSWORD_FIELD_ERROR,PASSWORD_AGAIN_FIELD_ERROR)
        override val message = "Пароль должен содежрать хотя бы одну заглавную букву"
    }

    class SignUpShortPasswordError: SignUpError() {
        override val list = listOf(PASSWORD_FIELD_ERROR, PASSWORD_AGAIN_FIELD_ERROR)
        override val message = "Пароль должен содежрать не менее 8 символов"
    }

    class SignUpNotEqualPasswordError: SignUpError() {
        override val list = listOf(PASSWORD_FIELD_ERROR, PASSWORD_AGAIN_FIELD_ERROR)
        override val message = "Пароли должны совпадать"
    }

    data class SignUpEmptyFieldError(
        val emptyFields: List<UserHighlightType>
    ): SignUpError() {
        override val list = emptyFields
        override val message = "У вас ${emptyFields.size} незаполненных поля. Заполните поля ${emptyFields.getFieldNames()}"
    }
}