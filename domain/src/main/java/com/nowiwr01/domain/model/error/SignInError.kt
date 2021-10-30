package com.nowiwr01.domain.model.error

import com.nowiwr01.domain.model.error.UserHighlightType.EMAIL_FIELD_ERROR
import com.nowiwr01.domain.utils.extensions.getFieldNames

sealed class SignInError: AuthError {

    abstract override val list: List<UserHighlightType>
    abstract override val message: String

    class SignInServerError: SignInError() {
        override val list = listOf<UserHighlightType>()
        override val message = "Произошла ошибка, попробуйте снова"
    }

    class SignInInvalidEmailError: SignInError() {
        override val list = listOf(EMAIL_FIELD_ERROR)
        override val message = "Неверный формат электронной почты"
    }

    data class SignInEmptyFieldError(
        val emptyFields: List<UserHighlightType>
    ): SignInError() {
        override val list = emptyFields
        override val message = "У вас ${emptyFields.size} незаполненных поля. Заполните поля ${emptyFields.getFieldNames()}"
    }
}