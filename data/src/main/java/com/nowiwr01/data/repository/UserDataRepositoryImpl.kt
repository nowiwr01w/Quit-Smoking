package com.nowiwr01.data.repository

import com.nowiwr01.basecoroutines.DispatchersProvider
import com.nowiwr01.domain.model.error.auth.SignInError
import com.nowiwr01.domain.model.error.auth.SignInError.*
import com.nowiwr01.domain.model.error.auth.SignUpError
import com.nowiwr01.domain.model.error.auth.SignUpError.*
import com.nowiwr01.domain.model.error.auth.UserHighlightType
import com.nowiwr01.domain.model.error.auth.UserHighlightType.*
import com.nowiwr01.domain.model.user.UserDataSignIn
import com.nowiwr01.domain.model.user.UserDataSignUp
import com.nowiwr01.domain.repository.UserDataRepository
import com.nowiwr01.domain.validators.EmailValidator
import com.nowiwr01.domain.validators.PasswordValidator
import kotlinx.coroutines.withContext

class UserDataRepositoryImpl(
    private val dispatchers: DispatchersProvider,
    private val emailValidator: EmailValidator,
    private val passwordValidator: PasswordValidator
): UserDataRepository {

    override suspend fun isSignInDataValid(userData: UserDataSignIn): SignInError? = withContext(dispatchers.io) {
        val emptyFieldsList = mutableListOf<UserHighlightType>()

        if (userData.email.isEmpty()) emptyFieldsList.add(EMAIL_FIELD_ERROR)
        if (userData.password.isEmpty()) emptyFieldsList.add(PASSWORD_FIELD_ERROR)

        if (emptyFieldsList.isNotEmpty()) {
            return@withContext SignInEmptyFieldError(emptyFieldsList)
        }
        if (!emailValidator.validate(userData.email)) {
            return@withContext SignInInvalidEmailError()
        }
        if (!passwordValidator.validate(userData.password)) {
            return@withContext SignInIncorrectPasswordError()
        }

        null
    }

    override suspend fun isSignUpDataValid(userData: UserDataSignUp): SignUpError? = withContext(dispatchers.io) {
        val emptyFieldsList = mutableListOf<UserHighlightType>()

        if (userData.email.isEmpty()) emptyFieldsList.add(EMAIL_FIELD_ERROR)
        if (userData.userName.isEmpty()) emptyFieldsList.add(USERNAME_FIELD_ERROR)
        if (userData.password.isEmpty()) emptyFieldsList.add(PASSWORD_FIELD_ERROR)
        if (userData.passwordRepeated.isEmpty()) emptyFieldsList.add(PASSWORD_AGAIN_FIELD_ERROR)

        if (emptyFieldsList.isNotEmpty()) {
            return@withContext SignUpEmptyFieldError(emptyFieldsList)
        }
        println("userData.email = ${userData.email}, emailValidator.validate(userData.email) = ${emailValidator.validate(userData.email)}")
        if (!emailValidator.validate(userData.email)) {
            return@withContext SignUpInvalidEmailError()
        }
        if (!passwordValidator.validate(userData.password) || !passwordValidator.validate(userData.passwordRepeated)) {
            return@withContext SignUpIncorrectPasswordError()
        }
        if (userData.password != userData.passwordRepeated) {
            return@withContext SignUpNotEqualPasswordError()
        }

        null
    }
}