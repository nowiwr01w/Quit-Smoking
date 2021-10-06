package com.nowiwr01.stop_smoking.ui.main.login.data

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.nowiwr01.stop_smoking.R
import com.nowiwr01.stop_smoking.ui.main.login.sign_in.SignInFragment
import com.nowiwr01.stop_smoking.ui.main.login.sign_up.SignUpFragment

class ViewPagerAdapter: RecyclerView.Adapter<ViewPagerAdapter.ViewPagerViewHolder>() {

    private val fragments = listOf(
        SignInFragment(), SignUpFragment()
    )

    override fun getItemCount() = fragments.size

    override fun onBindViewHolder(holder: ViewPagerViewHolder, position: Int) {}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewPagerViewHolder(
        LayoutInflater.from(parent.context).inflate(viewType, parent, false)
    )

    override fun getItemViewType(position: Int): Int {
        return if (position == 0) R.layout.fragment_sign_in else R.layout.fragment_sign_up
    }

    class ViewPagerViewHolder(itemView: View): RecyclerView.ViewHolder(itemView)
}