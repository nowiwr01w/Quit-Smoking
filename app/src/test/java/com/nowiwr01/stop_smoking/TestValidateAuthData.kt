package com.nowiwr01.stop_smoking

import com.google.common.truth.Truth.assertThat
import com.nowiwr01.domain.utils.extensions.hasUpperChar
import com.nowiwr01.domain.utils.extensions.isLongPassword
import com.nowiwr01.domain.utils.extensions.isValidEmail
import org.junit.Test

class TestValidateAuthData {

    @Test
    fun `test email validation`() {
        assertThat(CORRECT_EMAIL_1.isValidEmail()).isTrue()
        assertThat(CORRECT_EMAIL_2.isValidEmail()).isTrue()
        assertThat(INCORRECT_EMAIL_1.isValidEmail()).isFalse()
        assertThat(INCORRECT_EMAIL_2.isValidEmail()).isFalse()
    }

    @Test
    fun `test password validation`() {
        assertThat(isPasswordValid(CORRECT_PASSWORD)).isTrue()
        assertThat(isPasswordValid(INCORRECT_PASSWORD_1)).isFalse()
        assertThat(isPasswordValid(INCORRECT_PASSWORD_2)).isFalse()
    }

    private fun isPasswordValid(password: String): Boolean {
        return password.isLongPassword() && password.hasUpperChar()
    }

    private companion object {
        const val CORRECT_EMAIL_1 = "sample@any.com"
        const val CORRECT_EMAIL_2 = "sample@any.domen.com"
        const val INCORRECT_EMAIL_1 = "sample@gmailcom"
        const val INCORRECT_EMAIL_2 = "@gmail.com"

        const val CORRECT_PASSWORD = "Qwerty12"
        const val INCORRECT_PASSWORD_1 = "qwerty12"
        const val INCORRECT_PASSWORD_2 = "Qwerty"
    }
}