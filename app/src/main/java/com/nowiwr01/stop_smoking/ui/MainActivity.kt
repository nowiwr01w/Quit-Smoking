package com.nowiwr01.stop_smoking.ui

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupWithNavController
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.Task
import com.nowiwr01.stop_smoking.Const.GOOGLE_MARK
import com.nowiwr01.stop_smoking.R
import com.nowiwr01.stop_smoking.databinding.ActivityMainBinding
import com.nowiwr01.stop_smoking.ui.main.auth.AuthViewModel
import com.nowiwr01.stop_smoking.utils.extensions.createVKAuthCallback
import com.nowiwr01.stop_smoking.utils.extensions.setGone
import com.nowiwr01.stop_smoking.utils.extensions.setVisible
import com.vk.api.sdk.VK
import org.koin.androidx.viewmodel.ext.android.viewModel
import timber.log.Timber

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController
    private lateinit var appBarConfiguration: AppBarConfiguration

    private val viewModel by viewModel<AuthViewModel>()

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
        val callback = createVKAuthCallback {
            viewModel.authVk(it)
        }
        if (!VK.onActivityResult(requestCode, resultCode, data, callback)) {
            super.onActivityResult(requestCode, resultCode, data)
        }
        if (requestCode == GOOGLE_MARK) {
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
            handleSignInResult(task)
        }
    }

    private fun handleSignInResult(completedTask: Task<GoogleSignInAccount>) {
        try {
            val account = completedTask.getResult(ApiException::class.java)!!
            viewModel.googleAuth(account)
        } catch (e: ApiException) {
            Timber.tag(this::class.java.simpleName).e("signInResult:failed code=${e.statusCode}, message=${e.message}")
        }
    }

    fun showBottomBar() = binding.bottomNav.setVisible()

    fun hideBottomBar() = binding.bottomNav.setGone()
}