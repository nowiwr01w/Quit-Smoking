package com.nowiwr01.stop_smoking.presentation.main.auth

import android.content.Intent
import by.kirich1409.viewbindingdelegate.viewBinding
import com.facebook.CallbackManager
import com.facebook.login.LoginManager
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.nowiwr01.stop_smoking.Const.GOOGLE_CLIENT_ID
import com.nowiwr01.stop_smoking.Const.GOOGLE_MARK
import com.nowiwr01.stop_smoking.R
import com.nowiwr01.stop_smoking.databinding.FragmentAuthBinding
import com.nowiwr01.domain.model.user.User
import com.nowiwr01.stop_smoking.presentation.base.BaseFragment
import com.nowiwr01.domain.utils.extensions.*
import com.nowiwr01.stop_smoking.utils.extensions.hideKeyboard
import com.nowiwr01.stop_smoking.utils.extensions.setAllFocusListener
import com.nowiwr01.stop_smoking.utils.extensions.setKeyboardListener
import com.nowiwr01.stop_smoking.utils.observeEvent
import com.vk.api.sdk.VK
import com.vk.api.sdk.auth.VKScope
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import org.koin.core.parameter.parametersOf
import timber.log.Timber

class AuthFragment : BaseFragment(R.layout.fragment_auth) {

    private val vb by viewBinding<FragmentAuthBinding>()
    private val inputFields by lazy { listOf(vb.email, vb.username, vb.password0, vb.password1) }

    private val viewModel by sharedViewModel<AuthViewModel>()
    private val navigator by inject<AuthNavigator> { parametersOf(this) }
    private val controller by inject<AuthViewsController> { parametersOf(vb, viewModel) }

    private lateinit var googleClient: GoogleSignInClient
    private lateinit var fbCallbackManager: CallbackManager

    override fun setViews() {
        hideBottomBar()
        controller.setTabLayout()
        controller.setTextChangedCallback()
        controller.setAuthType(resources, viewModel.currentMode)

        setGoogleClient()
        setFacebookClient()
    }

    override fun setListeners() {
        inputFields.setAllFocusListener {
            expandOrCollapse(true)
        }
        requireView().setKeyboardListener(inputFields) {
            expandOrCollapse(false)
        }
        vb.vkAuth.setOnClickListener {
            VK.login(baseActivity, arrayListOf(VKScope.EMAIL))
        }
        vb.authBtn.setOnClickListener {
            setDefaultMotionMode()
            viewModel.checkAndAuth(controller.getUserData(viewModel.currentMode))
        }
        vb.googleAuth.setOnClickListener {
            baseActivity.startActivityForResult(googleClient.signInIntent, GOOGLE_MARK)
        }
        vb.facebookAuth.setOnClickListener {
            LoginManager.getInstance().logInWithReadPermissions(this, listOf("email", "public_profile"))
        }
        vb.haveAccountTitle.setOnClickListener {
            val type = if (viewModel.currentMode == SIGN_UP) SIGN_IN else SIGN_UP
            controller.setAuthType(resources, type)
            controller.setDefaultAll()
        }
    }

    override fun setObservers() {
        viewModel.userData.observe(viewLifecycleOwner) {
            controller.setDefaultAll()
            success(it)
        }
        viewModel.progress.observe(viewLifecycleOwner) {
            controller.manageProgressBar(viewModel.currentMode, it)
        }
        viewModel.authError.observeEvent(viewLifecycleOwner) {
            controller.setErrorByType(it.list)
            showSnackbar(it.message)
        }
    }

    private fun success(user: User) {
        showSnackbar(
            message = String.format("%s, добро пожаловать!", user.username),
            customColor = true,
            showCallback = {
                Timber.tag("Auth").d("Load data")
            },
            hideCallback = {
                navigator.toHomeScreen()
            }
        )
    }

    private fun setGoogleClient() {
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestEmail()
            .requestProfile()
            .requestIdToken(GOOGLE_CLIENT_ID)
            .build()

        googleClient = GoogleSignIn.getClient(baseActivity, gso)
    }

    private fun setFacebookClient() {
        fbCallbackManager = CallbackManager.Factory.create()

        LoginManager.getInstance().registerCallback(fbCallbackManager, getCallbackManager {
            viewModel.facebookAuth(it)
        })
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        fbCallbackManager.onActivityResult(requestCode, resultCode, data)
        super.onActivityResult(requestCode, resultCode, data)
    }

    private fun setDefaultMotionMode() {
        requireView().hideKeyboard()
        expandOrCollapse(false)
    }

    private fun expandOrCollapse(expand: Boolean) {
        if (expand) {
            vb.motionLayout.transitionToEnd()
        } else {
            vb.motionLayout.transitionToStart()
        }
    }

    companion object {
        const val SIGN_UP = "Sign Up"
        const val SIGN_IN = "Sign In"
    }
}