package com.nowiwr01.stop_smoking.ui.main.login.data

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.nowiwr01.stop_smoking.ui.main.login.sign_in.SignInFragment
import com.nowiwr01.stop_smoking.ui.main.login.sign_up.SignUpFragment

class ViewPagerAdapter(fragment: Fragment): FragmentStateAdapter(fragment) {
    override fun getItemCount() = 2

    override fun createFragment(pos: Int) = if (pos == 0) SignInFragment() else SignUpFragment()
}