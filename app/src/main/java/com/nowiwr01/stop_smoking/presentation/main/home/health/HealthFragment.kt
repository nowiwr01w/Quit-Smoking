package com.nowiwr01.stop_smoking.presentation.main.home.health

import by.kirich1409.viewbindingdelegate.viewBinding
import com.nowiwr01.domain.model.user.User
import com.nowiwr01.stop_smoking.R
import com.nowiwr01.stop_smoking.databinding.FragmentHealthBinding
import com.nowiwr01.stop_smoking.model.adapters.HealthAdapter
import com.nowiwr01.stop_smoking.model.health.HealthBuilder
import com.nowiwr01.stop_smoking.presentation.base.BaseFragment
import com.nowiwr01.stop_smoking.presentation.main.home.UserViewModel
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class HealthFragment: BaseFragment(R.layout.fragment_health) {

    private val binding by viewBinding<FragmentHealthBinding>()
    private val viewModel by sharedViewModel<UserViewModel>()

    override fun setListeners() {
        binding.toolbar.setNavigationOnClickListener {
            baseActivity.onBackPressed()
        }
    }

    override fun setObservers() {
        viewModel.userData.observe(viewLifecycleOwner) {
            setRecyclerView(it)
        }
    }

    private fun setRecyclerView(user: User) {
        binding.healthRecycler.adapter = HealthAdapter(
            HealthBuilder.getHealthList(context),
            user.smokeInfo
        )
    }
}