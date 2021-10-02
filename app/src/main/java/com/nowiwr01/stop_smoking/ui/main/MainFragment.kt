package com.nowiwr01.stop_smoking.ui.main

import com.nowiwr01.stop_smoking.R
import com.nowiwr01.stop_smoking.data.Star
import com.nowiwr01.stop_smoking.data.StarAdapter
import com.nowiwr01.stop_smoking.databinding.FragmentMainBinding
import com.nowiwr01.stop_smoking.ui.base.BaseFragment

class MainFragment(
    override val layoutResId: Int = R.layout.fragment_main
) : BaseFragment<FragmentMainBinding>() {

    override fun setViews() {
        setRecyclerView()
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