package com.nowiwr01.stop_smoking.logic.errors

import com.nowiwr01.stop_smoking.ui.main.login.data.UserHighlightType
import com.nowiwr01.stop_smoking.ui.main.login.data.UserHighlightType.*

data class SignInTextError(
    val list: List<UserHighlightType>,
    val message: String
) {
    companion object {
        fun createServerError() = SignInTextError(
            listOf(),
            "Произошла ошибка, попробуйте снова"
        )
        fun createInvalidEmailMessage() = SignInTextError(
            listOf(EMAIL_FIELD_ERROR),
            "Неверный формат электронной почты"
        )
        fun createEmptyFieldMessage(emptyFields: List<UserHighlightType>) = SignInTextError(
            emptyFields,
            "У вас ${emptyFields.size} незаполненных поля. Заполните поля ${getFieldNames(emptyFields)}"
        )

        private fun getFieldNames(emptyFields: List<UserHighlightType>) = mutableListOf<String>().apply {
            emptyFields.forEach {
                when (it) {
                    EMAIL_FIELD_ERROR -> add("email")
                    PASSWORD_FIELD_ERROR -> add("password")
                    else -> throw IllegalStateException("No another sign in errors.")
                }
            }
        }
    }
}