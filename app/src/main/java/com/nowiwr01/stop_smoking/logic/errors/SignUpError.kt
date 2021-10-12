package com.nowiwr01.stop_smoking.logic.errors

sealed class SignUpError {
    object CreateUserError: SignUpError()
}