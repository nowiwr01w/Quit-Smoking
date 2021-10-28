package com.nowiwr01.stop_smoking.logic.repositories.impl

import com.nowiwr01.stop_smoking.domain.user.UserDataSignIn
import com.nowiwr01.stop_smoking.domain.user.UserDataSignUp
import com.nowiwr01.stop_smoking.logic.DispatchersProvider
import com.nowiwr01.stop_smoking.logic.errors.SignInError
import com.nowiwr01.stop_smoking.logic.errors.SignInError.SignInEmptyFieldError
import com.nowiwr01.stop_smoking.logic.errors.SignInError.SignInInvalidEmailError
import com.nowiwr01.stop_smoking.logic.errors.SignUpError
import com.nowiwr01.stop_smoking.logic.errors.SignUpError.*
import com.nowiwr01.stop_smoking.logic.repositories.UserDataRepository
import com.nowiwr01.stop_smoking.ui.main.auth.data.UserHighlightType
import com.nowiwr01.stop_smoking.ui.main.auth.data.UserHighlightType.*
import com.nowiwr01.stop_smoking.utils.extensions.hasUpperChar
import com.nowiwr01.stop_smoking.utils.extensions.isLongPassword
import com.nowiwr01.stop_smoking.utils.extensions.isValidEmail
import kotlinx.coroutines.withContext

class UserDataRepositoryImpl(
    private val dispatchers: DispatchersProvider
): UserDataRepository {

    override suspend fun isSignInDataValid(userData: UserDataSignIn): SignInError? = withContext(dispatchers.io) {
        val emptyFieldsList = mutableListOf<UserHighlightType>()

        if (userData.email.isEmpty()) emptyFieldsList.add(EMAIL_FIELD_ERROR)
        if (userData.password.isEmpty()) emptyFieldsList.add(PASSWORD_FIELD_ERROR)

        if (emptyFieldsList.isNotEmpty()) {
            return@withContext SignInEmptyFieldError(emptyFieldsList)
        }
        if (!userData.email.isValidEmail()) {
            return@withContext SignInInvalidEmailError()
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
        if (!userData.email.isValidEmail()) {
            return@withContext SignUpInvalidEmailError()
        }
        if (userData.password != userData.passwordRepeated) {
            return@withContext SignUpNotEqualPasswordError()
        }
        if (!userData.password.isLongPassword()) {
            return@withContext SignUpShortPasswordError()
        }
        if (!userData.password.hasUpperChar()) {
            return@withContext SignUpWeakPasswordError()
        }

        null
    }
}