package com.nowiwr01.stop_smoking.ui.main.login

import android.widget.EditText
import androidx.viewbinding.ViewBinding
import com.nowiwr01.stop_smoking.databinding.FragmentSignInBinding
import com.nowiwr01.stop_smoking.databinding.FragmentSignUpBinding
import com.nowiwr01.stop_smoking.ui.base.BaseFragment
import com.nowiwr01.stop_smoking.utils.extensions.hideKeyboard
import com.nowiwr01.stop_smoking.utils.extensions.setAllFocusListener
import com.nowiwr01.stop_smoking.utils.extensions.setKeyboardListener

abstract class BaseSignFragment(layoutResId: Int): BaseFragment(layoutResId) {

    abstract val vb: ViewBinding

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
        toggleAuthMotionLayout(expand)
    }

    private fun toggleAuthMotionLayout(expand: Boolean) {
        val layout = getSpecificMotionLayout()
        if (expand) layout.transitionToEnd() else layout.transitionToStart()
    }

    protected fun setDefaultMotionMode() {
        requireView().hideKeyboard()
        getSpecificMotionLayout().transitionToStart()
        toggleMotionLayout(false)
    }

    private fun getSpecificMotionLayout() = when (vb) {
        is FragmentSignInBinding -> (vb as FragmentSignInBinding).motionSignIn
        is FragmentSignUpBinding -> (vb as FragmentSignUpBinding).motionSignUp
        else -> throw IllegalStateException("Not an auth layout")
    }
}