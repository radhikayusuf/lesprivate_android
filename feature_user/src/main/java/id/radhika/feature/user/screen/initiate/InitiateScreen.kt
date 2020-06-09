package id.radhika.feature.user.screen.initiate

import id.lesprivate.lib.mvvm.BaseScreen
import id.lesprivate.lib.mvvm.Renderer
import id.radhika.feature.user.databinding.ScreenInitiateBinding
import id.radhika.feature.user.screen.UserActivity
import id.radhika.feature.user.screen.login.LoginScreen
import id.radhika.feature.user.screen.register.RegisterScreen


class InitiateScreen : BaseScreen<ScreenInitiateBinding, InitiateVM, InitiateDao>(ScreenInitiateBinding::inflate) {

    val userActivity by lazy { requireActivity() as UserActivity }

    override fun onViewReady() {
        binding.buttonLogin.setOnClickListener { openScreen(LoginScreen()) }
        binding.buttonRegister.setOnClickListener { openScreen(RegisterScreen()) }
    }

    override fun render(): Renderer<InitiateDao> = {

    }

    override fun getViewModel() = InitiateVM::class.java

}
