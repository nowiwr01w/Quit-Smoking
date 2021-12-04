package com.nowiwr01.data.validators

import com.nowiwr01.domain.validators.PasswordValidator
import java.util.regex.Pattern

/**
 * [PasswordValidator] based on regular expression.
 * Requirements:
 * - 8-16 characters,
 * - min 1 uppercase,
 * - min 1 number or 1 special character
 * */
class RegexPasswordValidator : PasswordValidator {

    private val pattern = Pattern.compile(
        "(?=.*([0-9]|[@#\$%^&+=!?]))(?=.[a-z]*)(?=.*[A-Z]).{8,16}"
    )

    override fun validate(value: String) = pattern.matcher(value).matches()
}