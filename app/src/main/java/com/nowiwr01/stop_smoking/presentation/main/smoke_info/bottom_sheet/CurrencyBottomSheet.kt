package com.nowiwr01.stop_smoking.presentation.main.smoke_info.bottom_sheet

import by.kirich1409.viewbindingdelegate.viewBinding
import com.nowiwr01.stop_smoking.R
import com.nowiwr01.stop_smoking.databinding.BottomSheetCurrenciesBinding
import com.nowiwr01.stop_smoking.model.adapters.CurrenciesAdapter
import com.nowiwr01.stop_smoking.presentation.base.BaseBottomSheet
import com.nowiwr01.stop_smoking.presentation.main.smoke_info.SmokeInfoViewModel
import org.joda.money.CurrencyUnit
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class CurrencyBottomSheet : BaseBottomSheet(R.layout.bottom_sheet_currencies) {

    override val binding by viewBinding<BottomSheetCurrenciesBinding>()
    private val viewModel by sharedViewModel<SmokeInfoViewModel>()

    private val special = mutableListOf(
        CurrencyUnit.of("USD"),
        CurrencyUnit.of("EUR"),
        CurrencyUnit.of("RUB"),
        CurrencyUnit.of("UAH"),
        CurrencyUnit.of("BYN"),
        CurrencyUnit.of("KZT"),
        CurrencyUnit.of("UZS"),
        CurrencyUnit.of("TJS"),
        CurrencyUnit.of("GEL")
    )

    private val except = CurrencyUnit.registeredCurrencies().toMutableList().apply {
        special.forEach { remove(it) }
    }

    private val currencies = mutableListOf<CurrencyUnit>().apply {
        addAll(special)
        addAll(except)
    }

    override fun setBottomSheetViews() {
        binding.recyclerCurrency.adapter = CurrenciesAdapter(currencies) {
            viewModel.setCurrency(it)
            dismiss()
        }
    }
}