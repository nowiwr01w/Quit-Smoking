package com.nowiwr01.stop_smoking.presentation

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupWithNavController
import com.nowiwr01.stop_smoking.R
import com.nowiwr01.stop_smoking.databinding.ActivityMainBinding
import com.nowiwr01.stop_smoking.presentation.main.auth.AuthViewModel
import com.nowiwr01.stop_smoking.utils.extensions.setGone
import com.nowiwr01.stop_smoking.utils.extensions.setVisible
import com.nowiwr01.stop_smoking.utils.handler.ActivityResultChain
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController
    private lateinit var appBarConfiguration: AppBarConfiguration

    private val viewModel by viewModel<AuthViewModel>()
    private val activityResultChain by inject<ActivityResultChain> {
        parametersOf(lifecycle, viewModel)
    }

    override fun onSupportNavigateUp() = navController.navigateUp(appBarConfiguration)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_container) as NavHostFragment
        navController = navHostFragment.navController

        binding.bottomNav.setupWithNavController(navController)

        appBarConfiguration = AppBarConfiguration(
            setOf(R.id.main, R.id.desire, R.id.notes, R.id.chat)
        )
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        activityResultChain.onActivityResult(requestCode, resultCode, data)
    }

    fun showBottomBar() = binding.bottomNav.setVisible()

    fun hideBottomBar() = binding.bottomNav.setGone()
}