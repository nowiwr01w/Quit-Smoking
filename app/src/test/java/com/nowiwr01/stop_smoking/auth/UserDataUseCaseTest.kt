package com.nowiwr01.stop_smoking.auth

import com.google.common.truth.Truth
import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.eq
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import com.nowiwr01.basetest.assertThat
import com.nowiwr01.basetest.testDispatcherProvider
import com.nowiwr01.data.repository.UserDataRepositoryImpl
import com.nowiwr01.data.validators.RegexEmailValidator
import com.nowiwr01.data.validators.RegexPasswordValidator
import com.nowiwr01.domain.model.base.mapFailure
import com.nowiwr01.domain.model.error.auth.SignInError
import com.nowiwr01.domain.model.error.auth.SignUpError
import com.nowiwr01.domain.model.user.UserDataSignIn
import com.nowiwr01.domain.model.user.UserDataSignUp
import com.nowiwr01.domain.usecase.UserDataUseCase
import com.nowiwr01.domain.validators.EmailValidator
import com.nowiwr01.domain.validators.PasswordValidator
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Test
import org.mockito.ArgumentMatchers

internal class UserDataUseCaseTest {

    private val emailValidator = mock<EmailValidator> {
        on { validate(ArgumentMatchers.anyString()) } doReturn true
    }
    private val passwordValidator = mock<PasswordValidator> {
        on { validate(ArgumentMatchers.anyString()) } doReturn true
    }

    private val userDataUseCase = UserDataUseCase(
        UserDataRepositoryImpl(
            testDispatcherProvider(),
            RegexEmailValidator(),
            RegexPasswordValidator()
        )
    )

    @Test
    fun `test empty login input error`() {
        runBlocking {
            userDataUseCase.checkUserInput(
                UserDataSignIn("", "")
            ).mapFailure {
                it.assertThat<SignInError.SignInEmptyFieldError>()
                Truth.assertThat(it.list.size == 2).isTrue()
            }
        }
    }

    @Test
    fun `test empty sign up input error`() {
        runBlocking {
            userDataUseCase.checkUserInput(
                UserDataSignUp("", "", "", "")
            ).mapFailure {
                it.assertThat<SignUpError.SignUpEmptyFieldError>()
                Truth.assertThat(it.list.size == 4).isTrue()
            }
        }
    }

    @Test
    fun `return error when sign up with incorrect email`() {
        whenever(emailValidator.validate(eq(EMAIL))) doReturn false
        runBlocking {
            userDataUseCase.checkUserInput(
                UserDataSignUp(EMAIL, NAME, PASSWORD, PASSWORD)
            ).mapFailure {
                it.assertThat<SignUpError.SignUpIncorrectPasswordError>()
            }
        }
    }

    @Test
    fun `return error when sign up with invalid password`() {
        whenever(passwordValidator.validate(eq(PASSWORD_WRONG))) doReturn false
        runBlocking {
            userDataUseCase.checkUserInput(
                UserDataSignUp(EMAIL, NAME, PASSWORD_WRONG, PASSWORD_WRONG)
            ).mapFailure {
                it.assertThat<SignUpError.SignUpIncorrectPasswordError>()
            }
        }
    }

    @Test
    fun `return error when sign up with different passwords`() {
        whenever(passwordValidator.validate(eq(PASSWORD_WRONG))) doReturn false
        runBlocking {
            userDataUseCase.checkUserInput(
                UserDataSignUp(EMAIL, NAME, PASSWORD, PASSWORD1)
            ).mapFailure {
                it.assertThat<SignUpError.SignUpNotEqualPasswordError>()
            }
        }
    }

    @Test
    fun `return error when login with incorrect email`() {
        whenever(emailValidator.validate(eq(EMAIL))) doReturn false
        runBlocking {
            userDataUseCase.checkUserInput(
                UserDataSignIn(EMAIL, PASSWORD)
            ).mapFailure {
                it.assertThat<SignInError.SignInInvalidEmailError>()
            }
        }
    }

    @Test
    fun `return error when login with invalid password`() {
        whenever(passwordValidator.validate(eq(PASSWORD))) doReturn false
        runBlocking {
            userDataUseCase.checkUserInput(
                UserDataSignIn(EMAIL, PASSWORD)
            ).mapFailure {
                it.assertThat<SignInError.SignInIncorrectPasswordError>()
            }
        }
    }

    private companion object {
        const val EMAIL = "user@mail.ru"
        const val NAME = "user"
        const val PASSWORD = "P@ssw0rd"
        const val PASSWORD1 = "P@ssw0rd1"
        const val PASSWORD_WRONG = "password"
    }
}