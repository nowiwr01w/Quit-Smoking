package com.nowiwr01.stop_smoking

import com.google.common.truth.Truth.assertThat
import com.nowiwr01.basetest.assertThat
import com.nowiwr01.basetest.testDispatcherProvider
import com.nowiwr01.data.repository.UserDataRepositoryImpl
import com.nowiwr01.domain.model.base.mapFailure
import com.nowiwr01.domain.model.error.SignInError.SignInEmptyFieldError
import com.nowiwr01.domain.model.error.SignUpError.*
import com.nowiwr01.domain.model.user.UserDataSignIn
import com.nowiwr01.domain.model.user.UserDataSignUp
import com.nowiwr01.domain.usecase.UserDataUseCase
import kotlinx.coroutines.runBlocking
import org.junit.Test

class TestAuthErrors {

    private val signInEmpty = UserDataSignIn("", "")
    private val signInWrongEmail = UserDataSignIn(INCORRECT_EMAIL, OK_PASSWORD)
    private val signInWrongPassword = UserDataSignIn(INCORRECT_EMAIL, WRONG_PASSWORD)
    private val signInCorrect = UserDataSignIn(OK_EMAIL, OK_PASSWORD)

    private val signUpEmpty = UserDataSignUp("", "", "", "")
    private val signUpWrongPassword1 = UserDataSignUp(OK_EMAIL, NAME, OK_PASSWORD, WRONG_PASSWORD)
    private val signUpWrongPassword2 = UserDataSignUp(OK_EMAIL, NAME, WRONG_PASSWORD, WRONG_PASSWORD)
    private val signUpWrongPassword3 = UserDataSignUp(OK_EMAIL, NAME, WRONG_PASSWORD_1, WRONG_PASSWORD_1)
    private val signUpCorrect = UserDataSignUp(OK_EMAIL, NAME, OK_PASSWORD, OK_PASSWORD)

    private val repository = UserDataRepositoryImpl(testDispatcherProvider())
    private val userDataUseCase = UserDataUseCase(repository)

    @Test
    fun `test login status`() {
        runBlocking {
            assertThat(userDataUseCase.checkUserInput(signInEmpty).isSuccess).isFalse()
            assertThat(userDataUseCase.checkUserInput(signInWrongEmail).isSuccess).isFalse()
            assertThat(userDataUseCase.checkUserInput(signInWrongPassword).isSuccess).isFalse()
            assertThat(userDataUseCase.checkUserInput(signInCorrect).isSuccess).isTrue()
        }
    }

    @Test
    fun `test sign up status`() {
        runBlocking {
            assertThat(userDataUseCase.checkUserInput(signUpEmpty).isSuccess).isFalse()
            assertThat(userDataUseCase.checkUserInput(signUpWrongPassword1).isSuccess).isFalse()
            assertThat(userDataUseCase.checkUserInput(signUpWrongPassword2).isSuccess).isFalse()
            assertThat(userDataUseCase.checkUserInput(signUpCorrect).isSuccess).isTrue()
        }
    }

    @Test
    fun `test empty auth input errors`() {
        runBlocking {
            userDataUseCase.checkUserInput(signInEmpty).mapFailure {
                it.assertThat<SignInEmptyFieldError>()
                assertThat(it.list.size == 2).isTrue()
            }
            userDataUseCase.checkUserInput(signUpEmpty).mapFailure {
                it.assertThat<SignUpEmptyFieldError>()
                assertThat(it.list.size == 4).isTrue()
            }
        }
    }

    @Test
    fun `test sign up with wrong password`() {
        runBlocking {
            userDataUseCase.checkUserInput(signUpWrongPassword1).mapFailure {
                it.assertThat<SignUpNotEqualPasswordError>()
            }
            userDataUseCase.checkUserInput(signUpWrongPassword2).mapFailure {
                it.assertThat<SignUpWeakPasswordError>()
            }
            userDataUseCase.checkUserInput(signUpWrongPassword3).mapFailure {
                it.assertThat<SignUpShortPasswordError>()
            }
        }
    }

    private companion object {
        const val OK_EMAIL = "sample@any.domen.com"
        const val INCORRECT_EMAIL = "sample@gmailcom"
        const val NAME = "nowiwr01"
        const val OK_PASSWORD = "Qwerty12"
        const val WRONG_PASSWORD = "qwerty12"
        const val WRONG_PASSWORD_1 = "Qwerty"
    }
}