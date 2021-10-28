package com.nowiwr01.stop_smoking.ui.main.auth

import androidx.core.view.isVisible
import com.nowiwr01.stop_smoking.databinding.FragmentAuthBinding
import com.nowiwr01.stop_smoking.domain.user.UserData
import com.nowiwr01.stop_smoking.domain.user.UserDataSignIn
import com.nowiwr01.stop_smoking.domain.user.UserDataSignUp
import com.nowiwr01.stop_smoking.ui.main.auth.AuthFragment.Companion.SIGN_IN
import com.nowiwr01.stop_smoking.ui.main.auth.data.UserHighlightType
import com.nowiwr01.stop_smoking.ui.main.auth.data.UserHighlightType.*
import com.nowiwr01.stop_smoking.utils.extensions.doOnTextChanged
import com.nowiwr01.stop_smoking.utils.extensions.setDefault
import com.nowiwr01.stop_smoking.utils.extensions.setError

class AuthViewsController(
    private val binding: FragmentAuthBinding
) {

    fun getUserData(type: String): UserData {
        val email = binding.email.text.toString()
        val userName = binding.username.text.toString()
        val password = binding.password0.text.toString()
        val passwordRepeat = binding.password1.text.toString()
        return if (type == SIGN_IN) {
            UserDataSignIn(email, password)
        } else {
            UserDataSignUp(email, userName, password, passwordRepeat)
        }
    }

    fun manageProgressBar(type: String, isVisible: Boolean) {
        binding.authBtn.text = if (isVisible) "" else type
        binding.authBtnProgress.isVisible = isVisible
    }

    fun setErrorByType(numbers: List<UserHighlightType>) {
        numbers.forEach {
            when (it) {
                EMAIL_FIELD_ERROR -> binding.email.setError()
                USERNAME_FIELD_ERROR -> binding.username.setError()
                PASSWORD_FIELD_ERROR -> binding.password0.setError()
                PASSWORD_AGAIN_FIELD_ERROR -> binding.password1.setError()
            }
        }
    }

    fun setTextChangedCallback() {
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

    fun setDefaultAll() {
        binding.email.setDefault()
        binding.username.setDefault()
        binding.password0.setDefault()
        binding.password1.setDefault()
    }

    fun setTabLayout() {
        val tab = binding.tabLayout.newTab().apply {
            text = "Sign Up"
        }
        binding.tabLayout.addTab(tab)
    }
}