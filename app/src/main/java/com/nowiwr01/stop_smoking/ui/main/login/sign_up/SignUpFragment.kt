package com.nowiwr01.stop_smoking.ui.main.login.sign_up

import by.kirich1409.viewbindingdelegate.viewBinding
import com.nowiwr01.stop_smoking.R
import com.nowiwr01.stop_smoking.databinding.FragmentSignUpBinding
import com.nowiwr01.stop_smoking.ui.main.login.BaseSignFragment
import org.koin.core.parameter.parametersOf

/***
 * Вся общая логика для @see[SignUpFragment] и SignInFragment находится в @see[BaseSignFragment]
 */
class SignUpFragment : BaseSignFragment(R.layout.fragment_sign_up) {

    override val inputFields by lazy {
        listOf(vb.email, vb.username, vb.password0, vb.password1)
    }

    override val vb by viewBinding<FragmentSignUpBinding>()
    override val controller by inject<SignUpViewsController> {
        parametersOf(vb)
    }

    override fun setListeners() {
        super.setListeners()
        vb.signUp.setOnClickListener {
            setDefaultMotionMode()
            viewModel.checkAndAuth(controller.getUserData())
        }
        vb.vkSignUp.setOnClickListener {
            authVk()
        }
    }
}