package com.nowiwr01.stop_smoking.ui.main.login.sign_up

import android.util.Log
import com.nowiwr01.stop_smoking.R
import com.nowiwr01.stop_smoking.databinding.FragmentSignUpBinding
import com.nowiwr01.stop_smoking.domain.UserDataSignUp
import com.nowiwr01.stop_smoking.logic.errors.SignUpTextError
import com.nowiwr01.stop_smoking.ui.base.BaseFragment
import com.nowiwr01.stop_smoking.utils.extensions.showSnackbar
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class SignUpFragment(
    override val layoutResId: Int = R.layout.fragment_sign_up
) : BaseFragment<FragmentSignUpBinding>() {

    private val viewModel by viewModel<SignUpViewModel>()
    private val controller by inject<SignUpController> { parametersOf(binding) }

    override fun setViews() {
        controller.setTextChangedCallback()
    }

    override fun setObservers() {
        viewModel.userData.observe(viewLifecycleOwner) {
            // stub
        }
    }

    override fun setListeners() {
        binding.signUp.setOnClickListener {
            val userData = controller.getUserData()
            signUpIfValid(userData)
        }
    }

    private fun signUpIfValid(userData: UserDataSignUp) {
        controller.setDefaultAll()
        val error = viewModel.isUserInputValid(userData)
        if (error == null) {
            viewModel.signUp(userData.email, userData.password)
        } else {
            proceedError(error)
        }
    }

    private fun proceedError(error: SignUpTextError) {
        controller.setErrorByNumbers(error.list)
        showSnackbar(error.message)
    }
}