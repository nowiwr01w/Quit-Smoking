package com.nowiwr01.data.validators

import org.junit.Assert
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

internal class RegexEmailValidatorTest {

    private val validator = RegexEmailValidator()

    @ParameterizedTest(name = "check valid email: {0}")
    @ValueSource(
        strings = [
            "email@domain.com",
            "email@domain.test.com",
            "firstname.lastname@domain.com",
            "email@subdomain.domain.com",
            "firstname+lastname@domain.com",
            "email@123.123.123.123",
            "1234567890@domain.com",
            "email@domain-one.com",
            "_______@domain.com",
            "email@domain.co.jp",
            "firstname-lastname@domain.com"
        ]
    )
    fun `check valid emails`(email: String) {
        Assert.assertTrue(validator.validate(email))
    }

    @ParameterizedTest(name = "check invalid email: {0}")
    @ValueSource(
        strings = [
            "plainaddress",
            "#@%^%#$@#$@#.com",
            "@domain.com",
            "Joe Smith <email@domain.com>",
            "email.domain.com",
            "email@domain@domain.com",
            "あいうえお@domain.com",
            "email@domain.com (Joe Smith)",
            "email@domain",
            "email@-domain.com",
            "email@domain..com"
        ]
    )
    fun `check invalid emails`(email: String) {
        Assert.assertFalse(validator.validate(email))
    }
}