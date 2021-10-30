package com.nowiwr01.stop_smoking.presentation.main.info

import androidx.core.view.isVisible
import androidx.navigation.fragment.navArgs
import by.kirich1409.viewbindingdelegate.viewBinding
import com.nowiwr01.stop_smoking.R
import com.nowiwr01.stop_smoking.databinding.BottomSheetInfoBinding
import com.nowiwr01.stop_smoking.presentation.base.BaseBottomSheet

class InfoBottomSheet: BaseBottomSheet(R.layout.bottom_sheet_info) {

    private val args by navArgs<InfoBottomSheetArgs>()
    override val binding by viewBinding<BottomSheetInfoBinding>()

    override fun setBottomSheetViews() {
        when (args.type) {
            TYPE_FREE_TIME -> setFreeTimeMode()
            TYPE_STRONG_DESIRE -> setStrongDesireMode()
        }
    }

    private fun setFreeTimeMode() {
        binding.infoFreeTimeDescription.isVisible = true
        binding.infoStrongDesireDescription.isVisible = false
    }

    private fun setStrongDesireMode() {
        binding.infoFreeTimeDescription.isVisible = false
        binding.infoStrongDesireDescription.isVisible = true
    }

    companion object {
        const val TYPE_FREE_TIME = "TYPE_FREE_TIME"
        const val TYPE_STRONG_DESIRE = "TYPE_STRONG_DESIRE"
    }
}