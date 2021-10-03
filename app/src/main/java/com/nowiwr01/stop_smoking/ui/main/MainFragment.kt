package com.nowiwr01.stop_smoking.ui.main

import com.nowiwr01.stop_smoking.R
import com.nowiwr01.stop_smoking.data.Star
import com.nowiwr01.stop_smoking.data.StarAdapter
import com.nowiwr01.stop_smoking.databinding.FragmentMainBinding
import com.nowiwr01.stop_smoking.ui.base.BaseFragment
import com.nowiwr01.stop_smoking.ui.main.info.InfoBottomSheet.Companion.TYPE_FREE_TIME
import com.nowiwr01.stop_smoking.ui.main.info.InfoBottomSheet.Companion.TYPE_STRONG_DESIRE
import com.nowiwr01.stop_smoking.utils.extensions.setOnSingleClickListener
import org.koin.android.ext.android.inject
import org.koin.core.parameter.parametersOf

class MainFragment(
    override val layoutResId: Int = R.layout.fragment_main
) : BaseFragment<FragmentMainBinding>() {

    private val navigator by inject<MainNavigator> { parametersOf(this) }

    override fun setViews() {
        setRecyclerView()
    }

    override fun setListeners() {
        binding.infoFreeTime.infoFreeTimeInfoIcon.setOnSingleClickListener {
            showInfo(TYPE_FREE_TIME)
        }
        binding.infoDesire.infoDesireInfoIcon.setOnSingleClickListener {
            showInfo(TYPE_STRONG_DESIRE)
        }
    }

    private fun showInfo(type: String) {
        navigator.toInfo(type)
    }

    private fun setRecyclerView() {
        binding.starsRecycler.apply {
            adapter = StarAdapter(getStars())
        }
    }

    private fun getStars() = listOf(
        Star(), Star(), Star(), Star(), Star()
    )
}