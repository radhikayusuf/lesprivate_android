package id.radhika.feature.user.screen.register

import android.R as AndroidR
import android.view.View
import android.widget.ArrayAdapter
import id.lesprivate.lib.mvvm.BaseScreen
import id.lesprivate.lib.mvvm.Renderer
import id.lesprivate.lib.ui.utils.EditTextHelper
import id.radhika.feature.user.databinding.ScreenRegisterBinding

class RegisterScreen :
    BaseScreen<ScreenRegisterBinding, RegisterVM, RegisterDao>(ScreenRegisterBinding::inflate) {

    private val email
        get() = binding.etEmail.text.toString().trim()
    private val username
        get() = binding.etUserName.text.toString().trim()
    private val password
        get() = binding.etPassword.text.toString().trim()
    private val passwordAgain
        get() = binding.etPasswordAgain.text.toString().trim()
    private val fullname
        get() = binding.etNamaPelanggan.text.toString().trim()
    private val levelPosition
        get() = binding.spinnerLevel.selectedItemPosition

    private val spinnerAdapter by lazy {
        ArrayAdapter(
            requireContext(),
            AndroidR.layout.simple_spinner_dropdown_item,
            mutableListOf<String>()
        )
    }

    override fun onViewReady() {
        binding.spinnerLevel.adapter = spinnerAdapter
        EditTextHelper.addTypingListener(binding.etNamaPelanggan, binding.etUserName, binding.etEmail, binding.etPassword, binding.etPasswordAgain) { pos, _ ->
            vm.validateFields(pos, fullname, username, email, password, passwordAgain, levelPosition)
        }
    }

    override fun render(): Renderer<RegisterDao> = {
        renderButton(this)
        renderSpinner(this)
        renderError(this)
        renderLoading(this)

        if (isValid)
            finishScreen()
    }

    private fun renderError(registerDao: RegisterDao) {
        EditTextHelper.resetAllError(binding.inputEmail, binding.inputUsername, binding.inputNamaPelanggan, binding.inputPassword, binding.inputPasswordAgain)
        registerDao.apply {
            errorFullName?.let { binding.inputNamaPelanggan.error = getString(it) }
            errorUserName?.let { binding.inputUsername.error = getString(it) }
            errorEmail?.let { binding.inputEmail.error = getString(it) }
            errorPassword?.let { binding.inputPassword.error = getString(it) }
            errorPasswordAgain?.let { binding.inputPasswordAgain.error = getString(it) }
        }
    }

    private fun renderSpinner(registerDao: RegisterDao) {
        if (registerDao.levels.isNotEmpty()) {
            spinnerAdapter.let { adp ->
                adp.clear()
                adp.addAll(registerDao.levels)
                adp.notifyDataSetChanged()
            }
        }
    }

    private fun renderLoading(registerDao: RegisterDao) {
        binding.etEmail.isEnabled = !registerDao.isLoading
        binding.etUserName.isEnabled = !registerDao.isLoading
        binding.etNamaPelanggan.isEnabled = !registerDao.isLoading
        binding.etPassword.isEnabled = !registerDao.isLoading
        binding.etPasswordAgain.isEnabled = !registerDao.isLoading
        binding.spinnerLevel.isEnabled = !registerDao.isLoading

        if (registerDao.isLoading) {
            binding.buttonProgress.visibility = View.VISIBLE
            binding.buttonText.visibility = View.GONE
        } else {
            binding.buttonProgress.visibility = View.GONE
            binding.buttonText.visibility = View.VISIBLE
        }
    }

    private fun renderButton(registerDao: RegisterDao) {
        if (!registerDao.isAnyError && !registerDao.isLoading)
            binding.buttonRegister.setOnClickListener { vm.onClickRegister(username, fullname, email, password, levelPosition) }
        else
            binding.buttonRegister.setOnClickListener(null)
    }

    override fun getViewModel() = RegisterVM::class.java
}
