package com.nowiwr01.stop_smoking.ui.main.login.sign_in

import by.kirich1409.viewbindingdelegate.viewBinding
import com.nowiwr01.stop_smoking.R
import com.nowiwr01.stop_smoking.databinding.FragmentSignInBinding
import com.nowiwr01.stop_smoking.domain.User
import com.nowiwr01.stop_smoking.logic.errors.SignInTextError
import com.nowiwr01.stop_smoking.ui.main.login.BaseSignFragment
import com.nowiwr01.stop_smoking.utils.extensions.showSnackbar
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf
import timber.log.Timber

class SignInFragment : BaseSignFragment(R.layout.fragment_sign_in) {

    override val vb by viewBinding<FragmentSignInBinding>()

    override val inputFields by lazy { listOf(vb.email, vb.password0) }

    private val viewModel by viewModel<SignInViewModel>()
    private val controller by inject<SignInViewsController> { parametersOf(vb) }

    override fun setViews() {
        controller.setTextChangedCallback()
    }

    override fun setListeners() {
        super.setListeners()
        vb.login.setOnClickListener {
            setDefaultMotionMode()
            viewModel.login(controller.getUserData())
        }
    }

    override fun setObservers() {
        viewModel.signInError.observe(viewLifecycleOwner) {
            proceedError(it)
        }
        viewModel.progress.observe(viewLifecycleOwner) {
            controller.manageProgressBar(it)
        }
        viewModel.user.observe(viewLifecycleOwner) {
            successLogin(it)
        }
    }

    private fun successLogin(user: User) {
        showSnackbar(
            message = String.format("%s, добро пожаловать!", user.name),
            customColor = true,
            showCallback = {
                Timber.tag("Auth").d("Load data")
            },
            hideCallback = {
                Timber.tag("Auth").d("Navigate to main")
            }
        )
    }

    private fun proceedError(error: SignInTextError) {
        controller.setErrorByType(error.list)
        showSnackbar(error.message)
    }
}