package com.nowiwr01.data.validators

import com.nowiwr01.domain.validators.EmailValidator
import java.util.regex.Pattern

/**
 * [EmailValidator] based on regular expressions.
 * */
class RegexEmailValidator : EmailValidator {

    private val pattern = Pattern.compile(
        "[a-zA-Z0-9\\+\\.\\_\\%\\-\\+]{1,256}" +
                "\\@" +
                "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,64}" +
                "(" +
                "\\." +
                "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,25}" +
                ")+"
    )

    override fun validate(value: String) = pattern.matcher(value).matches()
}