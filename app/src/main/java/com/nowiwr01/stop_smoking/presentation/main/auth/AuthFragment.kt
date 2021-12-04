package com.nowiwr01.stop_smoking.presentation.main.auth

import by.kirich1409.viewbindingdelegate.viewBinding
import com.nowiwr01.data.repository.AuthRepositoryImpl.Companion.TYPE_NEW_USER
import com.nowiwr01.data.repository.AuthRepositoryImpl.Companion.TYPE_OLD_USER
import com.nowiwr01.domain.model.user.User
import com.nowiwr01.domain.utils.extensions.showSnackbar
import com.nowiwr01.stop_smoking.R
import com.nowiwr01.stop_smoking.databinding.FragmentAuthBinding
import com.nowiwr01.stop_smoking.presentation.base.BaseExpandableFragment
import com.nowiwr01.stop_smoking.utils.extensions.hideKeyboard
import com.nowiwr01.stop_smoking.utils.observeEvent
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import org.koin.core.parameter.parametersOf

class AuthFragment: BaseExpandableFragment(R.layout.fragment_auth) {

    private val vb by viewBinding<FragmentAuthBinding>()
    override val inputFields by lazy { listOf(vb.email, vb.username, vb.password0, vb.password1) }
    override val expandableMotionLayout by lazy { vb.motionLayout }

    private val viewModel by sharedViewModel<AuthViewModel>()
    private val navigator by inject<AuthNavigator> { parametersOf(this) }
    private val controller by inject<AuthViewsController> { parametersOf(vb, viewModel) }

    override fun initialize() {
        checkAuth()
    }

    private fun checkAuth() {
        if (viewModel.isUserAuthorized()) {
            navigator.toHomeScreen()
        }
    }

    override fun setViews() {
        super.setViews()
        controller.setTabLayout()
        controller.setTextChangedCallback()
        controller.setAuthType(resources, viewModel.currentMode)
    }

    override fun setListeners() {
        vb.vkAuth.setOnClickListener {
            navigator.toVkAuth()
        }
        vb.googleAuth.setOnClickListener {
            navigator.toGoogleAuth()
        }
        vb.facebookAuth.setOnClickListener {
            navigator.toFacebookAuth()
        }
        vb.authBtn.setOnClickListener {
            setDefaultMotionMode()
            viewModel.checkAndAuth(controller.getUserData(viewModel.currentMode))
        }
        vb.haveAccountTitle.setOnClickListener {
            val type = if (viewModel.currentMode == SIGN_UP) SIGN_IN else SIGN_UP
            controller.setAuthType(resources, type)
            controller.setDefaultAll()
        }
    }

    override fun setObservers() {
        viewModel.userData.observe(viewLifecycleOwner) {
            onAuthSuccess(it)
        }
        viewModel.progress.observe(viewLifecycleOwner) {
            controller.manageProgressBar(viewModel.currentMode, it)
        }
        viewModel.authError.observeEvent(viewLifecycleOwner) {
            controller.setErrorByType(it.list)
            showSnackbar(it.message)
        }
    }

    private fun onAuthSuccess(userData: Pair<String, User>) {
        controller.setDefaultAll()
        when (userData.first) {
            TYPE_OLD_USER -> navigator.toHomeScreen()
            TYPE_NEW_USER -> navigator.toSmokeInfoScreen()
        }
    }

    private fun setDefaultMotionMode() {
        requireView().hideKeyboard()
        expandOrCollapse(false)
    }

    companion object {
        const val SIGN_UP = "Sign Up"
        const val SIGN_IN = "Sign In"
    }
}