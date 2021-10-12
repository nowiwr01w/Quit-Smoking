package com.nowiwr01.stop_smoking.logic.errors

sealed class SignInError {
    object LoginUserError: SignInError()
}