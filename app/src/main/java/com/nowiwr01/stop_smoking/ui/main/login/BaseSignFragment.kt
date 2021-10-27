package com.nowiwr01.stop_smoking.ui.main.login

import android.widget.EditText
import androidx.viewbinding.ViewBinding
import com.nowiwr01.stop_smoking.databinding.FragmentSignInBinding
import com.nowiwr01.stop_smoking.databinding.FragmentSignUpBinding
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
import timber.log.Timber

abstract class BaseSignFragment(layoutResId: Int): BaseFragment(layoutResId) {

    abstract val vb: ViewBinding
    abstract val inputFields: List<EditText>
    abstract val controller: BaseSignViewsController

    protected val viewModel by sharedViewModel<AuthViewModel>()

    override fun setViews() {
        controller.setTextChangedCallback()
    }

    override fun setListeners() {
        inputFields.setAllFocusListener {
            toggleMotionLayout(true)
        }
        requireView().setKeyboardListener(inputFields) {
            toggleMotionLayout(false)
        }
    }

    override fun setObservers() {
        viewModel.user.observeEvent(this) {
            success(it)
        }
        viewModel.progress.observe(viewLifecycleOwner) {
            controller.manageProgressBar(it)
        }
        viewModel.authError.observeEvent(viewLifecycleOwner) {
            controller.setErrorByType(it.list)
            showSnackbar(rootView = getParent().getRoot(), message = it.message)
        }
    }

    private fun success(user: User) {
        showSnackbar(
            rootView = getParent().getRoot(),
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

    protected fun authVk() {
        VK.login(baseActivity, arrayListOf(VKScope.EMAIL))
    }

    private fun toggleMotionLayout(expand: Boolean) {
        getParent().expandOrCollapse(expand)
        toggleAuthMotionLayout(expand)
    }

    private fun getParent(): AuthFragment {
        val fragment = parentFragment
        return if (fragment is AuthFragment) fragment else throw IllegalStateException("Liar")
    }

    private fun toggleAuthMotionLayout(expand: Boolean) {
        val layout = getSpecificMotionLayout()
        if (expand) layout.transitionToEnd() else layout.transitionToStart()
    }

    private fun getSpecificMotionLayout() = when (vb) {
        is FragmentSignInBinding -> (vb as FragmentSignInBinding).motionSignIn
        is FragmentSignUpBinding -> (vb as FragmentSignUpBinding).motionSignUp
        else -> throw IllegalStateException("Not an auth layout")
    }

    protected fun setDefaultMotionMode() {
        requireView().hideKeyboard()
        getSpecificMotionLayout().transitionToStart()
        toggleMotionLayout(false)
    }
}