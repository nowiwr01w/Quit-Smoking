package com.nowiwr01.stop_smoking.ui.main

import by.kirich1409.viewbindingdelegate.viewBinding
import com.nowiwr01.stop_smoking.R
import com.nowiwr01.stop_smoking.data.Star
import com.nowiwr01.stop_smoking.data.StarAdapter
import com.nowiwr01.stop_smoking.databinding.FragmentDesireBinding
import com.nowiwr01.stop_smoking.databinding.FragmentMainBinding
import com.nowiwr01.stop_smoking.ui.base.BaseFragment
import com.nowiwr01.stop_smoking.ui.main.info.InfoBottomSheet.Companion.TYPE_FREE_TIME
import com.nowiwr01.stop_smoking.ui.main.info.InfoBottomSheet.Companion.TYPE_STRONG_DESIRE
import com.nowiwr01.stop_smoking.utils.extensions.setOnSingleClickListener
import org.koin.android.ext.android.inject
import org.koin.core.parameter.parametersOf

class MainFragment : BaseFragment(R.layout.fragment_main) {

    private val vb by viewBinding<FragmentMainBinding>()

    private val navigator by inject<MainNavigator> { parametersOf(this) }

    override fun setViews() {
        setRecyclerView()
    }

    override fun setListeners() {
        vb.infoFreeTime.infoFreeTimeInfoIcon.setOnSingleClickListener {
            showInfo(TYPE_FREE_TIME)
        }
        vb.infoDesire.infoDesireInfoIcon.setOnSingleClickListener {
            showInfo(TYPE_STRONG_DESIRE)
        }
    }

    private fun showInfo(type: String) {
        navigator.toInfo(type)
    }

    private fun setRecyclerView() {
        vb.starsRecycler.apply {
            adapter = StarAdapter(getStars())
        }
    }

    private fun getStars() = listOf(
        Star(), Star(), Star(), Star(), Star()
    )
}