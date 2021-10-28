package com.nowiwr01.stop_smoking.ui.main.auth

import androidx.core.view.isVisible
import by.kirich1409.viewbindingdelegate.viewBinding
import com.nowiwr01.stop_smoking.R
import com.nowiwr01.stop_smoking.databinding.FragmentAuthBinding
import com.nowiwr01.stop_smoking.domain.user.User
import com.nowiwr01.stop_smoking.ui.base.BaseFragment
import com.nowiwr01.stop_smoking.utils.extensions.hideKeyboard
import com.nowiwr01.stop_smoking.utils.extensions.setAllFocusListener
import com.nowiwr01.stop_smoking.utils.extensions.setKeyboardListener
import com.nowiwr01.stop_smoking.utils.extensions.showSnackbar
import com.nowiwr01.stop_smoking.utils.observeEvent
import com.vk.api.sdk.VK
import com.vk.api.sdk.auth.VKScope
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import org.koin.core.parameter.parametersOf
import timber.log.Timber

class AuthFragment : BaseFragment(R.layout.fragment_auth) {

    private val vb by viewBinding<FragmentAuthBinding>()
    private val inputFields by lazy { listOf(vb.email, vb.username, vb.password0, vb.password1) }

    private val viewModel by sharedViewModel<AuthViewModel>()
    private val controller by inject<AuthViewsController> { parametersOf(vb) }

    override fun setViews() {
        hideBottomBar()
        controller.setTabLayout()
        controller.setTextChangedCallback()
        setAuthType(viewModel.currentMode)
    }

    override fun setListeners() {
        inputFields.setAllFocusListener {
            expandOrCollapse(true)
        }
        requireView().setKeyboardListener(inputFields) {
            expandOrCollapse(false)
        }
        vb.vkAuth.setOnClickListener {
            VK.login(baseActivity, arrayListOf(VKScope.EMAIL))
        }
        vb.authBtn.setOnClickListener {
            setDefaultMotionMode()
            viewModel.checkAndAuth(controller.getUserData(viewModel.currentMode))
        }
        vb.haveAccountTitle.setOnClickListener {
            val type = if (viewModel.currentMode == SIGN_UP) SIGN_IN else SIGN_UP
            setAuthType(type)
            controller.setDefaultAll()
        }
    }

    override fun setObservers() {
        viewModel.user.observeEvent(this) {
            success(it)
        }
        viewModel.progress.observe(viewLifecycleOwner) {
            controller.manageProgressBar(viewModel.currentMode, it)
        }
        viewModel.authError.observeEvent(viewLifecycleOwner) {
            controller.setErrorByType(it.list)
            showSnackbar(it.message)
        }
    }

    private fun setAuthType(type: String) {
        vb.authBtn.text = type
        vb.username.isVisible = type != SIGN_IN
        vb.password1.isVisible = type != SIGN_IN
        viewModel.currentMode = type
        setTabTitle(type)
        vb.haveAccountTitle.text = if (type == SIGN_UP) {
            resources.getString(R.string.title_already_have_an_account)
        } else {
            resources.getString(R.string.title_first_time_here)
        }
    }

    private fun setTabTitle(type: String) {
        vb.tabLayout.getTabAt(0)?.let {
            it.text = type
        }
    }

    private fun success(user: User) {
        showSnackbar(
            message = String.format("%s, добро пожаловать!", user.username),
            customColor = true,
            showCallback = {
                Timber.tag("Auth").d("Load data")
            },
            hideCallback = {
                Timber.tag("Auth").d("Navigate to main")
            }
        )
    }

    private fun setDefaultMotionMode() {
        requireView().hideKeyboard()
        expandOrCollapse(false)
    }

    private fun expandOrCollapse(expand: Boolean) {
        if (expand) {
            vb.motionLayout.transitionToEnd()
        } else {
            vb.motionLayout.transitionToStart()
        }
    }

    companion object {
        const val SIGN_UP = "Sign Up"
        const val SIGN_IN = "Sign In"
    }
}