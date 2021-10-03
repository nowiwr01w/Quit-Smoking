package com.nowiwr01.stop_smoking.ui.base

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.nowiwr01.stop_smoking.R
import com.nowiwr01.stop_smoking.utils.extensions.dpToPx

abstract class BaseBottomSheet<T : ViewDataBinding>: BottomSheetDialogFragment() {

    protected abstract val layoutResId: Int

    protected lateinit var binding: T

    protected open fun setBottomSheetViews() {}
    protected open fun setListeners() {}
    protected open fun setObservers() {}
    protected open fun setActions() {}

    override fun getContext() = super.getContext() ?: requireContext()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val dialogContainer = LinearLayout(context).apply {
            orientation = LinearLayout.VERTICAL
        }
        val headerImg = ImageView(context).apply {
            setImageResource(R.drawable.ic_touch_dialog)
            setPadding(0, 24.dpToPx(requireContext()), 0, 24.dpToPx(requireContext()))
        }
        val header = FrameLayout(requireContext()).apply {
            addView(headerImg)
        }
        dialogContainer.addView(header)
        binding = DataBindingUtil.inflate(inflater, layoutResId, dialogContainer, false)
        dialogContainer.addView(binding.root)
        return dialogContainer
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setBottomSheetViews()
        setListeners()
        setObservers()
        setActions()
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = BottomSheetDialog(requireContext(), theme)
        dialog.setOnShowListener {
            val bottomSheet =
                dialog.findViewById(com.google.android.material.R.id.design_bottom_sheet) as FrameLayout?
            BottomSheetBehavior.from(bottomSheet!!).apply {
                state = BottomSheetBehavior.STATE_EXPANDED
            }
        }
        return dialog
    }
}