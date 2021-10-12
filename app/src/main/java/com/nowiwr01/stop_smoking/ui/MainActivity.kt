package com.nowiwr01.stop_smoking.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.nowiwr01.stop_smoking.R
import com.nowiwr01.stop_smoking.databinding.ActivityMainBinding
import com.nowiwr01.stop_smoking.utils.extensions.setGone
import com.nowiwr01.stop_smoking.utils.extensions.setVisible

class MainActivity : AppCompatActivity() {

    private lateinit var navController: NavController
    private lateinit var appBarConfiguration: AppBarConfiguration

    private lateinit var binding: ActivityMainBinding

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

    fun showBottomBar() = binding.bottomNav.setVisible()

    fun hideBottomBar() = binding.bottomNav.setGone()
}