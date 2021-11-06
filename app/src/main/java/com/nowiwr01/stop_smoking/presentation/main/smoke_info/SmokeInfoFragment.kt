package com.nowiwr01.stop_smoking.presentation.main.smoke_info

import androidx.core.view.isVisible
import by.kirich1409.viewbindingdelegate.viewBinding
import com.google.android.material.datepicker.MaterialDatePicker
import com.google.android.material.timepicker.MaterialTimePicker
import com.google.android.material.timepicker.TimeFormat
import com.nowiwr01.domain.model.user.User
import com.nowiwr01.domain.model.user.smoke_info.SmokeInfo
import com.nowiwr01.domain.utils.extensions.showSnackbar
import com.nowiwr01.stop_smoking.R
import com.nowiwr01.stop_smoking.databinding.FragmentSmokingInfoBinding
import com.nowiwr01.stop_smoking.presentation.base.BaseExpandableFragment
import com.nowiwr01.stop_smoking.utils.extensions.*
import com.nowiwr01.stop_smoking.utils.observeEvent
import org.joda.time.DateTime
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import org.koin.core.parameter.parametersOf
import timber.log.Timber

class SmokeInfoFragment: BaseExpandableFragment(R.layout.fragment_smoking_info) {

    private val vb by viewBinding<FragmentSmokingInfoBinding>()
    private val viewModel by sharedViewModel<SmokeInfoViewModel>()
    private val controller by inject<SmokeInfoViewsController> {
        parametersOf(this, vb, viewModel)
    }

    override val expandableMotionLayout by lazy { vb.motionLayout }
    override val inputFields by lazy {
        listOf(vb.onePackCost, vb.currency, vb.cigarettesPerPack, vb.cigarettesPerDay, vb.breakDate)
    }

    override fun setViews() {
        super.setViews()
        controller.setTextChangedCallback(inputFields)
    }

    override fun setListeners() {
        vb.continueBtn.setOnClickListener {
            updateSmokeInfo()
        }
        vb.breakDate.setOnClickListener {
            showDatePicker()
        }
        vb.currency.setOnClickListener {
            navigate(R.id.action_to_currency)
        }
    }

    override fun setObservers() {
        viewModel.currency.observe(viewLifecycleOwner) {
            vb.currency.setText(it.symbol)
        }
        viewModel.progress.observe(viewLifecycleOwner) {
            controller.manageProgressBar(it)
        }
        viewModel.errorMessage.observeEvent(viewLifecycleOwner) {
            showSnackbar(it)
        }
        viewModel.userData.observe(viewLifecycleOwner) {
            controller.showSuccessSnackBar(it)
            navigate(SmokeInfoFragmentDirections.actionInfoToHome(), setOptions = true)
        }
    }

    private fun updateSmokeInfo() {
        viewModel.setProgress(true)
        if (!controller.hasEmpty(inputFields)) {
            val smokeInfo = controller.getSmokeInfo()
            viewModel.setSmokeInfo(smokeInfo)
        }
    }

    private fun showDatePicker() {
        val picker = MaterialDatePicker.Builder.datePicker().build()
        picker.addOnPositiveButtonClickListener {
            showTimePicker(it)
        }
        picker.show(parentFragmentManager, "Date Picker")
    }

    private fun showTimePicker(date: Long) {
        val picker = MaterialTimePicker.Builder().setTimeFormat(TimeFormat.CLOCK_24H).build()
        picker.addOnPositiveButtonClickListener {
            val breakDate = DateTime(date).plusHours(picker.hour).plusMillis(picker.minute)
            controller.setBreakDate(breakDate, picker.hour, picker.minute)
        }
        picker.show(parentFragmentManager, "Time Picker")
    }
}