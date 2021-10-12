package com.nowiwr01.stop_smoking.logic.repositories.impl

import com.nowiwr01.stop_smoking.domain.UserDataSignIn
import com.nowiwr01.stop_smoking.domain.UserDataSignUp
import com.nowiwr01.stop_smoking.logic.errors.SignInTextError
import com.nowiwr01.stop_smoking.logic.errors.SignUpTextError
import com.nowiwr01.stop_smoking.logic.repositories.UserDataRepository
import com.nowiwr01.stop_smoking.ui.main.login.data.UserHighlightType
import com.nowiwr01.stop_smoking.ui.main.login.data.UserHighlightType.*
import com.nowiwr01.stop_smoking.utils.extensions.hasUpperChar
import com.nowiwr01.stop_smoking.utils.extensions.isLongPassword
import com.nowiwr01.stop_smoking.utils.extensions.isValidEmail

class UserDataRepositoryImpl: UserDataRepository {

    override fun isSignInDataValid(userData: UserDataSignIn): SignInTextError? {
        val emptyFieldsList = mutableListOf<UserHighlightType>()

        if (userData.email.isEmpty()) emptyFieldsList.add(EMAIL_FIELD_ERROR)
        if (userData.password.isEmpty()) emptyFieldsList.add(PASSWORD_FIELD_ERROR)

        if (emptyFieldsList.isNotEmpty()) {
            return SignInTextError.createEmptyFieldMessage(emptyFieldsList)
        }
        if (!userData.email.isValidEmail()) {
            return SignInTextError.createInvalidEmailMessage()
        }

        return null
    }

    override fun isSignUpDataValid(userData: UserDataSignUp): SignUpTextError? {
        val emptyFieldsList = mutableListOf<UserHighlightType>()

        if (userData.email.isEmpty()) emptyFieldsList.add(EMAIL_FIELD_ERROR)
        if (userData.userName.isEmpty()) emptyFieldsList.add(USERNAME_FIELD_ERROR)
        if (userData.password.isEmpty()) emptyFieldsList.add(PASSWORD_FIELD_ERROR)
        if (userData.passwordRepeated.isEmpty()) emptyFieldsList.add(PASSWORD_AGAIN_FIELD_ERROR)

        if (emptyFieldsList.isNotEmpty()) {
            return SignUpTextError.createEmptyFieldMessage(emptyFieldsList)
        }
        if (!userData.email.isValidEmail()) {
            return SignUpTextError.createInvalidEmailMessage()
        }
        if (userData.password != userData.passwordRepeated) {
            return SignUpTextError.createNotEqualPasswordMessage()
        }
        if (!userData.password.isLongPassword()) {
            return SignUpTextError.createShortPasswordMessage()
        }
        if (!userData.password.hasUpperChar()) {
            return SignUpTextError.createWeakPasswordMessage()
        }

        return null
    }
}