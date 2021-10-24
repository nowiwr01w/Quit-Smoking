package com.nowiwr01.stop_smoking.logic.errors

import com.nowiwr01.stop_smoking.ui.main.login.data.UserHighlightType
import com.nowiwr01.stop_smoking.ui.main.login.data.UserHighlightType.*

data class SignUpTextError(
    override val list: List<UserHighlightType>,
    override val message: String,
): AuthError {
    companion object {
        fun createServerError() = SignUpTextError(
            listOf(),
            "Произошла ошибка, попробуйте снова"
        )

        fun createWeakPasswordMessage() = SignUpTextError(
            listOf(PASSWORD_FIELD_ERROR, PASSWORD_AGAIN_FIELD_ERROR),
            "Пароль должен содежрать хотя бы одну заглавную букву",
        )
        fun createShortPasswordMessage() = SignUpTextError(
            listOf(PASSWORD_FIELD_ERROR, PASSWORD_AGAIN_FIELD_ERROR),
            "Пароль должен содежрать не менее 8 символов",
        )
        fun createNotEqualPasswordMessage() = SignUpTextError(
            listOf(PASSWORD_FIELD_ERROR, PASSWORD_AGAIN_FIELD_ERROR),
            "Пароли должны совпадать",
        )
        fun createEmptyFieldMessage(emptyFields: List<UserHighlightType>) = SignUpTextError(
            emptyFields,
            "У вас ${emptyFields.size} незаполненных поля. Заполните поля ${getFieldNames(emptyFields)}",
        )
        fun createInvalidEmailMessage() = SignUpTextError(
            listOf(EMAIL_FIELD_ERROR),
            "Неверный формат электронной почты"
        )

        private fun getFieldNames(list: List<UserHighlightType>) = mutableListOf<String>().apply {
            list.forEach {
                when (it) {
                    EMAIL_FIELD_ERROR -> add("email")
                    USERNAME_FIELD_ERROR -> add("username")
                    PASSWORD_FIELD_ERROR -> add("password")
                    PASSWORD_AGAIN_FIELD_ERROR -> add("password_repeat")
                }
            }
        }
    }
}