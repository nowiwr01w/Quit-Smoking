package com.nowiwr01.stop_smoking.ui.main.login.sign_up

import androidx.core.view.isVisible
import com.nowiwr01.stop_smoking.databinding.FragmentSignUpBinding
import com.nowiwr01.stop_smoking.domain.UserDataSignUp
import com.nowiwr01.stop_smoking.ui.main.login.BaseSignViewsController
import com.nowiwr01.stop_smoking.ui.main.login.data.UserHighlightType
import com.nowiwr01.stop_smoking.ui.main.login.data.UserHighlightType.*
import com.nowiwr01.stop_smoking.utils.extensions.doOnTextChanged
import com.nowiwr01.stop_smoking.utils.extensions.setDefault
import com.nowiwr01.stop_smoking.utils.extensions.setError

class SignUpViewsController(
    private val binding: FragmentSignUpBinding
): BaseSignViewsController() {

    override fun getUserData() = UserDataSignUp(
        binding.email.text.toString(),
        binding.username.text.toString(),
        binding.password0.text.toString(),
        binding.password1.text.toString()
    )

    override fun manageProgressBar(isVisible: Boolean) {
        binding.signUp.text = if (isVisible) "" else "Sign In"
        binding.signUpProgress.isVisible = isVisible
    }

    override fun setErrorByType(numbers: List<UserHighlightType>) {
        numbers.forEach {
            when (it) {
                EMAIL_FIELD_ERROR -> binding.email.setError()
                USERNAME_FIELD_ERROR -> binding.username.setError()
                PASSWORD_FIELD_ERROR -> binding.password0.setError()
                PASSWORD_AGAIN_FIELD_ERROR -> binding.password1.setError()
            }
        }
    }

    override fun setTextChangedCallback() {
        listOf(binding.email, binding.username).forEach {
            it.doOnTextChanged { it.setDefault() }
        }
        listOf(binding.password0, binding.password1).forEach {
            it.doOnTextChanged {
                binding.password0.setDefault()
                binding.password1.setDefault()
            }
        }
    }
}