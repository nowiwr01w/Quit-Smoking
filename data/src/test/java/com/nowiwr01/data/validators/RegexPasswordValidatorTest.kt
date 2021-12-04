package com.nowiwr01.data.validators

import org.junit.Assert
import org.junit.Assert.assertFalse
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

internal class RegexPasswordValidatorTest {

    private val validator = RegexPasswordValidator()

    @ParameterizedTest(name = "check valid passwords: {0}")
    @ValueSource(
        strings = [
            "123456Sa",
            "passworD0",
            "passw0Rd",
            "P@ssword",
            "12345678901234AS",
            "@#!?123Aa",
            "password0A"
        ]
    )
    fun `check valid password`(password: String) {
        Assert.assertTrue(validator.validate(password))
    }

    @ParameterizedTest(name = "check invalid passwords: {0}")
    @ValueSource(
        strings = [
            "123456sa",
            "passwoRd",
            "password",
            "12345678901234ASDasqwe",
            "@#!?123aa"
        ]
    )
    fun `check invalid password`(password: String) {
        assertFalse(validator.validate(password))
    }
}