package com.nowiwr01.stop_smoking.ui.main.login

import android.widget.EditText
import androidx.databinding.ViewDataBinding
import com.nowiwr01.stop_smoking.databinding.FragmentSignInBinding
import com.nowiwr01.stop_smoking.databinding.FragmentSignUpBinding
import com.nowiwr01.stop_smoking.ui.base.BaseFragment
import com.nowiwr01.stop_smoking.utils.extensions.hideKeyboard
import com.nowiwr01.stop_smoking.utils.extensions.setAllFocusListener
import com.nowiwr01.stop_smoking.utils.extensions.setKeyboardListener

abstract class BaseSignFragment<T : ViewDataBinding>(
    override val layoutResId: Int
) : BaseFragment<T>() {

    abstract val inputFields: List<EditText>

    override fun setListeners() {
        inputFields.setAllFocusListener {
            toggleMotionLayout(true)
        }
        requireView().setKeyboardListener(inputFields) {
            toggleMotionLayout(false)
        }
    }

    private fun toggleMotionLayout(expand: Boolean) {
        val parent = parentFragmentManager.fragments[0]
        if (parent != null && parent is LoginFragment) {
            parent.expandOrCollapse(expand)
        }
        toggleAuthMotionLayout(binding, expand)
    }

    private fun toggleAuthMotionLayout(binding: T, expand: Boolean) {
        val layout = getSpecificMotionLayout(binding)
        if (expand) layout.transitionToEnd() else layout.transitionToStart()
    }

    protected fun setDefaultMotionMode(binding: T) {
        requireView().hideKeyboard()
        getSpecificMotionLayout(binding).transitionToStart()
        toggleMotionLayout(false)
    }

    private fun getSpecificMotionLayout(binding: T) = when (binding) {
        is FragmentSignInBinding -> binding.motionSignIn
        is FragmentSignUpBinding -> binding.motionSignUp
        else -> throw IllegalStateException("Not an auth layout")
    }
}