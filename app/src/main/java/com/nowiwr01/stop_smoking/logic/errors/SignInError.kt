package com.nowiwr01.stop_smoking.logic.errors

sealed class SignInError {
    object LoginUserError: SignInError()

    companion object {
        fun mapError() = SignInTextError(
            listOf(), "Произошла ошибка, попробуйте снова."
        )
    }
}