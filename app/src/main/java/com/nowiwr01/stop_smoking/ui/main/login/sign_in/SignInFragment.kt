package com.nowiwr01.stop_smoking.ui.main.login.sign_in

import by.kirich1409.viewbindingdelegate.viewBinding
import com.nowiwr01.stop_smoking.R
import com.nowiwr01.stop_smoking.databinding.FragmentSignInBinding
import com.nowiwr01.stop_smoking.ui.main.login.BaseSignFragment
import org.koin.core.parameter.parametersOf

/***
 * Вся общая логика для @see[SignInFragment] и SignUpFragment находится в @see[BaseSignFragment]
 */
class SignInFragment : BaseSignFragment(R.layout.fragment_sign_in) {

    override val inputFields by lazy {
        listOf(vb.email, vb.password0)
    }

    override val vb by viewBinding<FragmentSignInBinding>()
    override val controller by inject<SignInViewsController> {
        parametersOf(vb)
    }

    override fun setListeners() {
        super.setListeners()
        vb.login.setOnClickListener {
            setDefaultMotionMode()
            viewModel.checkAndAuth(controller.getUserData())
        }
    }
}