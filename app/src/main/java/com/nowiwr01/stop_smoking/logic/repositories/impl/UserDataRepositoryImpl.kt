package com.nowiwr01.stop_smoking.logic.repositories.impl

import com.nowiwr01.stop_smoking.domain.user.UserDataSignIn
import com.nowiwr01.stop_smoking.domain.user.UserDataSignUp
import com.nowiwr01.stop_smoking.logic.DispatchersProvider
import com.nowiwr01.stop_smoking.logic.errors.SignInTextError
import com.nowiwr01.stop_smoking.logic.errors.SignUpTextError
import com.nowiwr01.stop_smoking.logic.repositories.UserDataRepository
import com.nowiwr01.stop_smoking.ui.main.login.data.UserHighlightType
import com.nowiwr01.stop_smoking.ui.main.login.data.UserHighlightType.*
import com.nowiwr01.stop_smoking.utils.extensions.hasUpperChar
import com.nowiwr01.stop_smoking.utils.extensions.isLongPassword
import com.nowiwr01.stop_smoking.utils.extensions.isValidEmail
import kotlinx.coroutines.withContext

class UserDataRepositoryImpl(
    private val dispatchers: DispatchersProvider
): UserDataRepository {

    override suspend fun isSignInDataValid(userData: UserDataSignIn): SignInTextError? = withContext(dispatchers.io) {
        val emptyFieldsList = mutableListOf<UserHighlightType>()

        if (userData.email.isEmpty()) emptyFieldsList.add(EMAIL_FIELD_ERROR)
        if (userData.password.isEmpty()) emptyFieldsList.add(PASSWORD_FIELD_ERROR)

        if (emptyFieldsList.isNotEmpty()) {
            return@withContext SignInTextError.createEmptyFieldMessage(emptyFieldsList)
        }
        if (!userData.email.isValidEmail()) {
            return@withContext SignInTextError.createInvalidEmailMessage()
        }

        null
    }

    override suspend fun isSignUpDataValid(userData: UserDataSignUp): SignUpTextError? = withContext(dispatchers.io) {
        val emptyFieldsList = mutableListOf<UserHighlightType>()

        if (userData.email.isEmpty()) emptyFieldsList.add(EMAIL_FIELD_ERROR)
        if (userData.userName.isEmpty()) emptyFieldsList.add(USERNAME_FIELD_ERROR)
        if (userData.password.isEmpty()) emptyFieldsList.add(PASSWORD_FIELD_ERROR)
        if (userData.passwordRepeated.isEmpty()) emptyFieldsList.add(PASSWORD_AGAIN_FIELD_ERROR)

        if (emptyFieldsList.isNotEmpty()) {
            return@withContext SignUpTextError.createEmptyFieldMessage(emptyFieldsList)
        }
        if (!userData.email.isValidEmail()) {
            return@withContext SignUpTextError.createInvalidEmailMessage()
        }
        if (userData.password != userData.passwordRepeated) {
            return@withContext SignUpTextError.createNotEqualPasswordMessage()
        }
        if (!userData.password.isLongPassword()) {
            return@withContext SignUpTextError.createShortPasswordMessage()
        }
        if (!userData.password.hasUpperChar()) {
            return@withContext SignUpTextError.createWeakPasswordMessage()
        }

        null
    }
}