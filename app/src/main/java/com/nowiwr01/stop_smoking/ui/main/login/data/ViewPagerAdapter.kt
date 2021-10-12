package com.nowiwr01.stop_smoking.ui.main.login.data

import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.nowiwr01.stop_smoking.ui.main.login.sign_in.SignInFragment
import com.nowiwr01.stop_smoking.ui.main.login.sign_up.SignUpFragment

class VPAdapter(
    fm: FragmentManager,
    lifecycle: Lifecycle
): FragmentStateAdapter(fm, lifecycle) {

    override fun getItemCount() = 2

    override fun createFragment(position: Int) = if (position == 0) SignInFragment() else SignUpFragment()
}