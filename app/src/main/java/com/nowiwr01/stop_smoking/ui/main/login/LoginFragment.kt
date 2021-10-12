package com.nowiwr01.stop_smoking.ui.main.login

import com.google.android.material.tabs.TabLayoutMediator
import com.nowiwr01.stop_smoking.R
import com.nowiwr01.stop_smoking.databinding.FragmentLoginBinding
import com.nowiwr01.stop_smoking.ui.base.BaseFragment
import com.nowiwr01.stop_smoking.ui.main.login.data.VPAdapter

class LoginFragment(
    override val layoutResId: Int = R.layout.fragment_login
) : BaseFragment<FragmentLoginBinding>() {

    override fun setViews() {
        hideBottomBar()
        setupViewPager()
    }

    private fun setupViewPager() {
        binding.viewPager.adapter = VPAdapter(parentFragmentManager, lifecycle)
        TabLayoutMediator(binding.tabLayout, binding.viewPager) { tab, position ->
            tab.text = if (position == 0) SIGN_IN_TITLE else SIGN_UP_TITLE
        }.attach()
    }

    private companion object {
        const val SIGN_UP_TITLE = "Sign Up"
        const val SIGN_IN_TITLE = "Sign In"
    }
}