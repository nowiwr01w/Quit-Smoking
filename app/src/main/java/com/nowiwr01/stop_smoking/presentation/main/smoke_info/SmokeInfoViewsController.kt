package com.nowiwr01.stop_smoking.presentation.main.smoke_info

import android.widget.EditText
import androidx.core.view.isVisible
import com.nowiwr01.domain.model.user.User
import com.nowiwr01.domain.model.user.smoke_info.SmokeInfo
import com.nowiwr01.domain.utils.extensions.round
import com.nowiwr01.domain.utils.extensions.showSnackbar
import com.nowiwr01.stop_smoking.databinding.FragmentSmokingInfoBinding
import com.nowiwr01.stop_smoking.presentation.base.BaseFragment
import com.nowiwr01.stop_smoking.utils.extensions.doOnTextChanged
import com.nowiwr01.stop_smoking.utils.extensions.setDefault
import com.nowiwr01.stop_smoking.utils.extensions.setError
import org.joda.time.DateTime

class SmokeInfoViewsController(
    private val fragment: BaseFragment,
    private val binding: FragmentSmokingInfoBinding,
    private val viewModel: SmokeInfoViewModel
) {

    fun hasEmpty(inputFields: List<EditText>): Boolean {
        var hasEmpty = false
        inputFields.forEach { field ->
            if (field.text.isNullOrEmpty()) {
                hasEmpty = true
                field.setError()
            }
        }
        if (hasEmpty) {
            viewModel.setProgress(false)
            fragment.showSnackbar("Необходимо заполнить все поля")
        }
        return hasEmpty
    }

    fun setBreakDate(dateTime: DateTime, hours: Int, minutes: Int) {
        viewModel.setBreakDate(dateTime)
        binding.breakDate.setText(
            String.format("%s/%s/%s %s:%s", dateTime.dayOfMonth.round(), dateTime.monthOfYear.round(),
                dateTime.yearOfEra, hours.round(), minutes.round()
            )
        )
    }

    fun getSmokeInfo() = SmokeInfo(
        breakDate = viewModel.breakDate.value!!.millis,
        craves = listOf(),
        currency = viewModel.currency.value!!.symbol,
        cigarettesPerDay = binding.cigarettesPerDay.text.toString().toInt(),
        cigarettesPerPack = binding.cigarettesPerPack.text.toString().toInt(),
        cigarettesPackCost = binding.onePackCost.text.toString().toDouble()
    )

    fun setTextChangedCallback(inputFields: List<EditText>) {
        inputFields.forEach { field ->
            field.doOnTextChanged { field.setDefault() }
        }
    }

    fun manageProgressBar(isVisible: Boolean) {
        binding.continueBtn.text = if (isVisible) "" else "Continue"
        binding.continueBtnProgress.isVisible = isVisible
    }

    fun showSuccessSnackBar(user: User) {
        viewModel.setProgress(false)
        fragment.showSnackbar(
            message = String.format("%s, добро пожаловать!", user.username),
            customColor = true
        )
    }
}