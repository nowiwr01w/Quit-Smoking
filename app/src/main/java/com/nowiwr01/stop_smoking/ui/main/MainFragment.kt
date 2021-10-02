package com.nowiwr01.stop_smoking.ui.main

import com.nowiwr01.stop_smoking.R
import com.nowiwr01.stop_smoking.data.Star
import com.nowiwr01.stop_smoking.data.StarAdapter
import com.nowiwr01.stop_smoking.databinding.FragmentMainBinding
import com.nowiwr01.stop_smoking.utils.extensions.navigate
import com.nowiwr01.stop_smoking.ui.base.BaseFragment
import com.nowiwr01.stop_smoking.utils.extensions.setOnSingleClickListener

class MainFragment(
    override val layoutResId: Int = R.layout.fragment_main
) : BaseFragment<FragmentMainBinding>() {

    override fun setViews() {
        setRecyclerView()
    }

    override fun setListeners() {
        binding.infoFreeTime.infoFreeTimeInfoIcon.setOnSingleClickListener {
            navigate(R.id.action_home_to_info)
        }
        binding.infoDesire.infoDesireInfoIcon.setOnSingleClickListener {
            navigate(R.id.action_home_to_info)
        }
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