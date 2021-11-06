package com.nowiwr01.stop_smoking.presentation.base

import android.widget.EditText
import androidx.constraintlayout.motion.widget.MotionLayout
import com.nowiwr01.stop_smoking.utils.extensions.setAllFocusListener
import com.nowiwr01.stop_smoking.utils.extensions.setKeyboardListener

abstract class BaseExpandableFragment(layoutResId: Int): BaseFragment(layoutResId) {

    abstract val inputFields: List<EditText>
    abstract val expandableMotionLayout: MotionLayout

    override fun setViews() {
        hideBottomBar()
        inputFields.setAllFocusListener {
            expandOrCollapse(true)
        }
        requireView().setKeyboardListener(inputFields) {
            expandOrCollapse(false)
        }
    }

    protected fun expandOrCollapse(expand: Boolean) {
        if (expand) {
            expandableMotionLayout.transitionToEnd()
        } else {
            expandableMotionLayout.transitionToStart()
        }
    }
}