package com.nowiwr01.stop_smoking.ui.main.login.sign_in

import com.nowiwr01.stop_smoking.R
import com.nowiwr01.stop_smoking.databinding.FragmentSignInBinding
import com.nowiwr01.stop_smoking.domain.UserDataSignIn
import com.nowiwr01.stop_smoking.logic.errors.SignInTextError
import com.nowiwr01.stop_smoking.ui.main.login.BaseSignFragment
import com.nowiwr01.stop_smoking.utils.extensions.showSnackbar
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class SignInFragment(
    override val layoutResId: Int = R.layout.fragment_sign_in
) : BaseSignFragment<FragmentSignInBinding>(layoutResId) {

    override val inputFields by lazy {
        listOf(binding.email, binding.password0)
    }

    private val viewModel by viewModel<SignInViewModel>()
    private val controller by inject<SignInController> { parametersOf(binding) }

    override fun setViews() {
        controller.setTextChangedCallback()
    }

    override fun setListeners() {
        super.setListeners()
        binding.login.setOnClickListener {
            val userData = controller.getUserData()
            signInIfValid(userData)
        }
    }

    private fun signInIfValid(userData: UserDataSignIn) {
        val error = viewModel.isUserInputValid(userData)
        if (error == null) {
            viewModel.login(userData.email, userData.password)
        } else {
            proceedError(error)
        }
    }

    private fun proceedError(error: SignInTextError) {
        controller.setErrorByType(error.list)
        showSnackbar(error.message)
    }
}