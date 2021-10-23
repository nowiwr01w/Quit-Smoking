package com.nowiwr01.stop_smoking.ui.main.login.sign_in

import androidx.core.view.isVisible
import com.nowiwr01.stop_smoking.databinding.FragmentSignInBinding
import com.nowiwr01.stop_smoking.domain.UserDataSignIn
import com.nowiwr01.stop_smoking.ui.main.login.data.UserHighlightType
import com.nowiwr01.stop_smoking.ui.main.login.data.UserHighlightType.*
import com.nowiwr01.stop_smoking.utils.extensions.doOnTextChanged
import com.nowiwr01.stop_smoking.utils.extensions.setDefault
import com.nowiwr01.stop_smoking.utils.extensions.setError

class SignInViewsController(
    private val binding: FragmentSignInBinding
) {
    fun getUserData() = UserDataSignIn(
        binding.email.text.toString(),
        binding.password0.text.toString(),
    )

    fun setErrorByType(numbers: List<UserHighlightType>) {
        numbers.forEach {
            when (it) {
                EMAIL_FIELD_ERROR -> binding.email.setError()
                PASSWORD_FIELD_ERROR -> binding.password0.setError()
                else -> throw IllegalStateException("We don't need another types in SignIn errors, type = $it")
            }
        }
    }

    fun setTextChangedCallback() {
        listOf(binding.email, binding.password0).forEach {
            it.doOnTextChanged { it.setDefault() }
        }
    }

    fun manageProgressBar(isVisible: Boolean) {
        binding.login.text = if (isVisible) "" else "Sign In"
        binding.signInProgress.isVisible = isVisible
    }
}