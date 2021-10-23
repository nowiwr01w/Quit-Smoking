package com.nowiwr01.stop_smoking.ui.main.login

import by.kirich1409.viewbindingdelegate.viewBinding
import com.google.android.material.tabs.TabLayoutMediator
import com.nowiwr01.stop_smoking.R
import com.nowiwr01.stop_smoking.databinding.FragmentDesireBinding
import com.nowiwr01.stop_smoking.databinding.FragmentLoginBinding
import com.nowiwr01.stop_smoking.ui.base.BaseFragment
import com.nowiwr01.stop_smoking.ui.main.login.data.VPAdapter

class LoginFragment : BaseFragment(R.layout.fragment_login) {

    private val vb by viewBinding<FragmentLoginBinding>()

    override fun setViews() {
        hideBottomBar()
        setupViewPager()
    }

    private fun setupViewPager() {
        vb.viewPager.adapter = VPAdapter(parentFragmentManager, lifecycle)
        TabLayoutMediator(vb.tabLayout, vb.viewPager) { tab, position ->
            tab.text = if (position == 0) SIGN_IN_TITLE else SIGN_UP_TITLE
        }.attach()
    }

    fun expandOrCollapse(expand: Boolean) {
        if (expand) {
            vb.motionLayout.transitionToEnd()
        } else {
            vb.motionLayout.transitionToStart()
        }
    }

    private companion object {
        const val SIGN_UP_TITLE = "Sign Up"
        const val SIGN_IN_TITLE = "Sign In"
    }
}