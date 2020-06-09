package id.radhika.feature.user.screen.login

import android.view.View
import id.lesprivate.lib.mvvm.BaseScreen
import id.lesprivate.lib.mvvm.Renderer
import id.lesprivate.lib.ui.utils.EditTextHelper
import id.radhika.feature.user.databinding.ScreenLoginBinding


class LoginScreen : BaseScreen<ScreenLoginBinding, LoginVM, LoginDao>(ScreenLoginBinding::inflate) {

    private val email
        get() = binding.etEmail.text.toString().trim()
    private val password
        get() = binding.etPassword.text.toString().trim()

    override fun onViewReady() {
        EditTextHelper.addTypingListener(binding.etEmail, binding.etPassword) { pos, result ->
            vm.validateFields(pos, email, password)
        }
    }

    override fun render(): Renderer<LoginDao> = {
        renderLoading(this)
        renderButton(this)
        renderError(this)

        if (isValid)
            finishScreen()
    }

    private fun renderLoading(loginDao: LoginDao) {
        binding.etEmail.isEnabled = !loginDao.isLoading
        binding.etPassword.isEnabled = !loginDao.isLoading
        binding.labelForgotPassword.isEnabled = !loginDao.isLoading

        if (loginDao.isLoading) {
            binding.buttonProgress.visibility = View.VISIBLE
            binding.buttonText.visibility = View.GONE
        } else {
            binding.buttonProgress.visibility = View.GONE
            binding.buttonText.visibility = View.VISIBLE
        }
    }

    private fun renderError(loginDao: LoginDao) {
        EditTextHelper.resetAllError(binding.inputEmail, binding.inputPassword)
        loginDao.apply {
            errorEmail?.let { binding.inputEmail.error = getString(it) }
            errorPassword?.let { binding.inputPassword.error = getString(it) }
        }
    }

    private fun renderButton(loginDao: LoginDao) {
        if (!loginDao.isAnyError && !loginDao.isLoading)
            binding.buttonLogin.setOnClickListener { vm.onClickLogin(email, password) }
        else
            binding.buttonLogin.setOnClickListener(null)
    }

    override fun getViewModel() = LoginVM::class.java
}
