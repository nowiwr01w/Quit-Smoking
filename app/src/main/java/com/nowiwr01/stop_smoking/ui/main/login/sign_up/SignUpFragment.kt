package com.nowiwr01.stop_smoking.ui.main.login.sign_up

import by.kirich1409.viewbindingdelegate.viewBinding
import com.nowiwr01.stop_smoking.R
import com.nowiwr01.stop_smoking.databinding.FragmentSignUpBinding
import com.nowiwr01.stop_smoking.logic.errors.SignUpTextError
import com.nowiwr01.stop_smoking.ui.main.login.BaseSignFragment
import com.nowiwr01.stop_smoking.utils.extensions.showSnackbar
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class SignUpFragment : BaseSignFragment(R.layout.fragment_sign_up) {

    override val vb by viewBinding<FragmentSignUpBinding>()

    private val viewModel by viewModel<SignUpViewModel>()
    private val controller by inject<SignUpViewsController> { parametersOf(vb) }

    override val inputFields by lazy {
        listOf(vb.email, vb.username, vb.password0, vb.password1)
    }

    override fun setViews() {
        controller.setTextChangedCallback()
    }

    override fun setListeners() {
        super.setListeners()
        vb.signUp.setOnClickListener {
            setDefaultMotionMode()
            viewModel.signUp(controller.getUserData())
        }
    }

    override fun setObservers() {
        viewModel.signUpError.observe(viewLifecycleOwner) {
            proceedError(it)
        }
    }

    private fun proceedError(error: SignUpTextError) {
        controller.setErrorByNumbers(error.list)
        showSnackbar(error.message)
    }
}